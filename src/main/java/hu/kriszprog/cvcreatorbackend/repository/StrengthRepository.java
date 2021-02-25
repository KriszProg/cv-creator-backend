package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.Strength;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StrengthRepository extends JpaRepository<Strength, Long> {
    Strength getStrengthById(Long id);

    @Query("SELECT s FROM Strength s WHERE s.strength = :strength")
    Strength getStrengthIfExist(@Param("strength") String strength);

}
