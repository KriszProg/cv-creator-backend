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
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    ImageType imageType;

    String url;

    @OneToMany(mappedBy = "image", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Transient
    @JsonIgnore
    private List<CV> cvList;

}
