package es.captcha.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Items {
    String type;
    Map<Integer,String> values;
}
