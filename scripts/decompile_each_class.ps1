$scriptRoot = $PSScriptRoot
$classesDir = Join-Path $scriptRoot "..\jar_extract\BOOT-INF\classes"
$classesDir = (Resolve-Path $classesDir).ProviderPath
$outDir = Join-Path $scriptRoot "..\recovered-src-full"
if (-not (Test-Path $outDir)) { New-Item -ItemType Directory -Path $outDir | Out-Null }
$cfr = Join-Path $scriptRoot "tools\cfr.jar"
if (-not (Test-Path $cfr)) { Write-Error "cfr.jar not found at $cfr; please place CFR jar there."; exit 1 }

# build extraclasspath
$libDir = Join-Path $scriptRoot "..\jar_extract\BOOT-INF\lib"
$libcp = ""
if (Test-Path $libDir) { $jars = Get-ChildItem -Path $libDir -Filter '*.jar' -File -ErrorAction SilentlyContinue | ForEach-Object { $_.FullName }; if ($jars) { $libcp = $jars -join ';' } }

$log = Join-Path $scriptRoot "decompile_each_class.log"
if (Test-Path $log) { Remove-Item $log -Force }

$failed = @()
$classes = Get-ChildItem -Path $classesDir -Recurse -Filter *.class -File | Sort-Object FullName
$total = $classes.Count
Write-Output "Found $total class files" | Tee-Object -FilePath $log -Append

$idx = 0
foreach ($c in $classes) {
    $idx++
    $cpath = $c.FullName
    Write-Output "[$idx/$total] Decompiling $cpath" | Tee-Object -FilePath $log -Append
    $args = @('-Xmx1G','-jar',$cfr,'--outputdir',$outDir)
    if ($libcp) { $args += @('--extraclasspath',$libcp) }
    $args += $cpath
    $proc = Start-Process -FilePath 'java' -ArgumentList $args -NoNewWindow -Wait -PassThru
    if ($proc.ExitCode -ne 0) {
        Write-Output "FAILED: $cpath (exit $($proc.ExitCode))" | Tee-Object -FilePath $log -Append
        $failed += $cpath
    }
}

Write-Output "Decompilation complete. Failed: $($failed.Count)" | Tee-Object -FilePath $log -Append
if ($failed.Count -gt 0) { $failed | Tee-Object -FilePath $log -Append }
Write-Output "Wrote log: $log"