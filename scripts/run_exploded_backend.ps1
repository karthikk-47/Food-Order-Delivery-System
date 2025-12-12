$proj = Split-Path -Parent $PSScriptRoot
$classes = Join-Path $proj 'jar_extract\BOOT-INF\classes'
$lib = Join-Path $proj 'jar_extract\BOOT-INF\lib'
$out = Join-Path $proj 'backend_exploded.log'
$err = Join-Path $proj 'backend_exploded.err'

# Ensure java exists
try {
    $javaVersion = & java -version 2>&1 | Out-String
    Write-Host "java found: $($javaVersion -split "`n" | Select-Object -First 1)"
} catch {
    Write-Error "java not found on PATH. Please install JRE/JDK and ensure 'java' is on PATH."
    exit 1
}

# Build classpath using wildcard for lib/*.jar
$cp = "$classes;$lib\*"
Write-Host "Using classpath: $cp"

# Prepare logs
if (Test-Path $out) { Remove-Item $out -Force }
if (Test-Path $err) { Remove-Item $err -Force }

# Use ArgumentList array so Start-Process passes args correctly
$argList = @('-Xmx2G','-cp',$cp,'org.springframework.boot.loader.JarLauncher')

$proc = Start-Process -FilePath 'java' -ArgumentList $argList -WorkingDirectory $proj -RedirectStandardOutput $out -RedirectStandardError $err -WindowStyle Hidden -PassThru
Set-Content -Path (Join-Path $proj 'backend_exploded.pid') -Value $proc.Id
Write-Host "Started exploded backend PID=$($proc.Id), logs: $out, $err"