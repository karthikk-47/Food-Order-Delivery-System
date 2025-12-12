#!/usr/bin/env powershell
# Frontend Startup Script

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "FoodApp - Frontend Server Startup" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if Node is installed
Write-Host "Checking Node.js installation..." -ForegroundColor Yellow
node -v
npm -v

if ($LASTEXITCODE -ne 0) {
    Write-Host "Node.js is not installed. Please install Node.js first." -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "Starting Angular development server..." -ForegroundColor Green
Write-Host "This will take a minute or two to start..." -ForegroundColor Yellow
Write-Host ""
Write-Host "Once started, the frontend will be available at:" -ForegroundColor Cyan
Write-Host "  http://localhost:4200" -ForegroundColor Green
Write-Host ""

cd C:\Users\Abcom\Documents\Projects\deliveryexecutive\frontend-angular

# Start the application
npm start

Write-Host ""
Write-Host "Frontend shutdown" -ForegroundColor Yellow
