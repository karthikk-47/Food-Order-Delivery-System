# Rewrote to be robust in environments without network access; provide instructions if download fails
$classesPath = Join-Path $PSScriptRoot "..\jar_extract\BOOT-INF\classes"
if (-not (Test-Path $classesPath)) { Write-Error "Classes path not found: $classesPath" ; exit 1 }
$classesPath = (Resolve-Path $classesPath).ProviderPath
$classes = Get-ChildItem -Path $classesPath -Recurse -Filter *.class -File -ErrorAction SilentlyContinue
$count = if ($classes) { $classes.Count } else { 0 }
$size = if ($classes) { ($classes | Measure-Object -Property Length -Sum).Sum } else { 0 }
Write-Host "Found $count .class files (total size: $([math]::Round($size/1MB,2)) MB)"

$outDir = Join-Path $PSScriptRoot "..\recovered-src-full"
if (Test-Path $outDir) { $bak = "$outDir.bak.$((Get-Date).ToString('yyyyMMdd-HHmmss'))" ; Write-Host "Backing up existing recovered folder to: $bak" ; Copy-Item -Path $outDir -Destination $bak -Recurse -Force }

$toolsDir = Join-Path $PSScriptRoot "tools"
if (-not (Test-Path $toolsDir)) { New-Item -ItemType Directory -Path $toolsDir | Out-Null }
$cfrPath = Join-Path $toolsDir "cfr.jar"

if (-not (Test-Path $cfrPath)) {
    Write-Host "cfr.jar not found at $cfrPath. Automatic download may not be available in this environment."
    Write-Host "Please download CFR manually (recommended): https://www.benf.org/other/cfr/ and save it as $cfrPath"
    Write-Host "Once downloaded, re-run this script: PowerShell -ExecutionPolicy Bypass -File .\scripts\run_cfr_decompile.ps1"
    exit 2
} else { Write-Host "Found CFR at $cfrPath" }

$libDir = Join-Path $PSScriptRoot "..\jar_extract\BOOT-INF\lib"
$libcp = ""
if (Test-Path $libDir) {
    $jars = Get-ChildItem -Path $libDir -Filter *.jar -File -ErrorAction SilentlyContinue | ForEach-Object { $_.FullName }
    if ($jars) { $libcp = $jars -join ';' }
}
if ($libcp) { Write-Host "Using extraclasspath with $($jars.Count) jars" } else { Write-Host "No extra jars found; proceeding without extraclasspath" }

if (Test-Path $outDir) { Remove-Item -Recurse -Force $outDir }
New-Item -ItemType Directory -Path $outDir | Out-Null

$javaCmd = "java -Xmx2G -jar `"$cfrPath`" --outputdir `"$outDir`""
if ($libcp) { $javaCmd += " --extraclasspath `"$libcp`"" }
$javaCmd += " `"$classesPath`""
Write-Host "Running: $javaCmd"
try { Invoke-Expression $javaCmd } catch { Write-Error "Failed to run CFR: $($_.Exception.Message)" ; exit 3 }

$javaFiles = Get-ChildItem -Path $outDir -Recurse -Filter *.java -File -ErrorAction SilentlyContinue
$jcount = if ($javaFiles) { $javaFiles.Count } else { 0 }
Write-Host "Decompiled Java files: $jcount"

if ($jcount -gt 0) {
    Write-Host "Sample recovered files:"
    $javaFiles | Select-Object -First 20 | ForEach-Object { Write-Host $_.FullName }
}

$zip = Join-Path $PSScriptRoot "..\recovered-src-full.zip"
if (Test-Path $zip) { Remove-Item $zip -Force }
Compress-Archive -Path (Join-Path $outDir "*") -DestinationPath $zip -Force
Write-Host "Created zip: $zip (size: $((Get-Item $zip).Length/1MB) MB)"
Write-Host "Done"