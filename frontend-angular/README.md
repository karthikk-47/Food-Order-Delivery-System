# Food Delivery App - Angular 17 Frontend

## Overview
Modern Angular 17 frontend application with standalone components, JWT authentication, and role-based access control.

## Features

### Authentication & Security
- JWT token-based authentication
- HTTP interceptor for automatic token injection
- Auth guard for route protection
- Role-based access control (DELIVERYEXECUTIVE, HOMEMAKER, USER, ADMIN)
- Automatic token validation
- Secure logout functionality

### Delivery Executive Features
- **Dashboard**: Real-time nearby orders with smart sorting
- **Order Management**: Accept, pickup, and deliver orders
- **Wallet**: View balance, earnings, and transactions
- **Profile**: View and edit profile information
- **Location Tracking**: Automatic location updates every 30 seconds
- **Smart Sorting**: 4 sorting algorithms (Optimal, Commission, Distance, Priority)

### Technical Stack
- Angular 17 (Standalone Components)
- TypeScript 5.2
- RxJS 7.8
- Angular Material (optional)
- SCSS for styling

## Installation

```bash
cd frontend-angular
npm install
```

## Development Server

```bash
npm start
```

Navigate to `http://localhost:4200/`

## Build

```bash
npm run build
```

Build artifacts will be stored in the `dist/` directory.

## Project Structure

```
frontend-angular/
├── src/
│   ├── app/
│   │   ├── core/
│   │   │   ├── guards/
│   │   │   │   └── auth.guard.ts
│   │   │   ├── interceptors/
│   │   │   │   └── auth.interceptor.ts
│   │   │   └── services/
│   │   │       └── auth.service.ts
│   │   ├── features/
│   │   │   ├── auth/
│   │   │   │   └── login/
│   │   │   ├── delivery-executive/
│   │   │   │   ├── dashboard/
│   │   │   │   ├── orders/
│   │   │   │   ├── wallet/
│   │   │   │   ├── profile/
│   │   │   │   └── services/
│   │   │   ├── homemaker/
│   │   │   ├── user/
│   │   │   └── admin/
│   │   ├── shared/
│   │   │   └── components/
│   │   ├── app.component.ts
│   │   ├── app.config.ts
│   │   └── app.routes.ts
│   ├── styles.scss
│   ├── index.html
│   └── main.ts
├── angular.json
├── package.json
└── tsconfig.json
```

## API Integration

### Base URL
```typescript
const apiUrl = 'http://localhost:8080/api';
```

### Authentication Endpoints
- `POST /auth/login` - User login
- `GET /auth/validate` - Token validation

### Delivery Executive Endpoints
- `GET /delivery-executive/{id}/nearby-orders` - Get nearby orders
- `POST /delivery-executive/{id}/orders/{orderId}/accept` - Accept order
- `POST /delivery-executive/{id}/orders/{orderId}/pickup` - Confirm pickup
- `POST /delivery-executive/{id}/orders/{orderId}/deliver` - Confirm delivery
- `GET /delivery-executive/{id}/wallet` - Get wallet info
- `GET /delivery-executive/{id}/ratings` - Get ratings
- `POST /delivery-executive/{id}/status` - Update online status
- `POST /delivery-executive/{id}/location` - Update location

## Authentication Flow

1. User enters mobile and password
2. Frontend sends credentials to `/api/auth/login`
3. Backend validates and returns JWT token + user info
4. Frontend stores token in localStorage
5. Auth interceptor adds token to all subsequent requests
6. Auth guard protects routes based on user role

## Role-Based Access

### Routes Protection
```typescript
{
  path: 'delivery-executive',
  canActivate: [AuthGuard],
  data: { roles: ['DELIVERYEXECUTIVE'] }
}
```

### Roles
- **DELIVERYEXECUTIVE**: Access to delivery dashboard, orders, wallet
- **HOMEMAKER**: Access to homemaker features
- **USER**: Access to user features
- **ADMIN**: Access to admin panel

## Smart Order Sorting

### Sorting Algorithms
1. **Optimal (Default)**: Weighted algorithm considering:
   - Commission: 35%
   - Distance: 25%
   - Priority: 20%
   - Surge: 10%
   - Time: 10%

2. **Commission**: Highest earning orders first
3. **Distance**: Nearest orders first
4. **Priority**: HIGH > MEDIUM > LOW

### Usage
```typescript
getNearbyOrders(executiveId, latitude, longitude, 'optimal')
```

## Location Tracking

### Features
- Automatic location detection on load
- Updates every 30 seconds when online
- Sends location to backend for order matching
- Geolocation API integration

### Implementation
```typescript
navigator.geolocation.getCurrentPosition((position) => {
  const { latitude, longitude } = position.coords;
  this.updateLocationOnServer(latitude, longitude);
});
```

## Styling

### Design System
- Primary Color: #667eea (Purple)
- Secondary Color: #764ba2 (Dark Purple)
- Success Color: #4caf50 (Green)
- Error Color: #f44336 (Red)
- Warning Color: #ff9800 (Orange)

### Responsive Design
- Mobile-first approach
- Breakpoints: 768px, 1024px, 1200px
- Grid-based layouts
- Flexible components

## Security Features

### Token Management
- Stored in localStorage
- Automatically added to requests
- Validated on each route change
- Cleared on logout

### Error Handling
- 401 errors trigger automatic logout
- User-friendly error messages
- Network error handling
- Validation feedback

## Performance Optimizations

### Lazy Loading
- Feature modules loaded on demand
- Standalone components
- Route-based code splitting

### Change Detection
- OnPush strategy where applicable
- Unsubscribe from observables
- Efficient DOM updates

## Testing

```bash
npm test
```

## Environment Configuration

Create `src/environments/environment.ts`:
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

## Deployment

### Production Build
```bash
npm run build --configuration production
```

### Deploy to Server
1. Build the application
2. Copy `dist/` folder to web server
3. Configure server for SPA routing
4. Set up HTTPS
5. Configure CORS on backend

## Browser Support
- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

## Contributing
1. Create feature branch
2. Make changes
3. Test thoroughly
4. Submit pull request

## License
Proprietary - All rights reserved
