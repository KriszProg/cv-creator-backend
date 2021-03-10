package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    @Query("SELECT q FROM Qualification q " +
            "WHERE q.name = :name " +
            "AND q.degree = :degree " +
            "AND q.yearFrom = :yearFrom " +
            "AND q.yearTo = :yearTo " +
            "AND q.school = :school " +
            "AND q.cityOfSchool = :cityOfSchool")
    Qualification getQualificationIfExist(
            @Param("name") String name,
            @Param("degree") String degree,
            @Param("yearFrom") Integer yearFrom,
            @Param("yearTo") Integer yearTo,
            @Param("school") String school,
            @Param("cityOfSchool") String cityOfSchool
    );
}
