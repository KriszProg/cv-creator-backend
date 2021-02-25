package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact getContactById(Long id);
}
