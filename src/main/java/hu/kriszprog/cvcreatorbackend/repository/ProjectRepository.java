package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p " +
            "WHERE p.title = :title " +
            "AND p.url1 = :url1 " +
            "AND p.url2 = :url2 " +
            "AND p.description = :description")
    Project getProjectIfExist(
            @Param("title") String title,
            @Param("url1") String url1,
            @Param("url2") String url2,
            @Param("description") String description
    );
}
