/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.boot.CommandLineRunner
 *  org.springframework.context.annotation.Profile
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.stereotype.Component
 */
package com.foodapp.deliveryexecutive.config;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.admin.repository.AdminRepository;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile(value={"seed"})
public class DataSeeder
implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);
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
    private final Random random = new Random();
    private final String[] firstNames = new String[]{"Aarav", "Vivaan", "Aditya", "Vihaan", "Arjun", "Sai", "Reyansh", "Ayaan", "Krishna", "Ishaan", "Ananya", "Diya", "Myra", "Aadhya", "Pihu", "Saanvi", "Aanya", "Isha", "Navya", "Avni", "Rahul", "Amit", "Priya", "Neha", "Vikram", "Sanjay", "Pooja", "Riya", "Karan", "Rohit", "Sneha", "Anjali", "Deepika", "Kavya", "Meera", "Nisha", "Pallavi", "Rashmi", "Shweta", "Tanvi"};
    private final String[] lastNames = new String[]{"Sharma", "Verma", "Patel", "Kumar", "Singh", "Gupta", "Reddy", "Rao", "Iyer", "Nair", "Das", "Roy", "Joshi", "Kulkarni", "Desai", "Mehta", "Shah", "Chopra", "Malhotra", "Kapoor"};
    private final String[] areas = new String[]{"Koramangala", "Indiranagar", "HSR Layout", "Whitefield", "Electronic City", "Marathahalli", "BTM Layout", "Jayanagar", "JP Nagar", "Banashankari", "Malleshwaram", "Rajajinagar", "Basavanagudi", "Yelahanka", "Hebbal"};
    private final String[] cuisines = new String[]{"North Indian", "South Indian", "Chinese", "Italian", "Continental", "Bengali", "Gujarati", "Punjabi", "Maharashtrian", "Healthy/Diet"};
    private final String[] vehicleTypes = new String[]{"BICYCLE", "MOTORCYCLE", "SCOOTER", "CAR"};

    public void run(String ... args) throws Exception {
        log.info("Starting data seeding...");
        this.seedAdmins();
        this.seedUsers();
        this.seedHomemakers();
        this.seedDeliveryExecutives();
        log.info("Data seeding completed successfully!");
    }

    private void seedAdmins() {
        if (this.adminRepository.count() >= 3L) {
            log.info("Admins already exist, skipping admin seeding");
            return;
        }
        log.info("Creating 3 admin users...");
        ArrayList<Admin> admins = new ArrayList<Admin>();
        Admin superAdmin = new Admin();
        superAdmin.setUsername("superadmin");
        superAdmin.setEmail("superadmin@homebites.com");
        superAdmin.setPhoneNumber("9000000001");
        superAdmin.setFullName("Super Administrator");
        superAdmin.setPassword(this.passwordEncoder.encode((CharSequence)"admin123"));
        superAdmin.setAdminRole(Admin.AdminRole.SUPER_ADMIN);
        superAdmin.setStatus(Admin.AdminStatus.ACTIVE);
        superAdmin.setCanManageUsers(true);
        superAdmin.setCanManagePayments(true);
        superAdmin.setCanManageDisputes(true);
        superAdmin.setCanViewAnalytics(true);
        superAdmin.setCanManageAdmins(true);
        admins.add(superAdmin);
        Admin admin1 = new Admin();
        admin1.setUsername("admin");
        admin1.setEmail("admin@homebites.com");
        admin1.setPhoneNumber("9000000002");
        admin1.setFullName("Platform Administrator");
        admin1.setPassword(this.passwordEncoder.encode((CharSequence)"admin123"));
        admin1.setAdminRole(Admin.AdminRole.MODERATOR);
        admin1.setStatus(Admin.AdminStatus.ACTIVE);
        admin1.setCanManageUsers(true);
        admin1.setCanManagePayments(true);
        admin1.setCanManageDisputes(true);
        admin1.setCanViewAnalytics(true);
        admin1.setCanManageAdmins(false);
        admins.add(admin1);
        Admin supportAdmin = new Admin();
        supportAdmin.setUsername("support");
        supportAdmin.setEmail("support@homebites.com");
        supportAdmin.setPhoneNumber("9000000003");
        supportAdmin.setFullName("Support Staff");
        supportAdmin.setPassword(this.passwordEncoder.encode((CharSequence)"admin123"));
        supportAdmin.setAdminRole(Admin.AdminRole.SUPPORT);
        supportAdmin.setStatus(Admin.AdminStatus.ACTIVE);
        supportAdmin.setCanManageUsers(true);
        supportAdmin.setCanManagePayments(false);
        supportAdmin.setCanManageDisputes(true);
        supportAdmin.setCanViewAnalytics(false);
        supportAdmin.setCanManageAdmins(false);
        admins.add(supportAdmin);
        this.adminRepository.saveAll(admins);
        log.info("Created {} admins", admins.size());
    }

    private void seedUsers() {
        long existingCount = this.userRepository.count();
        if (existingCount >= 500L) {
            log.info("Users already exist ({}), skipping user seeding", existingCount);
            return;
        }
        int usersToCreate = 500 - (int)existingCount;
        log.info("Creating {} users...", usersToCreate);
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < usersToCreate; ++i) {
            User user = new User();
            String firstName = this.firstNames[this.random.nextInt(this.firstNames.length)];
            String lastName = this.lastNames[this.random.nextInt(this.lastNames.length)];
            user.setName(firstName + " " + lastName);
            user.setEmail("user" + (existingCount + (long)i + 1L) + "@example.com");
            user.setMobile("70" + String.format("%08d", i + 1));
            user.setPassword(this.passwordEncoder.encode((CharSequence)"password123"));
            user.setAddress(this.randomAddress());
            users.add(user);
            if (users.size() < 100) continue;
            this.userRepository.saveAll(users);
            log.info("Saved batch of {} users", users.size());
            users.clear();
        }
        if (!users.isEmpty()) {
            this.userRepository.saveAll(users);
            log.info("Saved final batch of {} users", users.size());
        }
        log.info("Created {} total users", usersToCreate);
    }

    private void seedHomemakers() {
        long existingCount = this.homeMakerRepository.count();
        if (existingCount >= 30L) {
            log.info("Homemakers already exist ({}), skipping homemaker seeding", existingCount);
            return;
        }
        int homemakersToCreate = 30 - (int)existingCount;
        log.info("Creating {} homemakers...", homemakersToCreate);
        ArrayList<HomeMaker> homemakers = new ArrayList<HomeMaker>();
        for (int i = 0; i < homemakersToCreate; ++i) {
            HomeMaker hm = new HomeMaker();
            String firstName = this.firstNames[this.random.nextInt(this.firstNames.length)];
            String lastName = this.lastNames[this.random.nextInt(this.lastNames.length)];
            hm.setName(firstName + " " + lastName);
            hm.setMobile("80" + String.format("%08d", i + 1));
            hm.setPassword(this.passwordEncoder.encode((CharSequence)"password123"));
            hm.setAddress(this.randomAddress());
            int statusRandom = this.random.nextInt(100);
            if (statusRandom < 50) {
                hm.setApprovalStatus(HomeMaker.ApprovalStatus.APPROVED);
            } else if (statusRandom < 80) {
                hm.setApprovalStatus(HomeMaker.ApprovalStatus.PENDING);
            } else {
                hm.setApprovalStatus(HomeMaker.ApprovalStatus.REJECTED);
                hm.setRejectionReason("Documents not verified");
            }
            homemakers.add(hm);
        }
        this.homeMakerRepository.saveAll(homemakers);
        log.info("Created {} homemakers", homemakersToCreate);
    }

    private void seedDeliveryExecutives() {
        long existingCount = this.deliveryExecutiveRepository.count();
        if (existingCount >= 50L) {
            log.info("Delivery executives already exist ({}), skipping executive seeding", existingCount);
            return;
        }
        int executivesToCreate = 50 - (int)existingCount;
        log.info("Creating {} delivery executives...", executivesToCreate);
        ArrayList<DeliveryExecutive> executives = new ArrayList<DeliveryExecutive>();
        for (int i = 0; i < executivesToCreate; ++i) {
            DeliveryExecutive de = new DeliveryExecutive();
            String firstName = this.firstNames[this.random.nextInt(this.firstNames.length)];
            String lastName = this.lastNames[this.random.nextInt(this.lastNames.length)];
            de.setName(firstName + " " + lastName);
            de.setMobile("90" + String.format("%08d", i + 1));
            de.setPassword(this.passwordEncoder.encode((CharSequence)"password123"));
            de.setAadharNo(this.generateAadhar());
            de.setLicenseNo("DL" + String.format("%012d", this.random.nextInt(1000000000)));
            de.setOnline(this.random.nextBoolean());
            int statusRandom = this.random.nextInt(100);
            if (statusRandom < 50) {
                de.setApprovalStatus(DeliveryExecutive.ApprovalStatus.APPROVED);
            } else if (statusRandom < 80) {
                de.setApprovalStatus(DeliveryExecutive.ApprovalStatus.PENDING);
            } else {
                de.setApprovalStatus(DeliveryExecutive.ApprovalStatus.REJECTED);
                de.setRejectionReason("License verification failed");
            }
            executives.add(de);
        }
        this.deliveryExecutiveRepository.saveAll(executives);
        log.info("Created {} delivery executives", executivesToCreate);
    }

    private String randomAddress() {
        int houseNum = this.random.nextInt(500) + 1;
        String area = this.areas[this.random.nextInt(this.areas.length)];
        return houseNum + ", " + area + ", Bangalore - " + (560000 + this.random.nextInt(100));
    }

    private String generateAadhar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; ++i) {
            sb.append(this.random.nextInt(10));
        }
        return sb.toString();
    }

    private String randomCuisines() {
        int numCuisines = this.random.nextInt(3) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numCuisines; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.cuisines[this.random.nextInt(this.cuisines.length)]);
        }
        return sb.toString();
    }

    private char randomLetter() {
        return (char)(65 + this.random.nextInt(26));
    }
}
