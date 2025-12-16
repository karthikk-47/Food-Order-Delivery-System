#!/usr/bin/env powershell
# Backend Startup Script

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "FoodApp - Backend Server Startup" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Load environment variables from .env file
Write-Host "Loading environment variables from .env..." -ForegroundColor Yellow
$envFilePath = ".\.env"
if (Test-Path $envFilePath) {
    $envVars = Get-Content $envFilePath | Where-Object { 
        $_ -notmatch '^\s*#' -and $_.Trim() -ne '' 
    }
    foreach ($line in $envVars) {
        $parts = $line -split '=', 2
        if ($parts.Length -eq 2) {
            $name = $parts[0].Trim()
            $value = $parts[1].Trim()
            [System.Environment]::SetEnvironmentVariable($name, $value, [System.EnvironmentVariableTarget]::Process)
        }
    }
    Write-Host "Environment variables loaded successfully!" -ForegroundColor Green
} else {
    Write-Host "Warning: .env file not found. Using defaults from application.properties" -ForegroundColor Yellow
}
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
