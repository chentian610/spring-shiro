package com.cf.nlp;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.speech.AipSpeech;
import com.cf.utils.BaiduApiUtil;
import org.json.JSONObject;

import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
//    public static final String APP_ID = "10379724";
//    public static final String API_KEY = "sGGqNIXCLWWaPegcthgDih2x";
//    public static final String SECRET_KEY = "b6iQL0aHW5cXG8Eq9WyZ3ad1RINO0HXm";

    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client2 = new AipSpeech(BaiduApiUtil.APP_ID, BaiduApiUtil.API_KEY, BaiduApiUtil.SECRET_KEY);

        // 可选：设置网络连接参数
        client2.setConnectionTimeoutInMillis(2000);
        client2.setSocketTimeoutInMillis(60000);
        // 调用接口
        JSONObject rese = client2.asr("D:/tmp/8k.wav", "pcm", 8000, null);
        System.out.println("eneneenen:"+rese.toString(2));

        // 初始化一个AipNlp
        AipNlp client = new AipNlp(BaiduApiUtil.APP_ID, BaiduApiUtil.API_KEY, BaiduApiUtil.SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String text = "今天是2017年11月17日，地点：浙江省杭州市西湖区数字娱乐产业园，销售人员陈天辉，所属公司平安人寿大连分公司，执业证号AX009，产品名称平安护身福终身寿险（分红型）。投保人为张伟，被保人为陈红，投保人是被保险人的妻子";
//        String text = "今天是{TIME}，地点：{ADDR}{ALL}，销售人员{NAME}，所属公司{ORG}，执业证号{ALL}，产品名称平安护身福终身寿险（分红型）。投保人为{NAME}，被保人为{NAME}，投保人是被保险人的{ALL}。";
        System.out.println(client.lexer(text));
        // 调用接口
        String text2 = "今天是{TIME}，地点：{ADDR}{ALL}，销售人员{NAME}，所属公司{ORG}，执业证号{ALL}，产品名称平安护身福终身寿险（分红型）。投保人为{NAME}，被保人为{NAME}，投保人是被保险人的{ALL}。";
        // 选择CNN算法
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("model", "BOW");
        JSONObject res = client.simnet(text,text2,options);

////        client.
        System.out.println(res.toString(2));
//
//        // 获取两个文本的相似度
//        JSONObject response = client.simnet("百度是个搜索公司", "谷歌是个搜索公司", null);
//        System.out.println(response.toString());
//
//        // 选择CNN算法
//        HashMap<String, String> options2 = new HashMap<String, String>();
//        options.put("model", "CNN");
//        JSONObject response1 = client.simnet("百度是个搜索公司", "谷歌是个搜索公司", options2);
//        System.out.println(response1.toString());
//        // 获取一条文本的情感倾向
//        System.out.println(client.sentimentClassify("百度是一家伟大的公司"));
    }
}