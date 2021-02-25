package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CVRepository extends JpaRepository<CV, Long> {
    CV getCVById(Long id);

    @Query("SELECT candidate.id FROM CV WHERE id = :CVId")
    Long getCandidateIdByCVId(@Param("CVId") Long id);

    @Query("SELECT contact.id FROM CV WHERE id = :CVId")
    Long getContactIdByCVId(@Param("CVId") Long id);

    @Query("SELECT selfDefinition.id FROM CV WHERE id = :CVId")
    Long getSelfDefinitionIdByCVId(@Param("CVId") Long id);
}
