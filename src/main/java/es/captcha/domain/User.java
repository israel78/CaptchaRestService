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

    @OneToOne
    @JoinColumn(name="graphic_id")
    private Graphic graphic;

    @Column(name = "important_phrase")
    private String importantPhrase;

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
