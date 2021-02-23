package hu.kriszprog.cvcreatorbackend.entity;

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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Transient
    private List<CV> cvList;

}
