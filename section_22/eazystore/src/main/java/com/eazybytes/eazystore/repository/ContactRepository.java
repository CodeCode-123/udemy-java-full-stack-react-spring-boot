package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Optional
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // the method name findByStatus is the same as NamedQuery(name = "Contact.findByStatus")
    List<Contact> findByStatus(String status);

    @Query(name = "Contact.findByStatus")
    List<Contact> fetchByStatus(String status);

    List<Contact> findByStatusWithNativeQuery(String status);
}
