package es.captcha.controller;

import es.captcha.domain.CaptchaSettings;
import es.captcha.domain.GraphicValues;
import es.captcha.domain.ResponseValues;
import es.captcha.domain.User;
import es.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@PropertySource("classpath:variables.properties")
@RestController
@RequestMapping("/api/home/app")
public class HomeController {

    @Autowired
    private Service service;

    @Value( "${api.key}" )
    private String key;
    @Value( "${user.id.default}" )
    private int userIdDefault;

    @CrossOrigin(origins = {"http://localhost:8080","https://captcha-front.herokuapp.com","https://israelpersonalpage.herokuapp.com"}, allowCredentials = "true")
    @GetMapping(value = "/getgraphicdataandphrase", produces = "application/json")
    public ResponseEntity<User> getGraphicDataAndPhrase(@RequestHeader(name = "Authorization") String apiKey) {
        if(apiKey.equals(key))
            return new ResponseEntity<User>(service.getUserById(userIdDefault), HttpStatus.OK);
        else
            return new ResponseEntity<User>(new User(), HttpStatus.UNAUTHORIZED);
    }
}
