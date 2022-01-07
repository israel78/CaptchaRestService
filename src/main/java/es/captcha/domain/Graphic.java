package es.captcha.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "GRAPHIC")
public class Graphic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "subtitle", nullable = false)
    private String subTitle;

    @Column(name = "line_graphic_name_one")
    private String lineGraphicNameOne;

    @Column(name = "line_graphic_name_two")
    private String lineGraphicNameTwo;

    @Column(name = "line_graphic_name_tree")
    private String lineGraphicNameTree;

    @JoinColumn(name = "graphic_id")
    @OneToMany(fetch = FetchType.EAGER)
    private List<GraphicValues> GraphicValuesList;

}
