package com.foodapp.deliveryexecutive.payments.repository;

import com.foodapp.deliveryexecutive.payments.entity.Contact;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository
extends JpaRepository<Contact, String> {
    public Optional<Contact> findByEmail(String var1);

    public Optional<Contact> findByContact(String var1);

    public Optional<Contact> findByReferenceId(String var1);

    public List<Contact> findByType(String var1);

    public List<Contact> findByActive(boolean var1);

    @Query(value="SELECT c FROM Contact c WHERE c.email = :email AND c.active = true")
    public Optional<Contact> findActiveByEmail(@Param(value="email") String var1);

    @Query(value="SELECT c FROM Contact c WHERE c.contact = :contact AND c.active = true")
    public Optional<Contact> findActiveByContact(@Param(value="contact") String var1);

    @Query(value="SELECT c FROM Contact c WHERE c.type = :type AND c.active = true")
    public List<Contact> findActiveByType(@Param(value="type") String var1);

    public boolean existsByEmail(String var1);

    public boolean existsByContact(String var1);
}
