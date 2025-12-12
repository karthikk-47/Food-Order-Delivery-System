# Script to decompile all Java class files using CFR decompiler
$classesDir = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\target\classes"
$outputDir = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\recovered-src-cfr"
$cfrJar = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\cfr.jar"

# Get all .class files recursively
$classFiles = Get-ChildItem -Path $classesDir -Recurse -Filter *.class

Write-Host "Found $($classFiles.Count) class files to decompile with CFR"

$decompiledCount = 0
$errorCount = 0

foreach ($classFile in $classFiles) {
    try {
        Write-Host "Decompiling $($classFile.FullName)"
        
        # Use CFR to decompile the individual class file
        & java -jar $cfrJar $classFile.FullName --outputdir $outputDir
        
        $decompiledCount++
    }
    catch {
        Write-Host "Error decompiling $($classFile.FullName): $($_.Exception.Message)" -ForegroundColor Red
        $errorCount++
    }
}

Write-Host "CFR decompilation completed."
Write-Host "Successfully decompiled: $decompiledCount files"
Write-Host "Errors: $errorCount files"
Write-Host "Files saved to: $outputDir"
