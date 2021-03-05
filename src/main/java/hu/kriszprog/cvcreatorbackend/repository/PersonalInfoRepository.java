package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.PersonalInfo;
import hu.kriszprog.cvcreatorbackend.entity.PersonalInfoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    PersonalInfo getPersonalInfoById(Long id);

    @Query("SELECT pi FROM PersonalInfo pi " +
            "WHERE pi.personalInfoType = :personalInfoType " +
            "AND pi.sectionTitle = :sectionTitle " +
            "AND pi.text = :text")
    PersonalInfo getPersonalInfoIfExist(
            @Param("personalInfoType") PersonalInfoType personalInfoType,
            @Param("sectionTitle") String sectionTitle,
            @Param("text") String text
    );
}
