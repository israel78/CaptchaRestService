package es.utils;

import es.domain.CaptchaSettings;
import es.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.Base64;

public class Utils {

    @Autowired
    Service service;
    static char num[] = { '0', '1', '2', '3', '4', '5' };
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public static String generateNewCaptcha(CaptchaSettings captchaSettings) {

        if(captchaSettings.isAlfa()){
            return generateNewToken().substring(0,captchaSettings.getNumCharact());
        }else{
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < captchaSettings.getNumCharact(); i++) {
                strBuilder.append(randomNum());
            }
            return strBuilder.toString();
        }
    }

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static char randomNum() {
        return num[(int) Math.floor(Math.random() * 5)];
    }
}
