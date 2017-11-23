package com.cf.utils;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.speech.AipSpeech;
import com.cf.entity.BusRuleListEntity;
import com.cf.entity.BusRuleSttListEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BaiduApiUtil {
    protected Logger logger = LoggerFactory.getLogger(BaiduApiUtil.class);

    @Autowired
    private AipNlp aipNlpClient;

    @Autowired
    private AipSpeech aipSpeechClient;

    //设置APPID/AK/SK
    public static final String APP_ID = "10379724";
    public static final String API_KEY = "sGGqNIXCLWWaPegcthgDih2x";
    public static final String SECRET_KEY = "b6iQL0aHW5cXG8Eq9WyZ3ad1RINO0HXm";

    public String compareSimilarDegree(String content, String standard_text) {
        logger.info("目标内容：{}",content);
        logger.info("标准内容：{}",standard_text);

        StringBuffer sbContent = change2BasicSentence(content);
        StringBuffer sbStandardContent = change2BasicSentence(standard_text);

        // 选择CNN算法
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("model", "BOW");
        System.out.println("替换后内容:"+sbContent);
        compareSimilarFrom2Array(sbStandardContent, sbContent);
        JSONObject res = aipNlpClient.simnet(sbContent.toString(),standard_text,options);
        return res.get("score")+"";
    }

    public List<BusRuleSttListEntity>  compareSimilarDegree(List<BusRuleListEntity> ruleList, String stt) {
        logger.info("目标内容：{}",stt);
        return compareSimilarFrom2Array(ruleList, stt);
//        JSONObject res = aipNlpClient.simnet(sbContent.toString(),standard_text,options);
//        return  List<HashMap<String,Object>>
    }

    public StringBuffer change2BasicSentence(String content) {
        JSONArray wordsArray = aipNlpClient.lexer(content).getJSONArray("items");
        StringBuffer sbContent = new StringBuffer();
        Boolean preWordIsLOC = false;
        for (int i=0;i<wordsArray.length();i++) {
            JSONObject item = (JSONObject) wordsArray.get(i);
            sbContent.append(getStandarTextFromJSON(item,preWordIsLOC));
            preWordIsLOC = "LOC".equals(item.getString("ne"));
        }
        return sbContent;
    }

    private List<BusRuleSttListEntity> compareSimilarFrom2Array(List<BusRuleListEntity> ruleList, String stt) {
        String[] contentsOld = replaceAllSigns(stt).split(",");
        StringBuffer sbContent = change2BasicSentence(stt);
        String[] contents = replaceAllSigns(sbContent.toString()).split(",");
        HashMap<Integer, Boolean> flagMap = new HashMap<Integer,Boolean>();
        // 选择CNN算法
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("model", "BOW");

        List<BusRuleSttListEntity> list = new ArrayList<BusRuleSttListEntity>();
        for (BusRuleListEntity item:ruleList){
            Double tempScore=0.0;
            for (Integer i=0;i<contents.length;i++){
                String sentence = contents[i];
                JSONObject res =  aipNlpClient.simnet(sentence,item.getStandardText(),options);
                Double score;
                try {
                    score = res.getDouble("score");
                } catch (JSONException e) {
                    score=0.0;
                }
                if (score > tempScore) tempScore = score;
            }
            BusRuleSttListEntity entity = new BusRuleSttListEntity();
            entity.setRuleListId(item.getId());
            entity.setScore(tempScore);
            entity.setWeight(item.getWeight());
            list.add(entity);
        }
        return list;
    }

    private List<BusRuleSttListEntity> compareSimilarFrom2Array2(List<BusRuleListEntity> ruleList, String stt) {
        List<BusRuleSttListEntity> list = new ArrayList<BusRuleSttListEntity>();
        String sttNosign = replaceAllSigns2(stt);
        JSONArray wordsArray = aipNlpClient.lexer(sttNosign).getJSONArray("items");
        Boolean preWordIsLOC = false;
        List<String> sttList = new ArrayList<String>();
        for (int i=0;i<wordsArray.length();i++) {
            JSONObject item = (JSONObject) wordsArray.get(i);
            if ("u".equals(item.getString("pos")) || "v".equals(item.getString("pos"))) continue;
            sttList.add(getStandarTextFromJSON(item,preWordIsLOC));
            preWordIsLOC = "LOC".equals(item.getString("ne"));
        }
        for (BusRuleListEntity rule :ruleList) {
            BusRuleSttListEntity entity = new BusRuleSttListEntity();
            JSONArray array = aipNlpClient.lexer(rule.getStandardText()).getJSONArray("items");
            int count =0;
            double totalScore=0.0;
            for (int i=0;i<array.length();i++) {
                JSONObject item = (JSONObject) array.get(i);
                //跳过介词
                if ("u".equals(item.getString("pos")) || "v".equals(item.getString("pos"))) continue;
                JSONArray basic_words = item.getJSONArray("basic_words");
                //替换成员关系
                if (basic_words.length()==1 && isRelations(item)) continue;
                else if ("TIME".equals(item.getString("ne")) && basic_words.length()>1)
                    continue;
                else if ("PER".equals(item.getString("ne")))
                    continue;
                else if ("ORG".equals(item.getString("ne")))
                    continue;
                else if ("LOC".equals(item.getString("ne")))
                    continue;
                else {
                        count++;
                        totalScore+=getScoreFrom(item.getString("item"),sttList);
                    }
                }
            entity.setRuleListId(rule.getId());
            entity.setScore(totalScore/count);
            list.add(entity);
        }
        return list;
    }

    private double getScoreFrom(String item, List<String> sttList) {
        Double tempScore=0.0;
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("model", "BOW");
        for (String stt:sttList) {
            Double score;
            logger.info("比较:{}和{}相识度....",item,stt);
            JSONObject res  = aipNlpClient.simnet(item,stt,options);
            try {
                score = res.getDouble("score");
            } catch (JSONException e) {
                continue;
            }
            if (score==1.0) return 1.0;
            if (score > tempScore) tempScore = score;
        }
        logger.info("项目:{}得分{}",item,tempScore);
        return  tempScore;
    }

    private void compareSimilarFrom2Array(StringBuffer standard_text, StringBuffer sbContent) {
        String[] contents = replaceAllSigns(sbContent.toString()).split(",");
        String[] standard_texts = replaceAllSigns(standard_text.toString()).split(",");
        HashMap<Integer, Boolean> flagMap = new HashMap<Integer,Boolean>();
        // 选择CNN算法
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("model", "BOW");
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(BaiduApiUtil.APP_ID, BaiduApiUtil.API_KEY, BaiduApiUtil.SECRET_KEY);
        for (String standard_sentence:standard_texts){
            for (Integer i=0;i<contents.length;i++){
                if (flagMap.get(i)!=null && flagMap.get(i)) continue;
                String sentence = contents[i];
                JSONObject res =  client.simnet(sentence,standard_sentence,options);
                Double score = res.getDouble("score");
                if (score<0.9) {
                    if (i==contents.length-1)
                        logger.info("标准：{}没有命中....",standard_sentence,score);
                    continue;
                }
                flagMap.put(i,true);
                insert2ScoreListTable(1,sentence,standard_sentence,score);
                break;
            }
        }
    }

    private void insert2ScoreListTable(Integer rule_list_id, String sentence, String standard_sentence, Double score) {
        logger.info("在这里编写插入分句得分表:语句：{}，标准：{}，得分{}",sentence,standard_sentence,score);
        //todo
    }

    public String replaceAllSigns(String content) {
        return content.replaceAll("\\(|\\)|\\（|\\）|\\:|\\：|\\、", "").replaceAll("\\p{P}" , ",");
//		return content.replaceAll("\\p{P}" , "");
    }

    public String replaceAllSigns2(String content) {
        return content.replaceAll("\\p{P}" , "");
//		return content.replaceAll("\\p{P}" , "");
    }


    private String getStandarTextFromJSON(JSONObject item, boolean preWordIsLOC) {
        JSONArray basic_words = item.getJSONArray("basic_words");

        //替换成员关系
        if (basic_words.length()==1 && isRelations(item)) return "丈夫";
        else if ("TIME".equals(item.getString("ne")) && basic_words.length()>1)
            return "2017年11月17日";
        else if ("PER".equals(item.getString("ne")))
            return "张三";
        else if ("ORG".equals(item.getString("ne")))
            return "平安人寿大连分公司";
        else if ("LOC".equals(item.getString("ne"))) {
            if (preWordIsLOC) return "";
            return "浙江省";
        }
        else return item.getString("item");
    }

    private boolean isRelations(JSONObject item) {
        if  (!"n".equals(item.getString("pos"))) return false;
        String text = item.getString("item");
        return    "丈夫".equals(text)
                || "妻子".equals(text)
                || "哥哥".equals(text)
                || "弟弟".equals(text)
                || "老婆".equals(text)
                || "老公".equals(text)
                || "儿子".equals(text)
                || "外公".equals(text)
                || "外婆".equals(text)
                || "姥姥".equals(text)
                || "姥爷".equals(text)
                || "姐姐".equals(text)
                || "妹妹".equals(text)
                || "爸爸".equals(text)
                || "父亲".equals(text)
                || "母亲".equals(text);
    }



    public String Speed2Text(String wavPath) {
        JSONObject res = null;
        if (wavPath.lastIndexOf(".wav")>0) {
            res = aipSpeechClient.asr(wavPath, "wav", 16000, null);
        } else if (wavPath.lastIndexOf(".amr")>0) {
            res = aipSpeechClient.asr(wavPath, "amr", 8000, null);
        }
        // 调用接口
        System.out.println(res);
        if (0==res.getInt("err_no")) return res.getJSONArray("result").get(0).toString();
        else throw new RuntimeException("语音识别异常:"+res.getString("err_msg"));
    }

    public static void main(String[] args){
        AipSpeech aipSpeech = new AipSpeech(BaiduApiUtil.APP_ID, BaiduApiUtil.API_KEY, BaiduApiUtil.SECRET_KEY);
        // 可选：设置网络连接参数
        aipSpeech.setConnectionTimeoutInMillis(2000);
        aipSpeech.setSocketTimeoutInMillis(60000);
        JSONObject res =  aipSpeech.asr("E:/1-1.amr", "amr", 8000, null);
        System.out.println(res);
    }
}
