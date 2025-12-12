# Food Delivery App - Setup and Run Script
# PowerShell script for Windows

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Food Delivery App - Setup & Run" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check prerequisites
Write-Host "Checking prerequisites..." -ForegroundColor Yellow

# Check Java
try {
    $javaVersion = java -version 2>&1 | Select-String "version"
    Write-Host "✓ Java found: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Java not found. Please install Java 17" -ForegroundColor Red
    exit 1
}

# Check Maven
try {
    $mavenVersion = mvn -version 2>&1 | Select-String "Apache Maven"
    Write-Host "✓ Maven found: $mavenVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Maven not found. Please install Maven" -ForegroundColor Red
    exit 1
}

# Check Node.js
try {
    $nodeVersion = node --version
    Write-Host "✓ Node.js found: $nodeVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Node.js not found. Please install Node.js 18+" -ForegroundColor Red
    exit 1
}

# Check npm
try {
    $npmVersion = npm --version
    Write-Host "✓ npm found: v$npmVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ npm not found. Please install npm" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Setting up environment variables..." -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Cyan

# Set environment variables for current session
$env:DB_URL = "jdbc:mysql://localhost:3306/food_app"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = ""
$env:JWT_SECRET = "MySecretKeyForJWTTokenGenerationAndValidation2024FoodDeliveryApp"
$env:RAZORPAY_KEY = "your_razorpay_key"
$env:RAZORPAY_SECRET = "your_razorpay_secret"
$env:OLAMAP_API_KEY = "your_olamap_key"

Write-Host "✓ Environment variables set" -ForegroundColor Green
Write-Host ""

# Ask user what to do
Write-Host "What would you like to do?" -ForegroundColor Cyan
Write-Host "1. Setup and run backend only" -ForegroundColor White
Write-Host "2. Setup and run frontend only" -ForegroundColor White
Write-Host "3. Setup and run both (recommended)" -ForegroundColor White
Write-Host "4. Build for production" -ForegroundColor White
Write-Host "5. Exit" -ForegroundColor White
Write-Host ""

$choice = Read-Host "Enter your choice (1-5)"

switch ($choice) {
    "1" {
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "Starting Backend..." -ForegroundColor Yellow
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "Backend will start at: http://localhost:8080/api" -ForegroundColor Green
        Write-Host "Press Ctrl+C to stop" -ForegroundColor Yellow
        Write-Host ""
        mvn spring-boot:run
    }
    "2" {
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "Setting up Frontend..." -ForegroundColor Yellow
        Write-Host "========================================" -ForegroundColor Cyan
        
        if (-not (Test-Path "frontend-angular/node_modules")) {
            Write-Host "Installing npm dependencies..." -ForegroundColor Yellow
            Set-Location frontend-angular
            npm install
            Set-Location ..
            Write-Host "✓ Dependencies installed" -ForegroundColor Green
        } else {
            Write-Host "✓ Dependencies already installed" -ForegroundColor Green
        }
        
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "Starting Frontend..." -ForegroundColor Yellow
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "Frontend will start at: http://localhost:4200" -ForegroundColor Green
        Write-Host "Press Ctrl+C to stop" -ForegroundColor Yellow
        Write-Host ""
        Set-Location frontend-angular
        npm start
    }
    "3" {
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "Setting up Frontend..." -ForegroundColor Yellow
        Write-Host "========================================" -ForegroundColor Cyan
        
        if (-not (Test-Path "frontend-angular/node_modules")) {
            Write-Host "Installing npm dependencies..." -ForegroundColor Yellow
            Set-Location frontend-angular
            npm install
            Set-Location ..
            Write-Host "✓ Dependencies installed" -ForegroundColor Green
        } else {
            Write-Host "✓ Dependencies already installed" -ForegroundColor Green
        }
        
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "Starting Both Services..." -ForegroundColor Yellow
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "Backend: http://localhost:8080/api" -ForegroundColor Green
        Write-Host "Frontend: http://localhost:4200" -ForegroundColor Green
        Write-Host ""
        Write-Host "Starting backend in background..." -ForegroundColor Yellow
        
        # Start backend in new window
        Start-Process powershell -ArgumentList "-NoExit", "-Command", "mvn spring-boot:run"
        
        Write-Host "✓ Backend started in new window" -ForegroundColor Green
        Write-Host ""
        Write-Host "Waiting 10 seconds for backend to initialize..." -ForegroundColor Yellow
        Start-Sleep -Seconds 10
        
        Write-Host "Starting frontend..." -ForegroundColor Yellow
        Set-Location frontend-angular
        npm start
    }
    "4" {
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "Building for Production..." -ForegroundColor Yellow
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host ""
        
        # Build backend
        Write-Host "Building backend JAR..." -ForegroundColor Yellow
        mvn clean package -DskipTests
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✓ Backend built successfully" -ForegroundColor Green
            Write-Host "JAR location: target/food_order_deliverysystem-0.0.1-SNAPSHOT.jar" -ForegroundColor Cyan
        } else {
            Write-Host "✗ Backend build failed" -ForegroundColor Red
            exit 1
        }
        
        Write-Host ""
        
        # Build frontend
        Write-Host "Building frontend..." -ForegroundColor Yellow
        Set-Location frontend-angular
        npm run build --configuration production
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✓ Frontend built successfully" -ForegroundColor Green
            Write-Host "Build location: frontend-angular/dist/" -ForegroundColor Cyan
        } else {
            Write-Host "✗ Frontend build failed" -ForegroundColor Red
            exit 1
        }
        
        Set-Location ..
        
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "Production Build Complete!" -ForegroundColor Green
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "To run in production:" -ForegroundColor Yellow
        Write-Host "Backend: java -jar target/food_order_deliverysystem-0.0.1-SNAPSHOT.jar" -ForegroundColor White
        Write-Host "Frontend: Deploy dist/ folder to web server" -ForegroundColor White
    }
    "5" {
        Write-Host "Exiting..." -ForegroundColor Yellow
        exit 0
    }
    default {
        Write-Host "Invalid choice. Exiting..." -ForegroundColor Red
        exit 1
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Setup Complete!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
