package es.repository;

import es.domain.User;
import es.domain.CaptchaSettings;

import java.util.Date;
import java.util.List;

public interface Dao {
    public CaptchaSettings getCaptaSettings();
    public boolean setCaptaSettings(CaptchaSettings captchaSettings);
    public List<User> getUsers();
}
