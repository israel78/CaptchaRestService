package es.captcha.repository;

import es.captcha.domain.*;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

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
    void mergeSkillItems(SkillItem skillItem);
    void mergeDevToolsItems(DevToolItems devToolItems);
    void mergeFunctionsItems(JobFunctionItems jobFunctionItems);
}
