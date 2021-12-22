package es.service;

import es.captcha.domain.CaptchaSettings;

public interface Service {
    CaptchaSettings getCaptchaSettings();
    boolean setCaptchaSettings(CaptchaSettings captchaSettings);
    boolean login(String userName, String pass);
    String getUserId(String userName);

}
