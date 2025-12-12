$proj = Split-Path -Parent $PSScriptRoot
$script = Join-Path $proj 'run-backend-java17.ps1'
$logOut = Join-Path $proj 'backend.log'
$logErr = Join-Path $proj 'backend.err'
if (Test-Path $logOut) { Remove-Item $logOut -Force }
if (Test-Path $logErr) { Remove-Item $logErr -Force }
$args = "-NoProfile -ExecutionPolicy Bypass -Command \"Set-Location '$proj'; & '$script'\""
$proc = Start-Process -FilePath 'powershell.exe' -ArgumentList $args -RedirectStandardOutput $logOut -RedirectStandardError $logErr -WindowStyle Hidden -PassThru
$pidFile = Join-Path $proj 'backend.pid'
Set-Content -Path $pidFile -Value $proc.Id
Write-Host "Started backend PID=$($proc.Id), logs: $logOut, $logErr"