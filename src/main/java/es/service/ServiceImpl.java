package es.service;

import es.captcha.repository.Dao;
import es.captcha.domain.CaptchaSettings;
import org.springframework.beans.factory.annotation.Autowired;

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

}

