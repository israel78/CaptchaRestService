package es.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
