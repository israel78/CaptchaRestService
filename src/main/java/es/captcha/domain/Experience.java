package es.captcha.domain;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
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

    @Column(name = "title")
    private String title;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "url_imageExp")
    private String urlImageExp;

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
