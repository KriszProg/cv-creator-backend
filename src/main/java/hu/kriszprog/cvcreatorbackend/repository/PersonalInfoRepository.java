package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    @Query("SELECT pi FROM PersonalInfo pi " +
            "WHERE pi.sectionTitle = :sectionTitle " +
            "AND pi.text = :text")
    PersonalInfo getPersonalInfoIfExist(
            @Param("sectionTitle") String sectionTitle,
            @Param("text") String text
    );
}