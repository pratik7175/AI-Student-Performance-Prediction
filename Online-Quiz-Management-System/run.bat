@echo off
cd /d "%~dp0"
echo Starting Online Quiz Management System...
where java >nul 2>nul
if errorlevel 1 (
  echo Java was not found. Install JDK 17 or newer and add it to PATH.
  pause
  exit /b 1
)
where mvn >nul 2>nul
if errorlevel 1 (
  echo Maven was not found. Install Maven 3.9+ and add it to PATH.
  pause
  exit /b 1
)
if not exist pom.xml (
  echo pom.xml is missing. Please re-extract the complete project ZIP.
  pause
  exit /b 1
)
mvn spring-boot:run
if errorlevel 1 pause
