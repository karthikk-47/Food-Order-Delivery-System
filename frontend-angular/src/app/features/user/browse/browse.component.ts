import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UserService } from '../services/user.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-browse',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, NavbarComponent],
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.scss']
})
export class BrowseComponent implements OnInit {
  homemakers: any[] = [];
  filteredHomemakers: any[] = [];
  searchQuery = '';
  selectedCuisine = '';
  cuisines = ['All', 'North Indian', 'South Indian', 'Chinese', 'Continental', 'Desserts'];
  loading = false;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.loadHomemakers();
  }

  loadHomemakers() {
    this.loading = true;
    this.userService.getNearbyHomemakers().subscribe({
      next: (data) => {
        this.homemakers = data;
        this.filteredHomemakers = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading homemakers:', err);
        this.loading = false;
      }
    });
  }

  filterHomemakers() {
    this.filteredHomemakers = this.homemakers.filter(h => {
      const matchesSearch = h.name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
                           h.cuisine?.toLowerCase().includes(this.searchQuery.toLowerCase());
      const matchesCuisine = !this.selectedCuisine || this.selectedCuisine === 'All' || 
                            h.cuisine === this.selectedCuisine;
      return matchesSearch && matchesCuisine;
    });
  }
}
