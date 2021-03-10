package hu.kriszprog.cvcreatorbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Qualification {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String degree;

    private Integer yearFrom;

    private Integer yearTo;

    private String school;

    private String cityOfSchool;

    @ManyToMany(mappedBy = "qualificationList", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    @Transient
    private List<CV> cvList;
}
