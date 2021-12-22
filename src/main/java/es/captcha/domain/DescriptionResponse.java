package es.captcha.domain;

import lombok.Data;

import java.util.ArrayList;
@Data
public class DescriptionResponse {
    private ArrayList<CaptionResponse> captionResponseList;
    private String tags;
}
