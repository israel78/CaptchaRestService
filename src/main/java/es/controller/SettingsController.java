package es.controller;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import es.service.Service;
import es.domain.CaptchaSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
@PropertySource("classpath:variables.properties")
@RestController
@Scope("application")
@RequestMapping("/api/captcha/config")
public class SettingsController {

    @Autowired
    private Service service;

    @Value( "${api.key}" )
    private String key;
    @RequestMapping(value = "/getsettings")
    public ResponseEntity<CaptchaSettings> getPriceResponse(
            @RequestHeader(name = "Authorization") String apiKey,
            HttpSession session
    ) {
        CaptchaSettings captchaSettingsResponse = new CaptchaSettings();
        if (apiKey != null && apiKey.equals(key)) {
            captchaSettingsResponse = service.getCaptchaSettings();
            return new ResponseEntity<CaptchaSettings>(captchaSettingsResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<CaptchaSettings>(captchaSettingsResponse, HttpStatus.UNAUTHORIZED);
        }
    }
    @RequestMapping(value = "/setsettings")
    public ResponseEntity<CaptchaSettings> setPriceResponse(
            @RequestHeader(name = "Authorization") String apiKey,
            @RequestBody CaptchaSettings captchaSettings,
            HttpSession session
    ){
        if (apiKey != null && apiKey.equals(key)) {
            service.setCaptchaSettings(captchaSettings);
            return new ResponseEntity<CaptchaSettings>(service.getCaptchaSettings(), HttpStatus.OK);
        } else {
            return new ResponseEntity<CaptchaSettings>(new CaptchaSettings(), HttpStatus.UNAUTHORIZED);
        }
    }
}
