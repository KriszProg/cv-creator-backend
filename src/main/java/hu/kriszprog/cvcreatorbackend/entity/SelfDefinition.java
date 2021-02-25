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
public class SelfDefinition {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "text")
    private String selfDefinition;

    @OneToMany(mappedBy = "selfDefinition", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Transient
    @JsonIgnore
    private List<CV> cvList;

}
