package es.captcha.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "skill")
public class Skill {
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "experience_id")
    private Experience experience;
    @EqualsAndHashCode.Include
    @Column(name ="skill_name")
    private String skillName;


}