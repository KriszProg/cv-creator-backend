package hu.kriszprog.cvcreatorbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CVTitleModel {

    private Long id;
    private String title;
    private String creationDate;

}
