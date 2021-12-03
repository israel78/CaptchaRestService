package es.controller;

import es.domain.CaptchaSettings;
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
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static es.utils.Utils.generateNewToken;

@PropertySource("classpath:variables.properties")
@RestController
@Scope("application")
@RequestMapping("/api/captcha/app")
public class CaptchaController {

    @Autowired
    private Service service;

    @Value( "${api.key}" )
    private String key;


    @RequestMapping(value = "/login", produces = "application/json")
    public ResponseEntity<Map<String, String>> login(@RequestHeader(name = "pass", required = true) String pass,
                                                     @RequestHeader(name = "user", required = true) String userName,
                                                     @RequestBody String captcha,
                                                     HttpSession session) {
        Map<String, String> result = new <String, String>HashMap();
        if (service.login(userName, pass)) {
            if(session.getAttribute("Captcha")==null||!session.getAttribute("Captcha").equals(captcha)){
             int  attempsSaved =
                     Integer.parseInt((String)session.getAttribute("attemps"));
                if(attempsSaved==0){
                    session.setAttribute("attemps","1");
                    result.put("attemps","1");
                }else{
                    attempsSaved = attempsSaved+1;
                    session.setAttribute("attemps",String.valueOf(attempsSaved));
                    result.put("attemps",String.valueOf(attempsSaved));
                }
                result.put("id", service.getUserId(userName));
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }else{
                String token = Utils.generateNewToken();
                result.put("Token", token);
                result.put("id", service.getUserId(userName));
                session.setAttribute("Token", token);
                session.setAttribute("attemps", "0");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            } else {
            result.put("Token", "Not authorized");
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
    @RequestMapping(value = "/getcaptcha")
    public ResponseEntity <Map<String,Integer>> setPriceResponse(
            @RequestHeader(name = "Authorization") String apiKey,
            HttpSession session
    ) {
        Map<String, Integer> result = new <String, Integer>HashMap();
       CaptchaSettings captchaSettings = service.getCaptchaSettings();
        String newCaptcha = Utils.generateNewCaptcha(captchaSettings);
        session.setAttribute("Captcha",newCaptcha);
        session.setAttribute("attemps","0");
        if (apiKey != null && apiKey.equals(key)) {
            result.put(newCaptcha,captchaSettings.getAttemps());
            return new ResponseEntity <Map<String,Integer>>(result, HttpStatus.OK);
        } else {
            result.put("",0);
            return new ResponseEntity< Map<String,Integer>>(result, HttpStatus.UNAUTHORIZED);
        }
    }
}
