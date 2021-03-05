package hu.kriszprog.cvcreatorbackend.model;

import hu.kriszprog.cvcreatorbackend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CVModel {

    private Image background;
    private Image profilePhoto;
    private Candidate candidate;
    private CVIdentifiersModel cvIdentifiers;
    private Contact contact;
    private PersonalInfo persInf1;
    private PersonalInfo persInf2;
    private PersonalInfo persInf3;

}
