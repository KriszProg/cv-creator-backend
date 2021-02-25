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
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String phoneNr;

    private String linkedInProfile;

    @OneToMany(mappedBy = "contact", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    private List<CV> cvList;

}
