$classesDir = Join-Path $PSScriptRoot "..\jar_extract\BOOT-INF\classes"
# normalize absolute path and ensure trailing backslash
$classesDir = (Resolve-Path $classesDir).ProviderPath
if (-not $classesDir.EndsWith('\')) { $classesDir = $classesDir + '\' }
$outputDir = Join-Path $PSScriptRoot "..\recovered-src"
if (-not (Test-Path $outputDir)) { New-Item -ItemType Directory -Path $outputDir | Out-Null }
Get-ChildItem -Path $classesDir -Recurse -Filter *.class | ForEach-Object {
    # compute relative path to classesDir
    $relative = $_.FullName.Substring($classesDir.Length)
    # build output filename safely
    $safeName = ($relative -replace '\\', '_') + ".javap.txt"
    $out = Join-Path $outputDir $safeName
    Write-Host "Dumping $($_.FullName) -> $out"
    # form class name with dots and strip .class
    $className = ($relative -replace '\\','.') -replace '\.class$',''
    & javap -classpath $classesDir -public -s $className > $out 2>&1
}
Write-Host "Done decompiling to javap text files"