package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.admin.repository.AdminRepository;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/admin/seed"})
public class DataSeedController {
    private static final Logger log = LoggerFactory.getLogger(DataSeedController.class);
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

    @GetMapping(value={"/status"})
    public ResponseEntity<Map<String, Object>> getStatus() {
        HashMap<String, Object> status = new HashMap<String, Object>();
        status.put("users", this.userRepository.count());
        status.put("homemakers", this.homeMakerRepository.count());
        status.put("executives", this.deliveryExecutiveRepository.count());
        status.put("admins", this.adminRepository.count());
        return ResponseEntity.ok(status);
    }

    @PostMapping(value={"/all"})
    public ResponseEntity<Map<String, Object>> seedAll() {
        log.info("Starting data seeding...");
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            int adminsCreated = this.seedAdmins();
            int usersCreated = this.seedUsers();
            int homemakersCreated = this.seedHomemakers();
            int executivesCreated = this.seedDeliveryExecutives();
            result.put("success", true);
            result.put("message", "Data seeding completed");
            result.put("adminsCreated", adminsCreated);
            result.put("usersCreated", usersCreated);
            result.put("homemakersCreated", homemakersCreated);
            result.put("executivesCreated", executivesCreated);
            result.put("totalCounts", Map.of("users", this.userRepository.count(), "homemakers", this.homeMakerRepository.count(), "executives", this.deliveryExecutiveRepository.count(), "admins", this.adminRepository.count()));
            log.info("Data seeding completed: {} users, {} homemakers, {} executives, {} admins", new Object[]{usersCreated, homemakersCreated, executivesCreated, adminsCreated});
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            log.error("Error during data seeding", e);
            result.put("success", false);
            result.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    private int seedAdmins() {
        long existingCount = this.adminRepository.count();
        if (existingCount >= 3L) {
            log.info("Admins already exist ({}), skipping", existingCount);
            return 0;
        }
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
        return admins.size();
    }

    private int seedUsers() {
        long existingCount = this.userRepository.count();
        if (existingCount >= 500L) {
            log.info("Users already exist ({}), skipping", existingCount);
            return 0;
        }
        int usersToCreate = 500 - (int)existingCount;
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < usersToCreate; ++i) {
            User user = new User();
            String firstName = this.firstNames[this.random.nextInt(this.firstNames.length)];
            String lastName = this.lastNames[this.random.nextInt(this.lastNames.length)];
            user.setName(firstName + " " + lastName);
            user.setEmail("user" + (existingCount + (long)i + 1L) + "@example.com");
            user.setMobile("70" + String.format("%08d", existingCount + (long)i + 1L));
            user.setPassword(this.passwordEncoder.encode((CharSequence)"password123"));
            user.setAddress(this.randomAddress());
            users.add(user);
            if (users.size() < 100) continue;
            this.userRepository.saveAll(users);
            users.clear();
        }
        if (!users.isEmpty()) {
            this.userRepository.saveAll(users);
        }
        return usersToCreate;
    }

    private int seedHomemakers() {
        long existingCount = this.homeMakerRepository.count();
        if (existingCount >= 30L) {
            log.info("Homemakers already exist ({}), skipping", existingCount);
            return 0;
        }
        int homemakersToCreate = 30 - (int)existingCount;
        ArrayList<HomeMaker> homemakers = new ArrayList<HomeMaker>();
        for (int i = 0; i < homemakersToCreate; ++i) {
            HomeMaker hm = new HomeMaker();
            String firstName = this.firstNames[this.random.nextInt(this.firstNames.length)];
            String lastName = this.lastNames[this.random.nextInt(this.lastNames.length)];
            hm.setName(firstName + " " + lastName);
            hm.setMobile("80" + String.format("%08d", existingCount + (long)i + 1L));
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
        return homemakersToCreate;
    }

    private int seedDeliveryExecutives() {
        long existingCount = this.deliveryExecutiveRepository.count();
        if (existingCount >= 50L) {
            log.info("Delivery executives already exist ({}), skipping", existingCount);
            return 0;
        }
        int executivesToCreate = 50 - (int)existingCount;
        ArrayList<DeliveryExecutive> executives = new ArrayList<DeliveryExecutive>();
        for (int i = 0; i < executivesToCreate; ++i) {
            DeliveryExecutive de = new DeliveryExecutive();
            String firstName = this.firstNames[this.random.nextInt(this.firstNames.length)];
            String lastName = this.lastNames[this.random.nextInt(this.lastNames.length)];
            de.setName(firstName + " " + lastName);
            de.setMobile("90" + String.format("%08d", existingCount + (long)i + 1L));
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
        return executivesToCreate;
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
