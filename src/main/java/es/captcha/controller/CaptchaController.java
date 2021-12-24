package es.captcha.controller;

import es.captcha.domain.CaptchaSettings;
import es.captcha.domain.ResponseValues;
import es.captcha.domain.User;
import es.service.Service;
import es.utils.Utils;
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
@Scope("session")
@RequestMapping("/api/captcha/app")
public class CaptchaController {

    @Autowired
    private Service service;

    @Value( "${api.key}" )
    private String key;
    @CrossOrigin(value = {"http://localhost:8080,https://captcha-front.herokuapp.com"}, allowCredentials = "true")
    @RequestMapping(value = "/login", produces = "application/json")
    public ResponseEntity<ResponseValues> login(@RequestBody User user,
                                                     HttpSession session) {
        ResponseValues result = new ResponseValues();
        if (service.login(user.getFirstName(), user.getPassw())) {
            if(session.getAttribute("Captcha")==null
                    ||!session.getAttribute("Captcha").equals(user.getCaptcha())){
             int attempsSaved=0;
                if(session.getAttribute("attemps")==null){
                    session.setAttribute("attemps","1");
                    result.setKey("1");
                }else{
                    attempsSaved =
                            Integer.parseInt((String)session.getAttribute("attemps"));
                    attempsSaved = attempsSaved+1;
                    session.setAttribute("attemps",String.valueOf(attempsSaved));
                    result.setKey(String.valueOf(attempsSaved));
                }
                result.setValue("Captcha no correcto");
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }else{
                String token = Utils.generateNewToken();
                result.setKey(token);
                result.setValue(service.getUserId(user.getFirstName()));
                session.setAttribute("Token", token);
                session.setAttribute("attemps", "0");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            } else {
            result.setValue("Usuario no autorizado");
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
    @CrossOrigin(value = {"http://localhost:8080,https://captcha-front.herokuapp.com"}, allowCredentials = "true")
    @RequestMapping(value = "/getcaptcha")
    public ResponseEntity <ResponseValues> setPriceResponse(
            @RequestHeader(name = "Authorization") String apiKey,
            HttpSession session
    ) {
        ResponseValues result = new ResponseValues();
       CaptchaSettings captchaSettings = service.getCaptchaSettings();
        String newCaptcha = Utils.generateNewCaptcha(captchaSettings);
        session.setAttribute("Captcha",newCaptcha);
        session.setAttribute("attemps","0");
        if (apiKey != null && apiKey.equals(key)) {
            result.setKey(newCaptcha);
            result.setValue(String.valueOf(captchaSettings.getAttemps()));

            return new ResponseEntity <ResponseValues>(result, HttpStatus.OK);
        } else {

            return new ResponseEntity<ResponseValues>(new ResponseValues(), HttpStatus.UNAUTHORIZED);
        }
    }
}
