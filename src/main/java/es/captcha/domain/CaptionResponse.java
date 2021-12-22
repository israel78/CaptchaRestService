package es.captcha.domain;
import lombok.Data;

@Data
public class CaptionResponse {
    Double confidence;
    String text;
}
