package hu.kriszprog.cvcreatorbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactModel {

    private Long id;
    private String email;
    private String phoneNr;
    private String linkedInProfile;

}
