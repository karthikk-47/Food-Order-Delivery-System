# Script to decompile Java class files using javap
$classesDir = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\target\classes"
$outputDir = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\recovered-src-proper"

# Create output directory if it doesn't exist
if (-not (Test-Path $outputDir)) {
    New-Item -Path $outputDir -ItemType Directory -Force
}

# Get all .class files recursively
$classFiles = Get-ChildItem -Path $classesDir -Recurse -Filter *.class

Write-Host "Found $($classFiles.Count) class files to decompile"

foreach ($classFile in $classFiles) {
    # Calculate relative path from classes directory
    $relativePath = $classFile.FullName.Substring($classesDir.Length + 1)
    
    # Convert class file path to package structure
    $packagePath = [System.IO.Path]::ChangeExtension($relativePath, ".java")
    $outputFile = Join-Path $outputDir $packagePath
    
    # Create directory structure if needed
    $outputDirPath = Split-Path $outputFile -Parent
    if (-not (Test-Path $outputDirPath)) {
        New-Item -Path $outputDirPath -ItemType Directory -Force | Out-Null
    }
    
    # Get the full class name (replace \ with . and remove .class)
    $className = $relativePath.Replace('\', '.').Replace('/', '.').Replace('.class', '')
    
    Write-Host "Decompiling $className to $outputFile"
    
    # Use javap to decompile the class
    & javap -cp $classesDir -p -c -s $className | Out-File -FilePath $outputFile -Encoding UTF8
}

Write-Host "Decompilation completed. Files saved to: $outputDir"
