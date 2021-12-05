package es.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DescriptionResponse {
    private ArrayList<CaptionResponse> captionResponseList;
    private String tags;


}
