package es.captcha.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String firstName;

    @Column(name = "PASS")
    private String passw;

    @Column(name = "important_phrase")
    private String importantPhrase;

    @Column(name = "line_graphic_name_one")
    private String lineGraphicNameOne;

    @Column(name = "line_graphic_name_two")
    private String lineGraphicNameTwo;

    @Column(name = "line_graphic_name_tree")
    private String lineGraphicNameTree;

    @JoinColumn(name = "user_id")
    @OneToMany(fetch = FetchType.EAGER)
    private List<GraphicValues> GraphicValuesList;

    @Transient
    private String captcha;

    public User(String firstName, String passw) {
        this.firstName = firstName;
        this.passw = passw;
        this.captcha = "";

    }
    public User() {

    }
}
