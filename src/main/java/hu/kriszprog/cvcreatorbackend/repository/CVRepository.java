package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CVRepository extends JpaRepository<CV, Long> {
    CV getCVById(Long id);

    @Query("SELECT background.id FROM CV WHERE id = :CVId")
    Long getBackgroundIdByCVId(@Param("CVId") Long id);

    @Query("SELECT profilePhoto.id FROM CV WHERE id = :CVId")
    Long getProfilePhotoIdByCVId(@Param("CVId") Long id);

    @Query("SELECT candidate.id FROM CV WHERE id = :CVId")
    Long getCandidateIdByCVId(@Param("CVId") Long id);

    @Query("SELECT contact.id FROM CV WHERE id = :CVId")
    Long getContactIdByCVId(@Param("CVId") Long id);

    @Query("SELECT persInf1.id FROM CV WHERE id = :CVId")
    Long getPersInf1IdByCVId(@Param("CVId") Long id);

    @Query("SELECT persInf2.id FROM CV WHERE id = :CVId")
    Long getPersInf2IdByCVId(@Param("CVId") Long id);

    @Query("SELECT persInf3.id FROM CV WHERE id = :CVId")
    Long getPersInf3IdByCVId(@Param("CVId") Long id);
}
