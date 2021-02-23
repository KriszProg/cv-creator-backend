package hu.kriszprog.cvcreatorbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CVModel {

    private CVIdentifiersModel cvIdentifiers;
    private CandidateModel candidate;
    private ContactModel contact;
    private SelfDefinitionModel selfDefinition;

}
