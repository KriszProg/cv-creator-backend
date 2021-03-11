package hu.kriszprog.cvcreatorbackend.model;

import hu.kriszprog.cvcreatorbackend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<Project> projectList;
    private List<Job> jobList;
    private List<Qualification> qualificationList;
    private List<Language> languageList;

}
