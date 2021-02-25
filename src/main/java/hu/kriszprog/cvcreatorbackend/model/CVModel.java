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

    private Candidate candidate;
    private CVIdentifiersModel cvIdentifiers;
    private Contact contact;
    private SelfDefinition selfDefinition;
    private Strength strength;
    private MentorOpinion mentorOpinion;

}
