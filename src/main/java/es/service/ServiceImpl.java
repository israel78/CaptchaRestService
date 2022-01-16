package es.service;

import es.captcha.domain.*;
import es.captcha.repository.Dao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    private Dao dao;

    public CaptchaSettings getCaptchaSettings() {
        return dao.getCaptaSettings();
    }
    public boolean setCaptchaSettings(CaptchaSettings captchaSettings) {
        return dao.setCaptaSettings(captchaSettings);
    }

    public boolean login(String userName, String pass) {
        return null != dao.getUsers().stream()
                .filter(u -> u.getFirstName().equals(userName))
                .filter(u -> u.getPassw().equals(pass)).findAny().orElse(null);
    }
    public String getUserId(String userName) {
         return String.valueOf(dao.getUsers().stream()
                .filter(u -> u.getFirstName().equals(userName))
                .findAny().orElse(null).getId());

    }
    public User getUserById(int id) {
        return dao.getUsers().stream()
                .filter(u -> u.getId()==id)
                .findAny().orElse(null);

    }
    public void saveOrUpdateUser(User user){
        dao.saveOrUpdateUser(user);
    }
    public void saveOrUpdateGraphicAndGraphicData(Graphic graphic){
        dao.mergeGraphic(graphic);
    }
    public List<Experience> getExperiencesByUser(int userId){
        return dao.getExperiencesByUser(userId);
    };
}

