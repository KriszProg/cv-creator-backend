package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.SelfDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelfDefinitionRepository extends JpaRepository<SelfDefinition, Long> {
    SelfDefinition getSelfDefinitionById(Long id);
}
