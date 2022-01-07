package es.service;

import es.captcha.domain.CaptchaSettings;
import es.captcha.domain.User;
import org.springframework.http.ResponseEntity;

public interface Service {
    CaptchaSettings getCaptchaSettings();
    boolean setCaptchaSettings(CaptchaSettings captchaSettings);
    boolean login(String userName, String pass);
    String getUserId(String userName);
    User getUserById(int id);
    void saveOrUpdateUser(User user);
}
