# Set Java 17 as the Java version for this session
$env:JAVA_HOME = "C:\Program Files\Microsoft\jdk-17.0.16.8-hotspot"
$env:PATH = "C:\Program Files\Microsoft\jdk-17.0.16.8-hotspot\bin;$env:PATH"

Write-Host "Using Java 17:" -ForegroundColor Green
java -version
Write-Host ""
Write-Host "Starting Spring Boot application..." -ForegroundColor Cyan
Write-Host ""

# Run Maven with Spring Boot
./mvnw spring-boot:run
