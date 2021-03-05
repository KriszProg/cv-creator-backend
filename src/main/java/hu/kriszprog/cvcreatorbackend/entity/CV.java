package hu.kriszprog.cvcreatorbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @ManyToOne
    private PersonalInfo persInf1;

    @ManyToOne
    private PersonalInfo persInf2;

    @ManyToOne
    private PersonalInfo persInf3;

}
