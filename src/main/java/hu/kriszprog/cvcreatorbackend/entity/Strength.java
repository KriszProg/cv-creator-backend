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
public class Strength {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "text")
    private String strength;

    @OneToMany(mappedBy = "strength", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Transient
    @JsonIgnore
    private List<CV> cvList;

}
