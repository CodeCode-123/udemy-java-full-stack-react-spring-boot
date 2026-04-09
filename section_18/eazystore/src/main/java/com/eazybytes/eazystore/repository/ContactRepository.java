package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Optional
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
