package com.zhu.zhupro.utils.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

public class CreateCaptcha {

    public DefaultKaptcha getCaptcha() {
        // 创建Kaptcha对象
        DefaultKaptcha dk = new DefaultKaptcha();
        // 验证码配置

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("kaptcha.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            Config config = new Config(properties);
            dk.setConfig(config);
            return dk;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
