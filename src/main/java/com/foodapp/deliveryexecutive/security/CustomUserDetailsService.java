/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.security.core.userdetails.UserDetails
 *  org.springframework.security.core.userdetails.UserDetailsService
 *  org.springframework.security.core.userdetails.UsernameNotFoundException
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.security;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.admin.repository.AdminRepository;
import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.security.UserPrincipal;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService
implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeMakerRepository homeMakerRepository;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Loading user by username: {}", username);
        UserPrincipal userPrincipal = this.findUserByMobile(username);
        if (userPrincipal == null) {
            logger.error("User not found with mobile: {}", username);
            throw new UsernameNotFoundException("User not found with mobile: " + username);
        }
        logger.debug("User found with role: {}", userPrincipal.getRole());
        return userPrincipal;
    }

    @Transactional
    public UserDetails loadUserById(Long id, Actor.Role role) {
        logger.debug("Loading user by id: {} and role: {}", id, role);
        UserPrincipal userPrincipal = null;
        switch (role) {
            case USER: {
                User user = (User)this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
                userPrincipal = UserPrincipal.create(user);
                break;
            }
            case HOMEMAKER: {
                HomeMaker homeMaker = (HomeMaker)this.homeMakerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("HomeMaker not found with id: " + id));
                userPrincipal = UserPrincipal.create(homeMaker);
                break;
            }
            case DELIVERYEXECUTIVE: {
                DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Delivery Executive not found with id: " + id));
                userPrincipal = UserPrincipal.create(executive);
                break;
            }
            case ADMIN: {
                Admin admin = (Admin)this.adminRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Admin not found with id: " + id));
                userPrincipal = UserPrincipal.create(admin);
                break;
            }
            default: {
                throw new UsernameNotFoundException("User type not supported: " + String.valueOf(role));
            }
        }
        return userPrincipal;
    }

    private UserPrincipal findUserByMobile(String mobile) {
        Optional<User> user = this.userRepository.findByMobile(mobile);
        if (user.isPresent()) {
            return UserPrincipal.create(user.get());
        }
        Optional<HomeMaker> homeMaker = this.homeMakerRepository.findByMobile(mobile);
        if (homeMaker.isPresent()) {
            HomeMaker hm = homeMaker.get();
            if (hm.getApprovalStatus() == HomeMaker.ApprovalStatus.PENDING) {
                logger.warn("HomeMaker {} is pending approval", mobile);
                throw new UsernameNotFoundException("Your account is pending admin approval. Please wait for approval.");
            }
            if (hm.getApprovalStatus() == HomeMaker.ApprovalStatus.REJECTED) {
                logger.warn("HomeMaker {} was rejected", mobile);
                throw new UsernameNotFoundException("Your account application was rejected. Reason: " + (hm.getRejectionReason() != null ? hm.getRejectionReason() : "Not specified"));
            }
            return UserPrincipal.create(hm);
        }
        Optional<DeliveryExecutive> executive = this.deliveryExecutiveRepository.findByMobile(mobile);
        if (executive.isPresent()) {
            DeliveryExecutive de = executive.get();
            if (de.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.PENDING) {
                logger.warn("DeliveryExecutive {} is pending approval", mobile);
                throw new UsernameNotFoundException("Your account is pending admin approval. Please wait for approval.");
            }
            if (de.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.REJECTED) {
                logger.warn("DeliveryExecutive {} was rejected", mobile);
                throw new UsernameNotFoundException("Your account application was rejected. Reason: " + (de.getRejectionReason() != null ? de.getRejectionReason() : "Not specified"));
            }
            return UserPrincipal.create(de);
        }
        Optional<Admin> adminByUsername = this.adminRepository.findByUsername(mobile);
        if (adminByUsername.isPresent()) {
            return UserPrincipal.create(adminByUsername.get());
        }
        Optional<Admin> admin = this.adminRepository.findByPhoneNumber(mobile);
        if (admin.isPresent()) {
            return UserPrincipal.create(admin.get());
        }
        return null;
    }
}
