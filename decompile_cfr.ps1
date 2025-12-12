# Script to decompile Java class files using CFR decompiler
$classesDir = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\target\classes"
$outputDir = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\recovered-src-cfr"
$cfrJar = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\cfr.jar"

# Create output directory if it doesn't exist
if (-not (Test-Path $outputDir)) {
    New-Item -Path $outputDir -ItemType Directory -Force
}

# Get all .class files recursively
$classFiles = Get-ChildItem -Path $classesDir -Recurse -Filter *.class

Write-Host "Found $($classFiles.Count) class files to decompile with CFR"

if ($classFiles.Count -eq 0) {
    Write-Host "No class files found to decompile"
    exit
}

# Create a temporary JAR file containing all class files
$tempJar = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\temp_classes.jar"
if (Test-Path $tempJar) {
    Remove-Item $tempJar -Force
}

# Create JAR file with class files
& jar cf $tempJar -C $classesDir .

Write-Host "Created temporary JAR: $tempJar"

# Use CFR to decompile the JAR file
Write-Host "Decompiling with CFR..."
& java -jar $cfrJar $tempJar --outputdir $outputDir --comments true --decodeenumswitch true --sugarenums true --stringbuilder true --stringbuffer true --arrayiter true --collectioniter true --innerclasses true --removeboilerplate true --removeinnerclasssynthetics true --lambdainline true --showops false --hidebridgemethods true --hidelambdas false

Write-Host "CFR decompilation completed. Files saved to: $outputDir"

# Clean up temporary JAR
if (Test-Path $tempJar) {
    Remove-Item $tempJar -Force
    Write-Host "Cleaned up temporary JAR: $tempJar"
}
