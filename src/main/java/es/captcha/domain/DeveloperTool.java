package es.captcha.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "developer_tool")
public class DeveloperTool {
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
    @Column(name ="Dev_tool_name")
    private String devToolName;
}