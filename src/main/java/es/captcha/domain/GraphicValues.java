package es.captcha.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "GRAPHIC_VALUES")
public class GraphicValues {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "x_value", nullable = false)
    private String xValue;
    @Column(name = "y_value_one", nullable = false)
    private String yValue1;
    @Column(name = "y_value_two", nullable = false)
    private String yValue2;
    @Column(name = "y_value_tree", nullable = false)
    private String yValue3;

}
