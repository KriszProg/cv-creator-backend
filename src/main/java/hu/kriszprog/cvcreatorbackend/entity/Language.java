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
public class Language {

    @Id
    @GeneratedValue
    private Long id;

    private String language;

    private String level;

    @ManyToMany(mappedBy = "languageList", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    @Transient
    private List<CV> cvList;
}
