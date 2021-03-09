package hu.kriszprog.cvcreatorbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String url1;

    private String url2;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToMany(mappedBy = "projectList", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    @Transient
    private List<CV> cvList;
}
