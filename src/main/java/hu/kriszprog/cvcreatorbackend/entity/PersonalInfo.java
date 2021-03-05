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
public class PersonalInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PersonalInfoType personalInfoType;

    private String sectionTitle;

    @Column(columnDefinition = "text")
    private String text;

    @OneToMany(mappedBy = "personalInfo", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Transient
    @JsonIgnore
    private List<CV> cvList;

}
