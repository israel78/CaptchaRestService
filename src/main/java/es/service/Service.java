package es.service;

import es.captcha.domain.CaptchaSettings;
import es.captcha.domain.Experience;
import es.captcha.domain.Graphic;
import es.captcha.domain.User;
import es.captcha.pojo.ExperienceItemWithStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Service {
    CaptchaSettings getCaptchaSettings();

    boolean setCaptchaSettings(CaptchaSettings captchaSettings);

    boolean login(String userName, String pass);

    String getUserId(String userName);

    User getUserById(int id);

    void saveOrUpdateUser(User user);

    void saveOrUpdateGraphicAndGraphicData(Graphic graphic);

    List<Experience> getExperiencesByUser(int userId);

    List<ExperienceItemWithStatus> getExperiencesListByType(String type);
}