package es.captcha.controller;

import es.captcha.domain.*;
import es.captcha.pojo.ExperienceItemWithStatus;
import es.captcha.pojo.Items;
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

    @CrossOrigin(origins = {"http://localhost:8080","http://188.127.182.87","https://captcha-front.herokuapp.com","https://israelpersonalpage.herokuapp.com"}, allowCredentials = "true")
    @GetMapping(value = "/getexperiencesofuser", produces = "application/json")
    public ResponseEntity <List<Experience>> getExperiencesOfUser(@RequestHeader(name = "Authorization") String apiKey
   , @RequestParam(name="userid") int userId) {
        if(apiKey.equals(key)) {
            List<Experience> experiences = service.getExperiencesByUser(userId);
            return new ResponseEntity<List<Experience>>(experiences, HttpStatus.OK);
        } else
            return new ResponseEntity<List<Experience>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
    }
    @CrossOrigin(origins = {"http://localhost:8080","http://188.127.182.87","https://captcha-front.herokuapp.com","https://israelpersonalpage.herokuapp.com"}, allowCredentials = "true")
    @GetMapping(value = "/getexperienceitmes", produces = "application/json")
    public ResponseEntity <List<ExperienceItemWithStatus>> getExperienceItemList(@RequestHeader(name = "Authorization") String apiKey
            , @RequestParam(name="type") String type) {
        if(apiKey.equals(key)) {
            return new ResponseEntity<List<ExperienceItemWithStatus>>(service.getExperiencesListByType(type), HttpStatus.OK);
        } else
            return new ResponseEntity<List<ExperienceItemWithStatus>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
    }
    @CrossOrigin(origins = {"http://localhost:8080","http://188.127.182.87","https://captcha-front.herokuapp.com","https://israelpersonalpage.herokuapp.com"}, allowCredentials = "true")
    @PostMapping(value = "/setexperieneitems", produces = "application/json")
    public ResponseEntity<ResponseValues> setGraphicData(@RequestHeader(name = "Authorization") String apiKey,
                                                         @RequestBody Items items,
                                                         @RequestParam (name="userid") int userId) {
        ResponseValues result = new ResponseValues();
        if(apiKey.equals(key)){
            System.out.println("items");
             service.saveOrUpdateExperieneItems(items);
            result.setKey("resultado");
            result.setValue("Items actualizados correctamente");
            return new ResponseEntity<ResponseValues>(result, HttpStatus.OK);
        } else {
            result.setKey("resultado");
            result.setValue("No tienes permisos para realizar la operación");
            return new ResponseEntity<ResponseValues>(result, HttpStatus.UNAUTHORIZED);
        }
    }
}
