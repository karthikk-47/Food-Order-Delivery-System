import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { trigger, transition, style, animate, stagger, query, state } from '@angular/animations';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [CommonModule, RouterModule],
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
    animations: [
        trigger('fadeInUp', [
            transition(':enter', [
                style({ opacity: 0, transform: 'translateY(30px)' }),
                animate('600ms ease-out', style({ opacity: 1, transform: 'translateY(0)' }))
            ])
        ]),
        trigger('staggerCards', [
            transition(':enter', [
                query('.role-card', [
                    style({ opacity: 0, transform: 'translateY(50px) scale(0.9)' }),
                    stagger('100ms', [
                        animate('500ms cubic-bezier(0.35, 0, 0.25, 1)',
                            style({ opacity: 1, transform: 'translateY(0) scale(1)' }))
                    ])
                ], { optional: true })
            ])
        ]),
        trigger('floatAnimation', [
            state('float', style({ transform: 'translateY(0)' })),
            transition('* <=> *', [
                animate('3s ease-in-out')
            ])
        ])
    ]
})
export class HomeComponent implements OnInit, AfterViewInit {
    showContent = false;
    currentYear = new Date().getFullYear();

    roles = [
        {
            id: 'user',
            title: 'Customer',
            subtitle: 'Order delicious homemade food',
            icon: '🍽️',
            description: 'Explore authentic homemade meals from local chefs. Order fresh, healthy food delivered to your doorstep.',
            features: ['Wide variety of cuisines', 'Fresh homemade food', 'Easy ordering', 'Real-time tracking'],
            gradient: 'linear-gradient(135deg, #071627 0%, #1c3d5a 100%)',
            color: '#071627',
            loginRoute: '/login/user',
            signupRoute: '/signup/user'
        },
        {
            id: 'homemaker',
            title: 'Home Chef',
            subtitle: 'Share your culinary passion',
            icon: '👨‍🍳',
            description: 'Turn your cooking skills into earnings. Join our network of home chefs and reach hungry customers.',
            features: ['Earn from home', 'Flexible timings', 'Set your menu', 'Build your brand'],
            gradient: 'linear-gradient(135deg, #FF8A00 0%, #E67E00 100%)',
            color: '#FF8A00',
            loginRoute: '/login/homemaker',
            signupRoute: '/signup/homemaker'
        },
        {
            id: 'executive',
            title: 'Delivery Partner',
            subtitle: 'Earn on your own schedule',
            icon: '🚴',
            description: 'Be your own boss. Deliver food and earn money with flexible hours and great incentives.',
            features: ['Flexible hours', 'Weekly payouts', 'Bonus incentives', 'Easy navigation'],
            gradient: 'linear-gradient(135deg, #00c853 0%, #009624 100%)',
            color: '#00c853',
            loginRoute: '/login/executive',
            signupRoute: '/signup/executive'
        },
        {
            id: 'admin',
            title: 'Administrator',
            subtitle: 'Manage the platform',
            icon: '⚙️',
            description: 'Access the admin dashboard to manage users, orders, payments, and platform operations.',
            features: ['User management', 'Analytics', 'Order oversight', 'System controls'],
            gradient: 'linear-gradient(135deg, #9B59B6 0%, #8E44AD 100%)',
            color: '#9B59B6',
            loginRoute: '/login/admin',
            signupRoute: null // Admin signup is not public
        }
    ];

    stats = [
        { value: '10K+', label: 'Happy Customers', icon: '😊' },
        { value: '500+', label: 'Home Chefs', icon: '👨‍🍳' },
        { value: '1000+', label: 'Daily Deliveries', icon: '📦' },
        { value: '50+', label: 'Cities', icon: '🏙️' }
    ];

    features = [
        {
            icon: '🏠',
            title: 'Homemade Goodness',
            description: 'Authentic recipes made with love by local home chefs'
        },
        {
            icon: '⚡',
            title: 'Fast Delivery',
            description: 'Hot and fresh food delivered within 30-45 minutes'
        },
        {
            icon: '💰',
            title: 'Best Prices',
            description: 'Affordable homemade food without restaurant markups'
        },
        {
            icon: '🔒',
            title: 'Safe & Hygienic',
            description: 'Verified kitchens with strict hygiene standards'
        }
    ];

    ngOnInit(): void {
        // Trigger animations after component init
        setTimeout(() => {
            this.showContent = true;
        }, 100);
    }

    ngAfterViewInit(): void {
        // Add scroll animations
        this.initScrollAnimations();
    }

    private initScrollAnimations(): void {
        if (typeof window !== 'undefined' && 'IntersectionObserver' in window) {
            const observer = new IntersectionObserver((entries) => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        entry.target.classList.add('visible');
                    }
                });
            }, { threshold: 0.1 });

            document.querySelectorAll('.scroll-animate').forEach(el => {
                observer.observe(el);
            });
        }
    }
}
