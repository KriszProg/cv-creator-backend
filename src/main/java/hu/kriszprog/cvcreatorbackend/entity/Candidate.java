package hu.kriszprog.cvcreatorbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Candidate {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String role;

    @OneToMany(mappedBy = "candidate", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Transient
    private List<CV> cvList;

}
