package com.cf.config;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.speech.AipSpeech;
import com.cf.utils.BaiduApiUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * 生成验证码配置
 *
 */
@Configuration
public class BaiduAipConfig {

    @Bean
    public AipNlp initAipNlp() {
        AipNlp aipNlp = new AipNlp(BaiduApiUtil.APP_ID, BaiduApiUtil.API_KEY, BaiduApiUtil.SECRET_KEY);
        // 可选：设置网络连接参数
        aipNlp.setConnectionTimeoutInMillis(2000);
        aipNlp.setSocketTimeoutInMillis(60000);
        return aipNlp;
    }

    @Bean
    public AipSpeech initAipSpeech() {
        AipSpeech aipSpeech = new AipSpeech(BaiduApiUtil.APP_ID, BaiduApiUtil.API_KEY, BaiduApiUtil.SECRET_KEY);
        // 可选：设置网络连接参数
        aipSpeech.setConnectionTimeoutInMillis(2000);
        aipSpeech.setSocketTimeoutInMillis(60000);
        return aipSpeech;
    }
}
