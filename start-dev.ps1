# Development Startup Script
# Starts both backend and frontend servers

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Food Delivery App - Dev Startup" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if Java is installed
Write-Host "Checking Java installation..." -ForegroundColor Yellow
try {
    $javaVersion = java -version 2>&1 | Select-String "version"
    Write-Host "✓ Java found: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Java not found. Please install Java 17 or higher." -ForegroundColor Red
    exit 1
}

# Check if Node.js is installed
Write-Host "Checking Node.js installation..." -ForegroundColor Yellow
try {
    $nodeVersion = node --version
    Write-Host "✓ Node.js found: $nodeVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Node.js not found. Please install Node.js 18 or higher." -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Starting Backend Server" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

# Start backend in a new window
Start-Process powershell -ArgumentList "-NoExit", "-Command", "Write-Host 'Starting Spring Boot Backend...' -ForegroundColor Green; ./mvnw spring-boot:run"

Write-Host "✓ Backend server starting on http://localhost:8080" -ForegroundColor Green
Write-Host ""

# Wait a bit for backend to start
Write-Host "Waiting 10 seconds for backend to initialize..." -ForegroundColor Yellow
Start-Sleep -Seconds 10

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Starting Frontend Server" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

# Check if node_modules exists
if (-Not (Test-Path "delivery-executive-app/node_modules")) {
    Write-Host "Installing frontend dependencies..." -ForegroundColor Yellow
    Set-Location delivery-executive-app
    npm install
    Set-Location ..
}

# Start frontend in a new window
Start-Process powershell -ArgumentList "-NoExit", "-Command", "Write-Host 'Starting Angular Frontend...' -ForegroundColor Green; cd delivery-executive-app; npm start"

Write-Host "✓ Frontend server starting on http://localhost:4200" -ForegroundColor Green
Write-Host ""

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Servers Started Successfully!" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Backend:  http://localhost:8080/api" -ForegroundColor Green
Write-Host "Frontend: http://localhost:4200" -ForegroundColor Green
Write-Host "Dev Login: http://localhost:4200/dev-login" -ForegroundColor Yellow
Write-Host ""
Write-Host "Quick Test Users (Dev Login):" -ForegroundColor Cyan
Write-Host "  • Customer (USER)" -ForegroundColor White
Write-Host "  • Home Chef (HOMEMAKER)" -ForegroundColor White
Write-Host "  • Delivery Partner (DELIVERYEXECUTIVE)" -ForegroundColor White
Write-Host "  • Administrator (ADMIN)" -ForegroundColor White
Write-Host ""
Write-Host "Press Ctrl+C to stop this script" -ForegroundColor Yellow
Write-Host "Close the server windows to stop the servers" -ForegroundColor Yellow
Write-Host ""

# Keep script running
while ($true) {
    Start-Sleep -Seconds 60
}
