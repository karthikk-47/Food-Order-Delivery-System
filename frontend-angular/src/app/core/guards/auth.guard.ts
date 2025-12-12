import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUser = this.authService.currentUserValue;
    
    if (currentUser) {
      // Check if route has role requirement
      const requiredRoles = route.data['roles'] as Array<string>;
      if (requiredRoles && !requiredRoles.includes(currentUser.role)) {
        this.router.navigate(['/unauthorized']);
        return false;
      }
      return true;
    }

    // Not logged in, redirect to login page
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
