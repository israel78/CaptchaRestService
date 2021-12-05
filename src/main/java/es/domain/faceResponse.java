package es.domain;

import lombok.Data;

@Data
public class faceResponse {
    private int age;
    private String gender;
    private faceRectangleResponse faceRectangleResponse;
}
