package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.MentorOpinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MentorOpinionRepository extends JpaRepository<MentorOpinion, Long> {
    MentorOpinion getMentorOpinionById(Long id);

    @Query("SELECT mo FROM MentorOpinion mo WHERE mo.mentorOpinion = :mentorOpinion")
    MentorOpinion getMentorOpinionIfExist(@Param("mentorOpinion") String mentorOpinion);

}
