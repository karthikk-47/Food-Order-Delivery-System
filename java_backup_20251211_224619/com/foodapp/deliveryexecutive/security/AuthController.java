/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.ResponseEntity
 *  org.springframework.security.authentication.AuthenticationManager
 *  org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 *  org.springframework.security.core.Authentication
 *  org.springframework.security.core.context.SecurityContextHolder
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestHeader
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.security;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.security.JwtTokenProvider;
import com.foodapp.deliveryexecutive.security.UserPrincipal;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/auth"})
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeMakerRepository homeMakerRepository;
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;

    @PostMapping(value={"/login"})
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = this.authenticationManager.authenticate((Authentication)new UsernamePasswordAuthenticationToken((Object)loginRequest.getMobile(), (Object)loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = this.tokenProvider.createToken(authentication);
            UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
            return ResponseEntity.ok((Object)new JwtAuthenticationResponse(jwt, userPrincipal.getId(), userPrincipal.getMobile(), userPrincipal.getRole()));
        }
        catch (Exception e) {
            logger.error("Authentication failed for mobile: {}", (Object)loginRequest.getMobile(), (Object)e);
            return ResponseEntity.badRequest().body((Object)new ApiResponse(false, "Invalid mobile or password"));
        }
    }

    @PostMapping(value={"/register"})
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            String role = registerRequest.getRole();
            String mobile = registerRequest.getMobile();
            if (this.userRepository.findByMobile(mobile).isPresent() || this.homeMakerRepository.findByMobile(mobile).isPresent() || this.deliveryExecutiveRepository.findByMobile(mobile).isPresent()) {
                return ResponseEntity.badRequest().body((Object)new ApiResponse(false, "Mobile number already registered"));
            }
            String encodedPassword = this.passwordEncoder.encode((CharSequence)registerRequest.getPassword());
            if ("USER".equals(role)) {
                User user = new User();
                user.setName(registerRequest.getName());
                user.setMobile(mobile);
                user.setPassword(encodedPassword);
                user.setEmail(registerRequest.getEmail());
                user.setAddress(registerRequest.getAddress());
                user.setRole(Actor.Role.USER);
                this.userRepository.save(user);
                return this.authenticateAndRespond(mobile, registerRequest.getPassword());
            }
            if ("HOMEMAKER".equals(role)) {
                HomeMaker homeMaker = new HomeMaker();
                homeMaker.setName(registerRequest.getName());
                homeMaker.setMobile(mobile);
                homeMaker.setPassword(encodedPassword);
                homeMaker.setAddress(registerRequest.getAddress());
                homeMaker.setRole(Actor.Role.HOMEMAKER);
                homeMaker.setApprovalStatus(HomeMaker.ApprovalStatus.PENDING);
                this.homeMakerRepository.save(homeMaker);
                return ResponseEntity.ok((Object)new ApiResponse(true, "Registration successful! Your account is pending admin approval. You will be able to login once approved."));
            }
            if ("DELIVERYEXECUTIVE".equals(role)) {
                DeliveryExecutive executive = new DeliveryExecutive();
                executive.setName(registerRequest.getName());
                executive.setMobile(mobile);
                executive.setPassword(encodedPassword);
                executive.setAadharNo(registerRequest.getAadharNo());
                executive.setLicenseNo(registerRequest.getLicenseNo());
                executive.setRole(Actor.Role.DELIVERYEXECUTIVE);
                executive.setApprovalStatus(DeliveryExecutive.ApprovalStatus.PENDING);
                this.deliveryExecutiveRepository.save(executive);
                return ResponseEntity.ok((Object)new ApiResponse(true, "Registration successful! Your account is pending admin approval. You will be able to login once approved."));
            }
            return ResponseEntity.badRequest().body((Object)new ApiResponse(false, "Invalid role specified"));
        }
        catch (Exception e) {
            logger.error("Registration failed for mobile: {}", (Object)registerRequest.getMobile(), (Object)e);
            return ResponseEntity.badRequest().body((Object)new ApiResponse(false, "Registration failed: " + e.getMessage()));
        }
    }

    private ResponseEntity<?> authenticateAndRespond(String mobile, String password) {
        Authentication authentication = this.authenticationManager.authenticate((Authentication)new UsernamePasswordAuthenticationToken((Object)mobile, (Object)password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.tokenProvider.createToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        return ResponseEntity.ok((Object)new JwtAuthenticationResponse(jwt, userPrincipal.getId(), userPrincipal.getMobile(), userPrincipal.getRole()));
    }

    @GetMapping(value={"/validate"})
    public ResponseEntity<?> validateToken(@RequestHeader(value="Authorization") String token) {
        try {
            String jwt;
            if (token != null && token.startsWith("Bearer ") && this.tokenProvider.validateToken(jwt = token.substring(7))) {
                String username = this.tokenProvider.getUsernameFromJWT(jwt);
                return ResponseEntity.ok((Object)new ApiResponse(true, "Token is valid for user: " + username));
            }
            return ResponseEntity.badRequest().body((Object)new ApiResponse(false, "Invalid token"));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body((Object)new ApiResponse(false, "Token validation failed"));
        }
    }

    public static class LoginRequest {
        private String mobile;
        private String password;

        public String getMobile() {
            return this.mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class JwtAuthenticationResponse {
        private String accessToken;
        private String tokenType = "Bearer";
        private Long userId;
        private String mobile;
        private Actor.Role role;

        public JwtAuthenticationResponse(String accessToken, Long userId, String mobile, Actor.Role role) {
            this.accessToken = accessToken;
            this.userId = userId;
            this.mobile = mobile;
            this.role = role;
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getTokenType() {
            return this.tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public Long getUserId() {
            return this.userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Actor.Role getRole() {
            return this.role;
        }

        public void setRole(Actor.Role role) {
            this.role = role;
        }
    }

    public static class ApiResponse {
        private Boolean success;
        private String message;

        public ApiResponse(Boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public Boolean getSuccess() {
            return this.success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class RegisterRequest {
        private String name;
        private String email;
        private String mobile;
        private String password;
        private String role;
        private String address;
        private String aadharNo;
        private String licenseNo;
        private String vehicleType;
        private String vehicleNumber;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return this.role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAadharNo() {
            return this.aadharNo;
        }

        public void setAadharNo(String aadharNo) {
            this.aadharNo = aadharNo;
        }

        public String getLicenseNo() {
            return this.licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public String getVehicleType() {
            return this.vehicleType;
        }

        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }

        public String getVehicleNumber() {
            return this.vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }
    }
}
