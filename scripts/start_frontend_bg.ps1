$proj = Split-Path -Parent $PSScriptRoot
$script = Join-Path $proj 'start-frontend.ps1'
$logOut = Join-Path $proj 'frontend.log'
$logErr = Join-Path $proj 'frontend.err'
if (Test-Path $logOut) { Remove-Item $logOut -Force }
if (Test-Path $logErr) { Remove-Item $logErr -Force }
$args = "-NoProfile -ExecutionPolicy Bypass -Command \"Set-Location '$proj'; & '$script'\""
$proc = Start-Process -FilePath 'powershell.exe' -ArgumentList $args -RedirectStandardOutput $logOut -RedirectStandardError $logErr -WindowStyle Hidden -PassThru
$pidFile = Join-Path $proj 'frontend.pid'
Set-Content -Path $pidFile -Value $proc.Id
Write-Host "Started frontend PID=$($proc.Id), logs: $logOut, $logErr"