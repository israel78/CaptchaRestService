package es.service;


import es.domain.CaptchaSettings;

import java.util.Date;

public interface Service {
    public CaptchaSettings getCaptchaSettings();
    public boolean setCaptchaSettings(CaptchaSettings captchaSettings);
    public boolean login(String userName, String pass);
    public String getUserId(String userName);

}
