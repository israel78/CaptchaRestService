package es.captcha.repository;

import es.captcha.domain.Graphic;
import es.captcha.domain.GraphicValues;
import es.captcha.domain.User;
import es.captcha.domain.CaptchaSettings;
import java.util.List;

public interface Dao {
    CaptchaSettings getCaptaSettings();
    boolean setCaptaSettings(CaptchaSettings captchaSettings);
    List<User> getUsers();
    void saveOrUpdateUser(User user);
    void mergeGraphic(Graphic graphic);

}
