package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j " +
            "WHERE j.role = :role " +
            "AND j.yearFrom = :yearFrom " +
            "AND j.yearTo = :yearTo " +
            "AND j.company = :company")
    Job getJobIfExist(
            @Param("role") String role,
            @Param("yearFrom") Integer yearFrom,
            @Param("yearTo") Integer yearTo,
            @Param("company") String company
    );
}
