# Script to decompile all Java class files from jar_extract using CFR decompiler
$classesDir = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\jar_extract\BOOT-INF\classes"
$outputDir = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\recovered-src-jar"
$cfrJar = "c:\Users\Abcom\Documents\Projects\deliveryexecutive\cfr.jar"

# Get all .class files recursively
$classFiles = Get-ChildItem -Path $classesDir -Recurse -Filter *.class

Write-Host "Found $($classFiles.Count) class files to decompile with CFR from jar_extract"

$decompiledCount = 0
$errorCount = 0
$startTime = Get-Date

foreach ($classFile in $classFiles) {
    try {
        Write-Host "[$($decompiledCount + 1)/$($classFiles.Count)] Decompiling $($classFile.FullName)"
        
        # Use CFR to decompile the individual class file
        & java -jar $cfrJar $classFile.FullName --outputdir $outputDir
        
        $decompiledCount++
        
        # Show progress every 25 files
        if ($decompiledCount % 25 -eq 0) {
            $elapsed = (Get-Date) - $startTime
            $rate = [math]::Round($decompiledCount / $elapsed.TotalMinutes, 2)
            Write-Host "Progress: $decompiledCount/$($classFiles.Count) files completed ($rate files/minute)"
        }
    }
    catch {
        Write-Host "Error decompiling $($classFile.FullName): $($_.Exception.Message)" -ForegroundColor Red
        $errorCount++
    }
}

$endTime = Get-Date
$totalTime = $endTime - $startTime
$totalMinutes = [math]::Round($totalTime.TotalMinutes, 2)

Write-Host "CFR decompilation completed."
Write-Host "Successfully decompiled: $decompiledCount files"
Write-Host "Errors: $errorCount files"
Write-Host "Total time: $totalMinutes minutes"
Write-Host "Files saved to: $outputDir"
Write-Host "Average rate: $([math]::Round($decompiledCount / $totalMinutes, 2)) files/minute"
