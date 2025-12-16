/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.user.service;

import com.foodapp.deliveryexecutive.user.dto.UserPreferenceDTO;
import com.foodapp.deliveryexecutive.user.entity.UserPreference;
import com.foodapp.deliveryexecutive.user.repository.UserPreferenceRepository;
import java.util.ArrayList;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserPreferenceService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(UserPreferenceService.class);
    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    public UserPreferenceDTO getOrCreatePreferences(Long userId) {
        log.debug("Fetching preferences for user: {}", (Object)userId);
        UserPreference preference = this.userPreferenceRepository.findByUserId(userId).orElseGet(() -> this.createDefaultPreferences(userId));
        return this.convertToDTO(preference);
    }

    private UserPreference createDefaultPreferences(Long userId) {
        log.info("Creating default preferences for user: {}", (Object)userId);
        UserPreference preference = new UserPreference();
        preference.setUserId(userId);
        preference.setVegetarianPreference(false);
        preference.setSpicyPreference(true);
        preference.setDairyFreePreference(false);
        preference.setGlutenFreePreference(false);
        preference.setPreferredCuisines(new ArrayList<String>());
        preference.setAllergies(new ArrayList<String>());
        preference.setReceiveOrderReminders(true);
        preference.setReceiveNewHomemakerNotifications(true);
        preference.setReceiveDealsAndOffers(true);
        return (UserPreference)this.userPreferenceRepository.save(preference);
    }

    public UserPreferenceDTO updatePreferences(Long userId, UserPreferenceDTO preferenceDTO) {
        log.info("Updating preferences for user: {}", (Object)userId);
        UserPreference preference = this.userPreferenceRepository.findByUserId(userId).orElseGet(() -> this.createDefaultPreferences(userId));
        if (preferenceDTO.getVegetarianPreference() != null) {
            preference.setVegetarianPreference(preferenceDTO.getVegetarianPreference());
        }
        if (preferenceDTO.getSpicyPreference() != null) {
            preference.setSpicyPreference(preferenceDTO.getSpicyPreference());
        }
        if (preferenceDTO.getDairyFreePreference() != null) {
            preference.setDairyFreePreference(preferenceDTO.getDairyFreePreference());
        }
        if (preferenceDTO.getGlutenFreePreference() != null) {
            preference.setGlutenFreePreference(preferenceDTO.getGlutenFreePreference());
        }
        if (preferenceDTO.getPreferredCuisines() != null) {
            preference.setPreferredCuisines(preferenceDTO.getPreferredCuisines());
        }
        if (preferenceDTO.getAllergies() != null) {
            preference.setAllergies(preferenceDTO.getAllergies());
        }
        if (preferenceDTO.getReceiveOrderReminders() != null) {
            preference.setReceiveOrderReminders(preferenceDTO.getReceiveOrderReminders());
        }
        if (preferenceDTO.getReceiveNewHomemakerNotifications() != null) {
            preference.setReceiveNewHomemakerNotifications(preferenceDTO.getReceiveNewHomemakerNotifications());
        }
        if (preferenceDTO.getReceiveDealsAndOffers() != null) {
            preference.setReceiveDealsAndOffers(preferenceDTO.getReceiveDealsAndOffers());
        }
        UserPreference updatedPreference = (UserPreference)this.userPreferenceRepository.save(preference);
        log.info("Preferences updated successfully");
        return this.convertToDTO(updatedPreference);
    }

    public UserPreferenceDTO addAllergy(Long userId, String allergy) {
        log.info("Adding allergy for user: {} - {}", (Object)userId, (Object)allergy);
        UserPreference preference = this.userPreferenceRepository.findByUserId(userId).orElseGet(() -> this.createDefaultPreferences(userId));
        if (preference.getAllergies() == null) {
            preference.setAllergies(new ArrayList<String>());
        }
        if (!preference.getAllergies().contains(allergy)) {
            preference.getAllergies().add(allergy);
        }
        UserPreference updatedPreference = (UserPreference)this.userPreferenceRepository.save(preference);
        log.info("Allergy added successfully");
        return this.convertToDTO(updatedPreference);
    }

    public UserPreferenceDTO removeAllergy(Long userId, String allergy) {
        log.info("Removing allergy for user: {} - {}", (Object)userId, (Object)allergy);
        UserPreference preference = this.userPreferenceRepository.findByUserId(userId).orElseGet(() -> this.createDefaultPreferences(userId));
        if (preference.getAllergies() != null) {
            preference.getAllergies().remove(allergy);
        }
        UserPreference updatedPreference = (UserPreference)this.userPreferenceRepository.save(preference);
        log.info("Allergy removed successfully");
        return this.convertToDTO(updatedPreference);
    }

    private UserPreferenceDTO convertToDTO(UserPreference preference) {
        UserPreferenceDTO dto = new UserPreferenceDTO();
        dto.setId(preference.getId());
        dto.setUserId(preference.getUserId());
        dto.setVegetarianPreference(preference.getVegetarianPreference());
        dto.setSpicyPreference(preference.getSpicyPreference());
        dto.setDairyFreePreference(preference.getDairyFreePreference());
        dto.setGlutenFreePreference(preference.getGlutenFreePreference());
        dto.setPreferredCuisines(preference.getPreferredCuisines());
        dto.setAllergies(preference.getAllergies());
        dto.setIsNutAllergy(preference.getIsNutAllergy());
        dto.setIsDairyAllergy(preference.getIsDairyAllergy());
        dto.setIsSeafoodAllergy(preference.getIsSeafoodAllergy());
        dto.setIsPeanutAllergy(preference.getIsPeanutAllergy());
        dto.setDietaryRestrictions(preference.getDietaryRestrictions());
        dto.setReceiveOrderReminders(preference.getReceiveOrderReminders());
        dto.setReceiveNewHomemakerNotifications(preference.getReceiveNewHomemakerNotifications());
        dto.setReceiveDealsAndOffers(preference.getReceiveDealsAndOffers());
        dto.setPreferredNotificationTime(preference.getPreferredNotificationTime());
        dto.setCreatedAt(preference.getCreatedAt());
        dto.setUpdatedAt(preference.getUpdatedAt());
        return dto;
    }
}
