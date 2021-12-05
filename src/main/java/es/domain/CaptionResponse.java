package es.domain;

import lombok.Data;

@Data
public class CaptionResponse {
    Double confidence;
    String text;
}
