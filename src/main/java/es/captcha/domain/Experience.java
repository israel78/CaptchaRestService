package es.captcha.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode
@ToString
@Entity (name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "experience", cascade=CascadeType.ALL)
    @JsonManagedReference
    private Set <JobFunction> JobFunctions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "experience", cascade=CascadeType.ALL)
    @JsonManagedReference
    private Set <Skill> skills;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "experience", cascade=CascadeType.ALL)
    @JsonManagedReference
    private Set <DeveloperTool> developerTools;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
