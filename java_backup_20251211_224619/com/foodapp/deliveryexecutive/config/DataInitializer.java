/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.boot.CommandLineRunner
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.context.annotation.Profile
 *  org.springframework.security.crypto.password.PasswordEncoder
 */
package com.foodapp.deliveryexecutive.config;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.admin.repository.AdminRepository;
import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.homemaker.repository.HomemakerProfileRepository;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.entity.UserProfile;
import com.foodapp.deliveryexecutive.user.repository.UserProfileRepository;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    @Profile(value={"dev"})
    CommandLineRunner initDatabase(UserRepository userRepository, UserProfileRepository userProfileRepository, HomeMakerRepository homeMakerRepository, HomemakerProfileRepository homemakerProfileRepository, DeliveryExecutiveRepository deliveryExecutiveRepository, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User savedUser;
            System.out.println("=== Initializing Test Data ===");
            if (userRepository.findByMobile("9876543210").isEmpty()) {
                User user = new User();
                user.setName("Rahul Sharma");
                user.setMobile("9876543210");
                user.setPassword(passwordEncoder.encode((CharSequence)"test123"));
                user.setEmail("rahul.sharma@example.com");
                user.setAddress("123, MG Road, Andheri West, Mumbai");
                user.setRole(Actor.Role.USER);
                savedUser = (User)userRepository.save(user);
                System.out.println("\u2713 Created test Customer: Rahul Sharma (9876543210)");
            } else {
                savedUser = userRepository.findByMobile("9876543210").get();
            }
            if (userProfileRepository.findByUserId(savedUser.getId()).isEmpty()) {
                UserProfile profile = new UserProfile();
                profile.setUserId(savedUser.getId());
                profile.setFirstName("Rahul");
                profile.setLastName("Sharma");
                profile.setEmail("rahul.sharma@example.com");
                profile.setPhoneNumber("9876543210");
                profile.setCity("Mumbai");
                profile.setState("Maharashtra");
                profile.setZipCode("400053");
                profile.setPrimaryAddress("123, MG Road, Andheri West, Mumbai");
                profile.setPreferredPaymentMethod("UPI");
                userProfileRepository.save(profile);
                System.out.println("\u2713 Created test User Profile for Rahul Sharma");
            }
            if (homeMakerRepository.findByMobile("9876543211").isEmpty()) {
                HomeMaker homeMaker = new HomeMaker();
                homeMaker.setName("Priya Patel");
                homeMaker.setMobile("9876543211");
                homeMaker.setPassword(passwordEncoder.encode((CharSequence)"test123"));
                homeMaker.setAddress("45, Shanti Nagar, Borivali East, Mumbai");
                homeMaker.setRole(Actor.Role.HOMEMAKER);
                homeMaker.setApprovalStatus(HomeMaker.ApprovalStatus.APPROVED);
                homeMakerRepository.save(homeMaker);
                System.out.println("\u2713 Created test Home Chef: Priya Patel (9876543211) [APPROVED]");
            }
            if (deliveryExecutiveRepository.findByMobile("9876543212").isEmpty()) {
                DeliveryExecutive partner = new DeliveryExecutive();
                partner.setName("Vikram Singh");
                partner.setMobile("9876543212");
                partner.setPassword(passwordEncoder.encode((CharSequence)"test123"));
                partner.setAadharNo("123456789012");
                partner.setLicenseNo("MH01-2020-0012345");
                partner.setRole(Actor.Role.DELIVERYEXECUTIVE);
                partner.setOnline(true);
                partner.setApprovalStatus(DeliveryExecutive.ApprovalStatus.APPROVED);
                deliveryExecutiveRepository.save(partner);
                System.out.println("\u2713 Created test Delivery Partner: Vikram Singh (9876543212) [APPROVED]");
            }
            if (adminRepository.findByUsername("admin").isEmpty()) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setEmail("admin@homebites.com");
                admin.setPassword(passwordEncoder.encode((CharSequence)"admin123"));
                admin.setFullName("System Administrator");
                admin.setPhoneNumber("9876543213");
                admin.setAdminRole(Admin.AdminRole.SUPER_ADMIN);
                admin.setStatus(Admin.AdminStatus.ACTIVE);
                admin.setCanManageUsers(true);
                admin.setCanManagePayments(true);
                admin.setCanManageDisputes(true);
                admin.setCanViewAnalytics(true);
                admin.setCanManageAdmins(true);
                adminRepository.save(admin);
                System.out.println("\u2713 Created test Admin: admin (admin@homebites.com)");
            }
            System.out.println("=== Test Data Initialization Complete ===");
            System.out.println();
            System.out.println("Test Credentials:");
            System.out.println("  Customer:         9876543210 / test123");
            System.out.println("  Home Chef:        9876543211 / test123");
            System.out.println("  Delivery Partner: 9876543212 / test123");
            System.out.println("  Admin:            admin / admin123");
        };
    }
}
