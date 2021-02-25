package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact getContactById(Long id);

    @Query("SELECT c FROM Contact c " +
            "WHERE c.email = :email " +
            "AND c.phoneNr = :phoneNr " +
            "AND c.linkedInProfile = :linkedInProfile")
    Contact getContactIfExist(@Param("email") String email,
                              @Param("phoneNr") String phoneNr,
                              @Param("linkedInProfile") String linkedInProfile);
}
