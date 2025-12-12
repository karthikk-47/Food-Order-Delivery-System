# Set-Env.ps1 - PowerShell script to set environment variables from .env file

# Path to your .env file
$envFilePath = ".\.env"

# Check if .env file exists
if (-not (Test-Path $envFilePath)) {
    Write-Host "Error: .env file not found at $envFilePath" -ForegroundColor Red
    Write-Host "Please copy .env.example to .env and update with your values"
    exit 1
}

# Read .env file
$envVars = Get-Content $envFilePath | Where-Object { 
    $_ -notmatch '^\s*#' -and $_.Trim() -ne '' 
}

# Set environment variables for current session
foreach ($line in $envVars) {
    $name, $value = $line -split '=', 2
    $name = $name.Trim()
    $value = $value.Trim()
    
    # Remove quotes if present
    if ($value.StartsWith('"') -and $value.EndsWith('"')) {
        $value = $value.Substring(1, $value.Length - 2)
    }
    
    [System.Environment]::SetEnvironmentVariable($name, $value, [System.EnvironmentVariableTarget]::Process)
    Write-Host "Set $name" -ForegroundColor Green
}

Write-Host "`nEnvironment variables set successfully!" -ForegroundColor Green
Write-Host "Note: These variables are only set for the current PowerShell session."
Write-Host "To set them permanently, add them to your system's environment variables."
