package es.captcha.controller;

import es.captcha.domain.CaptchaSettings;
import es.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@PropertySource("classpath:variables.properties")
@RestController
@Scope("session")
@RequestMapping("/api/captcha/config")
public class SettingsController {

    @Autowired
    private Service service;

    @Value( "${api.key}" )
    private String key;
    @CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/getsettings")
    public ResponseEntity<CaptchaSettings> getSettings(
            @RequestHeader(name = "Authorization") String apiKey
    ) {
        CaptchaSettings captchaSettingsResponse = new CaptchaSettings();
        if (apiKey != null && apiKey.equals(key)) {
            captchaSettingsResponse = service.getCaptchaSettings();
            return new ResponseEntity<CaptchaSettings>(captchaSettingsResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<CaptchaSettings>(captchaSettingsResponse, HttpStatus.UNAUTHORIZED);
        }
    }
    @CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
    @PostMapping (value = "/setsettings")
    public ResponseEntity<CaptchaSettings> setPriceResponse(
            @RequestHeader(name = "Authorization") String apiKey,
            @RequestBody CaptchaSettings captchaSettings
    ){
        //Si el número de caracteres es menor que 4 o mayor que 10...
        if(captchaSettings.getNumCharact()>10)
            captchaSettings.setNumCharact(10);
        if(captchaSettings.getNumCharact()<4)
            captchaSettings.setNumCharact(4);
        if (apiKey != null && apiKey.equals(key)) {
            service.setCaptchaSettings(captchaSettings);
            return new ResponseEntity<>(service.getCaptchaSettings(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CaptchaSettings(), HttpStatus.UNAUTHORIZED);
        }
    }
}
