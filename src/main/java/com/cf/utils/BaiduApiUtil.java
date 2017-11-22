package com.cf.utils;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.speech.AipSpeech;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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

    private StringBuffer change2BasicSentence(String content) {
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

    private String replaceAllSigns(String content) {
        return content.replaceAll("\\(|\\)|\\（|\\）|\\:|\\：", "").replaceAll("\\p{P}" , ",");
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
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(BaiduApiUtil.APP_ID, BaiduApiUtil.API_KEY, BaiduApiUtil.SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        JSONObject res = client.asr(wavPath, "wav", 16000, null);
        System.out.println(res);
        if (0==res.getInt("err_no")) return res.getJSONArray("result").get(0).toString();
        else throw new RuntimeException("语音识别异常:"+res.getString("err_msg"));
    }

}
