package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.CVTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CVTitleRepository extends JpaRepository<CVTitle, Long> {
    CVTitle getCVTitleById(Long id);
}
