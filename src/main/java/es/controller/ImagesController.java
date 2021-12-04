package es.controller;

import es.domain.CaptchaSettings;
import es.domain.ResponseValues;
import es.domain.User;
import es.service.ImagesService;
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
import java.util.ArrayList;

@PropertySource("classpath:variables.properties")
@RestController
@Scope("session")
@RequestMapping("/api/images")
public class ImagesController {

    @Autowired
    private ImagesService service;

    @Value( "${api.key}" )
    private String key;
    @CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/processimages", produces = "application/json")
    public ArrayList<ImagesDataResponse> processImages(@RequestBody ArrayList<String> urlList,
                                                        @RequestHeader(name = "Authorization") String token,
                                                        HttpSession session) {

        return new ResponseEntity<>( service.getProcessImages(urlList), HttpStatus.OK);
    }
}

