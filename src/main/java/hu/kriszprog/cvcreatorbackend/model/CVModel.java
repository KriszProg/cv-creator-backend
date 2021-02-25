package hu.kriszprog.cvcreatorbackend.model;

import hu.kriszprog.cvcreatorbackend.entity.Candidate;
import hu.kriszprog.cvcreatorbackend.entity.Contact;
import hu.kriszprog.cvcreatorbackend.entity.SelfDefinition;
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

}
