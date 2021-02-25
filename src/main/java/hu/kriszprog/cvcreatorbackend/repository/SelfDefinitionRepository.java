package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.SelfDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SelfDefinitionRepository extends JpaRepository<SelfDefinition, Long> {
    SelfDefinition getSelfDefinitionById(Long id);

    @Query("SELECT sd FROM SelfDefinition sd WHERE sd.selfDefinition = :selfDefinition")
    SelfDefinition getSelfDefinitionIfExist(@Param("selfDefinition") String selfDefinition);

}
