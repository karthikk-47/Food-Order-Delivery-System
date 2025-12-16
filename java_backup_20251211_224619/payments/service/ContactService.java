/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.payments.service;

import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.payments.dto.CreateContactRequest;
import com.foodapp.deliveryexecutive.payments.dto.CreateContactResponse;
import com.foodapp.deliveryexecutive.payments.entity.Contact;
import com.foodapp.deliveryexecutive.payments.repository.ContactRepository;
import com.foodapp.deliveryexecutive.payments.service.PaymentsApi;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {
    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private PaymentsApi paymentsApi;

    @Transactional
    public CreateContactResponse createAndSaveContact(CreateContactRequest request) throws Exception {
        Optional<Contact> existing;
        if (this.contactRepository.existsByEmail(request.getEmail()) && (existing = this.contactRepository.findByEmail(request.getEmail())).isPresent()) {
            logger.info("Contact already exists with email: {}", (Object)request.getEmail());
            return this.mapToResponse(existing.get());
        }
        CreateContactResponse response = this.paymentsApi.createContact(request);
        if (response != null && response.getId() != null) {
            Contact contact = new Contact();
            contact.setId(response.getId());
            contact.setEntity(response.getEntity());
            contact.setName(response.getName());
            contact.setContact(response.getContact());
            contact.setEmail(response.getEmail());
            contact.setType(response.getType());
            contact.setReferenceId(response.getReference_id());
            contact.setBatchId(response.getBatch_id());
            contact.setActive(response.isActive());
            contact.setNotes(response.getNotes());
            contact.setCreated_at(response.getCreated_at());
            this.contactRepository.save(contact);
            logger.info("Contact saved successfully with ID: {}", (Object)contact.getId());
        }
        return response;
    }

    public Optional<Contact> getContactById(String id) {
        return this.contactRepository.findById(id);
    }

    public Optional<Contact> getContactByEmail(String email) {
        return this.contactRepository.findByEmail(email);
    }

    public Optional<Contact> getContactByPhone(String phone) {
        return this.contactRepository.findByContact(phone);
    }

    public List<Contact> getContactsByType(String type) {
        return this.contactRepository.findByType(type);
    }

    public List<Contact> getActiveContacts() {
        return this.contactRepository.findByActive(true);
    }

    public List<Contact> getAllContacts() {
        return this.contactRepository.findAll();
    }

    @Transactional
    public void deactivateContact(String id) {
        Contact contact = (Contact)this.contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + id));
        contact.setActive(false);
        this.contactRepository.save(contact);
        logger.info("Contact deactivated: {}", (Object)id);
    }

    private CreateContactResponse mapToResponse(Contact contact) {
        CreateContactResponse response = new CreateContactResponse();
        response.setId(contact.getId());
        response.setEntity(contact.getEntity());
        response.setName(contact.getName());
        response.setContact(contact.getContact());
        response.setEmail(contact.getEmail());
        response.setType(contact.getType());
        response.setReference_id(contact.getReferenceId());
        response.setBatch_id(contact.getBatchId());
        response.setActive(contact.isActive());
        response.setNotes(contact.getNotes());
        response.setCreated_at(contact.getCreated_at());
        return response;
    }
}
