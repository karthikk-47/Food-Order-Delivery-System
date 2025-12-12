#!/usr/bin/env powershell
# Backend Startup Script

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "FoodApp - Backend Server Startup" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if Maven is installed
Write-Host "Checking Maven installation..." -ForegroundColor Yellow
mvn -v

if ($LASTEXITCODE -ne 0) {
    Write-Host "Maven is not installed. Please install Maven first." -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "Starting Spring Boot application..." -ForegroundColor Green
Write-Host "This will take a minute or two to start..." -ForegroundColor Yellow
Write-Host ""
Write-Host "Once started, the backend will be available at:" -ForegroundColor Cyan
Write-Host "  http://localhost:8082" -ForegroundColor Green
Write-Host ""

cd C:\Users\Abcom\Documents\Projects\deliveryexecutive

# Start the application
mvn spring-boot:run -DskipTests

Write-Host ""
Write-Host "Backend shutdown" -ForegroundColor Yellow
