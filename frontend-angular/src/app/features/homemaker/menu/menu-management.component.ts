import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HomemakerService } from '../services/homemaker.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-menu-management',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, NavbarComponent],
  providers: [HomemakerService],
  templateUrl: './menu-management.component.html',
  styleUrls: ['./menu-management.component.scss']
})
export class MenuManagementComponent implements OnInit {
  menuItems: any[] = [];
  showAddForm = false;
  editingItem: any = null;
  isSubmitting = false;
  errorMessage = '';
  successMessage = '';
  
  newItem = {
    itemName: '',
    description: '',
    price: 0,
    category: '',
    isAvailable: true,
    estimatedPrepTime: 30,
    itemImage: ''
  };

  editItemPhoto: File | null = null;
  newItemPhoto: File | null = null;

  categories = ['Breakfast', 'Lunch', 'Dinner', 'Snacks', 'Desserts', 'Beverages'];

  constructor(private homemakerService: HomemakerService) {}

  ngOnInit() {
    this.loadMenuItems();
  }

  loadMenuItems() {
    this.homemakerService.getMenuItems().subscribe({
      next: (items: any) => {
        this.menuItems = items;
      },
      error: (err: any) => {
        console.error('Error loading menu items:', err);
        this.errorMessage = 'Failed to load menu items';
      }
    });
  }

  onPhotoSelected(event: any, isEdit: boolean = false) {
    const file = event.target.files[0];
    if (file) {
      if (isEdit) {
        this.editItemPhoto = file;
      } else {
        this.newItemPhoto = file;
      }
      
      // Create preview
      const reader = new FileReader();
      reader.onload = (e: any) => {
        if (isEdit) {
          this.editingItem.itemImage = e.target.result;
        } else {
          this.newItem.itemImage = e.target.result;
        }
      };
      reader.readAsDataURL(file);
    }
  }

  addMenuItem() {
    if (!this.newItem.itemName || !this.newItem.category || !this.newItem.price) {
      this.errorMessage = 'Please fill in all required fields';
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = '';
    this.successMessage = '';

    const formData = new FormData();
    formData.append('itemName', this.newItem.itemName);
    formData.append('description', this.newItem.description);
    formData.append('price', this.newItem.price.toString());
    formData.append('estimatedPrepTime', this.newItem.estimatedPrepTime.toString());
    formData.append('isAvailable', this.newItem.isAvailable.toString());
    
    if (this.newItemPhoto) {
      formData.append('photo', this.newItemPhoto);
    }

    this.homemakerService.addMenuItemWithPhoto(formData).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.successMessage = 'Menu item added successfully!';
        this.loadMenuItems();
        this.resetForm();
        this.showAddForm = false;
        
        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
      },
      error: (err: any) => {
        this.isSubmitting = false;
        console.error('Error adding item:', err);
        this.errorMessage = err.error?.message || 'Failed to add menu item';
      }
    });
  }

  editItem(item: any) {
    this.editingItem = { ...item };
    this.editItemPhoto = null;
  }

  updateMenuItem() {
    if (!this.editingItem.itemName || !this.editingItem.category || !this.editingItem.price) {
      this.errorMessage = 'Please fill in all required fields';
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = '';

    const formData = new FormData();
    formData.append('itemName', this.editingItem.itemName);
    formData.append('description', this.editingItem.description);
    formData.append('price', this.editingItem.price.toString());
    formData.append('estimatedPrepTime', this.editingItem.estimatedPrepTime.toString());
    formData.append('isAvailable', this.editingItem.isAvailable.toString());
    
    if (this.editItemPhoto) {
      formData.append('photo', this.editItemPhoto);
    }

    this.homemakerService.updateMenuItemWithPhoto(this.editingItem.id, formData).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.successMessage = 'Menu item updated successfully!';
        this.loadMenuItems();
        this.editingItem = null;
        
        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
      },
      error: (err: any) => {
        this.isSubmitting = false;
        console.error('Error updating item:', err);
        this.errorMessage = err.error?.message || 'Failed to update menu item';
      }
    });
  }

  deleteMenuItem(id: number) {
    if (confirm('Are you sure you want to delete this item?')) {
      this.homemakerService.deleteMenuItem(id).subscribe({
        next: () => {
          this.successMessage = 'Menu item deleted successfully!';
          this.loadMenuItems();
          
          setTimeout(() => {
            this.successMessage = '';
          }, 3000);
        },
        error: (err: any) => {
          console.error('Error deleting item:', err);
          this.errorMessage = 'Failed to delete menu item';
        }
      });
    }
  }

  toggleAvailability(item: any) {
    item.isAvailable = !item.isAvailable;
    this.homemakerService.updateMenuItem(item.id, item).subscribe({
      next: () => {
        this.successMessage = 'Availability updated!';
        this.loadMenuItems();
        
        setTimeout(() => {
          this.successMessage = '';
        }, 2000);
      },
      error: (err: any) => {
        console.error('Error updating availability:', err);
        this.errorMessage = 'Failed to update availability';
      }
    });
  }

  resetForm() {
    this.newItem = {
      itemName: '',
      description: '',
      price: 0,
      category: '',
      isAvailable: true,
      estimatedPrepTime: 30,
      itemImage: ''
    };
    this.newItemPhoto = null;
    this.errorMessage = '';
  }

  cancelEdit() {
    this.editingItem = null;
    this.editItemPhoto = null;
    this.errorMessage = '';
  }
}