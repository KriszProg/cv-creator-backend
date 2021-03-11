package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LanguageRepository extends JpaRepository<Language, Long> {
    @Query("SELECT l FROM Language l " +
            "WHERE l.language = :language " +
            "AND l.level = :level")
    Language getLanguageIfExist(
            @Param("language") String language,
            @Param("level") String level
    );
}
