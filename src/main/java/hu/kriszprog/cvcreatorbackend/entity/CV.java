package hu.kriszprog.cvcreatorbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CV {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Builder.Default
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    private Image profilePhoto;

    @ManyToOne
    private Image background;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private Contact contact;

    @ManyToMany
    private List<PersonalInfo> personalInfoList;

    @ManyToMany
    private List<Project> projectList;

    @ManyToMany
    private List<Job> jobList;

    @ManyToMany
    private List<Qualification> qualificationList;

    @ManyToMany
    private List<Language> languageList;
}
