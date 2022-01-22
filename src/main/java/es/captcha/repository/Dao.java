package es.captcha.repository;

import es.captcha.domain.*;

import java.util.List;

public interface Dao {
    CaptchaSettings getCaptaSettings();
    boolean setCaptaSettings(CaptchaSettings captchaSettings);
    List<User> getUsers();
    void saveOrUpdateUser(User user);
    void mergeGraphic(Graphic graphic);
    List<Experience> getExperiencesByUser(int user);
    List<Experience> getExperiences();
    List<DevToolItems> getDevToolsItems();
    List<JobFunctionItems> getJobFunctionsItems();
    List<SkillItem> getSkillItems();
}
