package es.captcha.domain;
import lombok.Data;
@Data
public class FaceRectangleResponse {
   private int top;
   private int left;
   private int width;
   private int height;
}
