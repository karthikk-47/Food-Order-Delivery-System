package com.foodapp.deliveryexecutive.config;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.admin.repository.AdminRepository;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class TestAccountInitializer implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(TestAccountInitializer.class);
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeMakerRepository homeMakerRepository;
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Value("${test.user.mobile:}")
    private String testUserMobile;
    @Value("${test.user.password:}")
    private String testUserPassword;
    @Value("${test.user.email:}")
    private String testUserEmail;
    
    @Value("${test.homemaker.mobile:}")
    private String testHomemakerMobile;
    @Value("${test.homemaker.password:}")
    private String testHomemakerPassword;
    
    @Value("${test.executive.mobile:}")
    private String testExecutiveMobile;
    @Value("${test.executive.password:}")
    private String testExecutivePassword;
    
    @Value("${test.admin.phone:}")
    private String testAdminPhone;
    @Value("${test.admin.password:}")
    private String testAdminPassword;

    @Override
    public void run(String... args) {
        log.info("Initializing test accounts from application.properties...");
        createTestUser();
        createTestHomemaker();
        createTestExecutive();
        createTestAdmin();
        log.info("Test account initialization complete.");
    }

    private void createTestUser() {
        if (testUserMobile == null || testUserMobile.isEmpty()) return;
        if (userRepository.findByMobile(testUserMobile).isPresent()) {
            log.info("Test user already exists: {}", testUserMobile);
            return;
        }
        User user = new User();
        user.setName("Test User");
        user.setMobile(testUserMobile);
        user.setPassword(passwordEncoder.encode(testUserPassword));
        user.setEmail(testUserEmail);
        user.setAddress("Test Address, Bangalore");
        userRepository.save(user);
        log.info("Created test user: {}", testUserMobile);
    }
    
    private void createTestHomemaker() {
        if (testHomemakerMobile == null || testHomemakerMobile.isEmpty()) return;
        if (homeMakerRepository.findByMobile(testHomemakerMobile).isPresent()) {
            log.info("Test homemaker already exists: {}", testHomemakerMobile);
            return;
        }
        HomeMaker hm = new HomeMaker();
        hm.setName("Test Homemaker");
        hm.setMobile(testHomemakerMobile);
        hm.setPassword(passwordEncoder.encode(testHomemakerPassword));
        hm.setAddress("Test Kitchen, Bangalore");
        hm.setApprovalStatus(HomeMaker.ApprovalStatus.APPROVED);
        homeMakerRepository.save(hm);
        log.info("Created test homemaker: {}", testHomemakerMobile);
    }
    
    private void createTestExecutive() {
        if (testExecutiveMobile == null || testExecutiveMobile.isEmpty()) return;
        if (deliveryExecutiveRepository.findByMobile(testExecutiveMobile).isPresent()) {
            log.info("Test executive already exists: {}", testExecutiveMobile);
            return;
        }
        DeliveryExecutive de = new DeliveryExecutive();
        de.setName("Test Executive");
        de.setMobile(testExecutiveMobile);
        de.setPassword(passwordEncoder.encode(testExecutivePassword));
        de.setAadharNo("123456789012");
        de.setLicenseNo("DL0000000001");
        de.setApprovalStatus(DeliveryExecutive.ApprovalStatus.APPROVED);
        de.setOnline(true);
        deliveryExecutiveRepository.save(de);
        log.info("Created test executive: {}", testExecutiveMobile);
    }
    
    private void createTestAdmin() {
        if (testAdminPhone == null || testAdminPhone.isEmpty()) return;
        if (adminRepository.findByPhoneNumber(testAdminPhone).isPresent()) {
            log.info("Test admin already exists: {}", testAdminPhone);
            return;
        }
        Admin admin = new Admin();
        admin.setUsername("testadmin");
        admin.setEmail("testadmin@homebites.com");
        admin.setPhoneNumber(testAdminPhone);
        admin.setFullName("Test Administrator");
        admin.setPassword(passwordEncoder.encode(testAdminPassword));
        admin.setAdminRole(Admin.AdminRole.SUPER_ADMIN);
        admin.setStatus(Admin.AdminStatus.ACTIVE);
        admin.setCanManageUsers(true);
        admin.setCanManagePayments(true);
        admin.setCanManageDisputes(true);
        admin.setCanViewAnalytics(true);
        admin.setCanManageAdmins(true);
        adminRepository.save(admin);
        log.info("Created test admin: {}", testAdminPhone);
    }
}
