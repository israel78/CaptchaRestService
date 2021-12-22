package es.captcha.domain;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class CaptchaSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NUM_CHARACT")
    private int numCharact;
    @Column(name = "ATTEMPS")
    private int attemps;
    @Column(name = "ALFA")
    private boolean alfa;


}
