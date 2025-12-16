import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent {
  step: 'mobile' | 'otp' | 'password' | 'success' = 'mobile';
  mobile = '';
  otp = '';
  newPassword = '';
  confirmPassword = '';
  loading = false;
  error = '';
  success = '';
  devOtp = ''; // For development testing
  role = 'USER';
  roleColor = '#071627';

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.route.data.subscribe(data => {
      if (data['role']) {
        this.role = data['role'];
        this.roleColor = data['color'] || '#071627';
      }
    });
  }

  requestOTP() {
    if (!this.mobile) {
      this.error = 'Please enter your mobile number';
      return;
    }

    this.loading = true;
    this.error = '';

    this.authService.forgotPassword(this.mobile).subscribe({
      next: (response: any) => {
        this.loading = false;
        if (response.success) {
          this.step = 'otp';
          this.success = response.message;
          // For development, show OTP
          if (response.otp) {
            this.devOtp = response.otp;
          }
        } else {
          this.error = response.message || 'Failed to send OTP';
        }
      },
      error: (err) => {
        this.loading = false;
        this.error = err.error?.message || 'Failed to send OTP. Please try again.';
      }
    });
  }

  verifyOTP() {
    if (!this.otp) {
      this.error = 'Please enter the OTP';
      return;
    }

    this.loading = true;
    this.error = '';

    this.authService.verifyOTP(this.mobile, this.otp).subscribe({
      next: (response: any) => {
        this.loading = false;
        if (response.success) {
          this.step = 'password';
          this.success = 'OTP verified. Please set your new password.';
        } else {
          this.error = response.message || 'Invalid OTP';
        }
      },
      error: (err) => {
        this.loading = false;
        this.error = err.error?.message || 'Invalid OTP. Please try again.';
      }
    });
  }

  resetPassword() {
    if (!this.newPassword || !this.confirmPassword) {
      this.error = 'Please fill in all fields';
      return;
    }

    if (this.newPassword !== this.confirmPassword) {
      this.error = 'Passwords do not match';
      return;
    }

    if (this.newPassword.length < 6) {
      this.error = 'Password must be at least 6 characters';
      return;
    }

    this.loading = true;
    this.error = '';

    this.authService.resetPassword(this.mobile, this.otp, this.newPassword).subscribe({
      next: (response: any) => {
        this.loading = false;
        if (response.success) {
          this.step = 'success';
          this.success = response.message;
        } else {
          this.error = response.message || 'Failed to reset password';
        }
      },
      error: (err) => {
        this.loading = false;
        this.error = err.error?.message || 'Failed to reset password. Please try again.';
      }
    });
  }

  goToLogin() {
    const roleRoute = this.role.toLowerCase();
    this.router.navigate(['/login', roleRoute === 'deliveryexecutive' ? 'executive' : roleRoute]);
  }

  resendOTP() {
    this.otp = '';
    this.devOtp = '';
    this.requestOTP();
  }
}
