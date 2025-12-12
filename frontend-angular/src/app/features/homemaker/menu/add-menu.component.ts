import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HomemakerService } from '../services/homemaker.service';

@Component({
  selector: 'app-add-menu',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  providers: [HomemakerService],
  templateUrl: './add-menu.component.html',
  styleUrls: ['./add-menu.component.scss']
})
export class AddMenuComponent implements OnInit {
  menuForm: FormGroup;
  isSubmitting = false;
  errorMessage = '';
  successMessage = '';
  homemakerId: number = 0;

  menuTypes = ['SECRET_MENU', 'SIGNATURE_DISH', 'SUBSCRIPTION'];
  cuisineTypes = [
    'Indian',
    'Chinese',
    'Continental',
    'Italian',
    'Thai',
    'Mexican',
    'Japanese',
    'Korean',
    'Vietnamese',
    'Spanish'
  ];

  constructor(
    private formBuilder: FormBuilder,
    private homemakerService: HomemakerService,
    private router: Router
  ) {
    this.menuForm = this.createForm();
  }

  ngOnInit() {
    // Get homemaker ID from localStorage or session
    const userData = localStorage.getItem('user');
    if (userData) {
      try {
        const user = JSON.parse(userData);
        this.homemakerId = user.id;
      } catch (e) {
        console.error('Error parsing user data', e);
        this.errorMessage = 'Unable to identify user. Please login again.';
      }
    }
  }

  createForm(): FormGroup {
    return this.formBuilder.group({
      description: ['', [Validators.required, Validators.minLength(10)]],
      menuType: ['SIGNATURE_DISH', Validators.required],
      cuisineTypes: [[], Validators.required],
      estimatedPrepTime: ['30', [Validators.required, Validators.min(1)]],
      validFrom: ['', Validators.required],
      validUntil: [''],
      isRecurring: [false],
      recurrencePattern: [''],
      minOrderQuantity: ['1', [Validators.required, Validators.min(1)]],
      maxOrderQuantity: ['100', [Validators.required, Validators.min(1)]]
    });
  }

  toggleCuisineType(cuisine: string) {
    const cuisineTypesControl = this.menuForm.get('cuisineTypes');
    if (cuisineTypesControl) {
      const currentValue = cuisineTypesControl.value || [];
      const index = currentValue.indexOf(cuisine);
      if (index > -1) {
        currentValue.splice(index, 1);
      } else {
        currentValue.push(cuisine);
      }
      cuisineTypesControl.setValue([...currentValue]);
    }
  }

  isCuisineSelected(cuisine: string): boolean {
    const currentValue = this.menuForm.get('cuisineTypes')?.value || [];
    return currentValue.includes(cuisine);
  }

  onRecurringChange(event: any) {
    const isRecurring = event.target.checked;
    const recurrenceControl = this.menuForm.get('recurrencePattern');
    if (isRecurring) {
      recurrenceControl?.setValidators([Validators.required]);
    } else {
      recurrenceControl?.clearValidators();
    }
    recurrenceControl?.updateValueAndValidity();
  }

  addMenu() {
    if (!this.menuForm.valid) {
      this.errorMessage = 'Please fill in all required fields correctly.';
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = '';
    this.successMessage = '';

    const menuData = {
      ...this.menuForm.value,
      homemakerId: this.homemakerId
    };

    this.homemakerService.createMenu(this.homemakerId, menuData).subscribe({
      next: (response) => {
        this.isSubmitting = false;
        this.successMessage = 'Menu created successfully!';
        setTimeout(() => {
          this.router.navigate(['/homemaker/menu']);
        }, 2000);
      },
      error: (error) => {
        this.isSubmitting = false;
        console.error('Error creating menu:', error);
        this.errorMessage = error.error?.message || 'Failed to create menu. Please try again.';
      }
    });
  }

  cancel() {
    this.router.navigate(['/homemaker/menu']);
  }
}
