package es.captcha.controller;

import es.captcha.domain.Experience;
import es.captcha.domain.Graphic;
import es.captcha.domain.ResponseValues;
import es.captcha.domain.User;
import es.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:variables.properties")
@RestController
@RequestMapping("/api/experience/app")
public class ExperienceController {

    @Autowired
    private Service service;

    @Value( "${api.key}" )
    private String key;
    @Value( "${user.id.default}" )
    private int userIdDefault;

    @CrossOrigin(origins = {"http://localhost:8080","https://captcha-front.herokuapp.com","https://israelpersonalpage.herokuapp.com"}, allowCredentials = "true")
    @GetMapping(value = "/getexperiencesofuser", produces = "application/json")
    public ResponseEntity <List<Experience>> getExperiencesOfUser(@RequestHeader(name = "Authorization") String apiKey
   , @RequestParam(name="userid") int userId) {
        if(apiKey.equals(key)) {
            List<Experience> experiences = service.getExperiencesByUser(userId);
            return new ResponseEntity<List<Experience>>(experiences, HttpStatus.OK);
        } else
            return new ResponseEntity<List<Experience>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
    }
}
