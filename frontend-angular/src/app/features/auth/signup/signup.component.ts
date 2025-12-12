import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { AuthService, RegisterRequest } from '../../../core/services/auth.service';

interface RoleConfig {
    role: string;
    title: string;
    color: string;
    icon: string;
    gradient: string;
    fields: string[];
}

@Component({
    selector: 'app-signup',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, RouterModule],
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
    signupForm!: FormGroup;
    loading = false;
    error = '';
    success = '';
    showPassword = false;
    currentStep = 1;
    totalSteps = 2;

    // Getter for easy access to form controls
    get f() { return this.signupForm.controls; }

    roleConfig: RoleConfig = {
        role: 'USER',
        title: 'Customer Signup',
        color: '#071627',
        icon: '🍽️',
        gradient: 'linear-gradient(135deg, #071627 0%, #1c3d5a 100%)',
        fields: ['name', 'email', 'mobile', 'password', 'address']
    };

    private roleConfigs: { [key: string]: RoleConfig } = {
        'USER': {
            role: 'USER',
            title: 'Customer Signup',
            color: '#071627',
            icon: '🍽️',
            gradient: 'linear-gradient(135deg, #071627 0%, #1c3d5a 100%)',
            fields: ['name', 'email', 'mobile', 'password', 'address']
        },
        'HOMEMAKER': {
            role: 'HOMEMAKER',
            title: 'Home Chef Signup',
            color: '#FF8A00',
            icon: '👨‍🍳',
            gradient: 'linear-gradient(135deg, #FF8A00 0%, #E67E00 100%)',
            fields: ['name', 'email', 'mobile', 'password', 'address', 'cuisineTypes', 'fssaiLicense']
        },
        'DELIVERYEXECUTIVE': {
            role: 'DELIVERYEXECUTIVE',
            title: 'Delivery Partner Signup',
            color: '#00c853',
            icon: '🚴',
            gradient: 'linear-gradient(135deg, #00c853 0%, #009624 100%)',
            fields: ['name', 'email', 'mobile', 'password', 'licenseNo', 'vehicleType', 'vehicleNumber', 'aadharNo']
        }
    };

    vehicleTypes = [
        { value: 'BICYCLE', label: 'Bicycle' },
        { value: 'MOTORCYCLE', label: 'Motorcycle' },
        { value: 'SCOOTER', label: 'Scooter' },
        { value: 'CAR', label: 'Car' }
    ];

    cuisineOptions = [
        'North Indian', 'South Indian', 'Chinese', 'Italian',
        'Continental', 'Bengali', 'Gujarati', 'Punjabi',
        'Maharashtrian', 'Healthy/Diet', 'Desserts', 'Snacks'
    ];

    constructor(
        private fb: FormBuilder,
        private router: Router,
        private route: ActivatedRoute,
        private authService: AuthService
    ) { }

    ngOnInit(): void {
        this.route.data.subscribe(data => {
            if (data['role'] && this.roleConfigs[data['role']]) {
                this.roleConfig = this.roleConfigs[data['role']];
            }
        });

        this.initForm();
    }

    private initForm(): void {
        const baseControls: any = {
            name: ['', [Validators.required, Validators.minLength(2)]],
            email: ['', [Validators.required, Validators.email]],
            mobile: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            confirmPassword: ['', [Validators.required]],
            agreeTerms: [false, [Validators.requiredTrue]]
        };

        // Add role-specific fields
        if (this.roleConfig.role === 'USER') {
            baseControls['address'] = ['', [Validators.required]];
        } else if (this.roleConfig.role === 'HOMEMAKER') {
            baseControls['address'] = ['', [Validators.required]];
            baseControls['cuisineTypes'] = [[], [Validators.required]];
            baseControls['fssaiLicense'] = [''];
            baseControls['kitchenDescription'] = [''];
        } else if (this.roleConfig.role === 'DELIVERYEXECUTIVE') {
            baseControls['licenseNo'] = ['', [Validators.required]];
            baseControls['vehicleType'] = ['', [Validators.required]];
            baseControls['vehicleNumber'] = ['', [Validators.required]];
            baseControls['aadharNo'] = ['', [Validators.required, Validators.pattern('^[0-9]{12}$')]];
        }

        this.signupForm = this.fb.group(baseControls, {
            validators: this.passwordMatchValidator
        });
    }

    passwordMatchValidator(form: FormGroup) {
        const password = form.get('password');
        const confirmPassword = form.get('confirmPassword');

        if (password && confirmPassword && password.value !== confirmPassword.value) {
            confirmPassword.setErrors({ passwordMismatch: true });
        }
        return null;
    }

    togglePassword(): void {
        this.showPassword = !this.showPassword;
    }

    nextStep(): void {
        if (this.currentStep < this.totalSteps) {
            this.currentStep++;
        }
    }

    prevStep(): void {
        if (this.currentStep > 1) {
            this.currentStep--;
        }
    }

    toggleCuisine(cuisine: string): void {
        const cuisines = this.signupForm.get('cuisineTypes')?.value as string[] || [];
        const index = cuisines.indexOf(cuisine);

        if (index > -1) {
            cuisines.splice(index, 1);
        } else {
            cuisines.push(cuisine);
        }

        this.signupForm.patchValue({ cuisineTypes: [...cuisines] });
    }

    isCuisineSelected(cuisine: string): boolean {
        const cuisines = this.signupForm.get('cuisineTypes')?.value as string[] || [];
        return cuisines.includes(cuisine);
    }

    onSubmit(): void {
        if (this.signupForm.invalid) {
            // Mark all fields as touched to show errors
            Object.keys(this.signupForm.controls).forEach(key => {
                this.signupForm.get(key)?.markAsTouched();
            });
            return;
        }

        this.loading = true;
        this.error = '';
        this.success = '';

        // Prepare registration data
        const formValue = this.signupForm.value;
        const userData: RegisterRequest = {
            name: formValue.name,
            email: formValue.email,
            mobile: formValue.mobile,
            password: formValue.password,
            role: this.roleConfig.role as 'USER' | 'HOMEMAKER' | 'DELIVERYEXECUTIVE'
        };

        // Add role-specific fields
        if (this.roleConfig.role === 'USER') {
            userData.address = formValue.address;
        } else if (this.roleConfig.role === 'HOMEMAKER') {
            userData.address = formValue.address;
            userData.cuisineTypes = formValue.cuisineTypes || [];
            userData.fssaiLicense = formValue.fssaiLicense;
            userData.kitchenDescription = formValue.kitchenDescription;
        } else if (this.roleConfig.role === 'DELIVERYEXECUTIVE') {
            userData.licenseNo = formValue.licenseNo;
            userData.vehicleType = formValue.vehicleType;
            userData.vehicleNumber = formValue.vehicleNumber;
            userData.aadharNo = formValue.aadharNo;
        }

        // Call the registration API
        this.authService.register(userData).subscribe({
            next: (response) => {
                this.loading = false;
                
                // Check if this is a pending approval response (HOMEMAKER/DELIVERYEXECUTIVE)
                if (response.success !== undefined) {
                    // This is an ApiResponse (success/message format)
                    if (response.success) {
                        this.success = response.message || 'Registration successful!';
                        // Redirect to login after showing message
                        setTimeout(() => {
                            this.router.navigate([this.getLoginRoute()]);
                        }, 3000);
                    } else {
                        this.error = response.message || 'Registration failed.';
                    }
                } else if (response.accessToken) {
                    // This is an AuthResponse (USER registration with auto-login)
                    this.success = 'Account created successfully! Redirecting to dashboard...';
                    setTimeout(() => {
                        this.router.navigate(['/user/dashboard']);
                    }, 2000);
                }
            },
            error: (error) => {
                console.error('Registration error:', error);
                this.loading = false;
                this.error = error.error?.message || 'Registration failed. Please try again.';
            }
        });
    }

    getLoginRoute(): string {
        const routes: { [key: string]: string } = {
            'USER': '/login/user',
            'HOMEMAKER': '/login/homemaker',
            'DELIVERYEXECUTIVE': '/login/executive'
        };
        return routes[this.roleConfig.role] || '/login/user';
    }
}
