package es.domain;

import lombok.Data;

@Data
public class FaceResponse {
    private int age;
    private String gender;
    private FaceRectangleResponse faceRectangleResponse;
}
