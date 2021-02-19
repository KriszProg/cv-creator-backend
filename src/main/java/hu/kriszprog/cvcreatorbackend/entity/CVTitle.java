package hu.kriszprog.cvcreatorbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CVTitle {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Builder.Default
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());
}
