@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist FULL_OUTPUT.TXT del FULL_OUTPUT.TXT
if exist SKIP_INTRO.TXT del SKIP_INTRO.TXT

REM compile the code into the bin folder
javac -encoding UTF-8 -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\amadeus\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the FULL_OUTPUT.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin amadeus.Amadeus < input.txt > FULL_OUTPUT.TXT

REM Skips the Amadeus Introduction for comparison
REM powershell -Command "Get-Content FULL_OUTPUT.TXT | Select-Object -Skip 46 | Set-Content SKIP_INTRO.TXT"

REM compare the output to the expected output
FC FULL_OUTPUT.TXT EXPECTED.TXT
IF ERRORLEVEL 1 (
    echo ********** TEST FAILED **********
    echo The contents of FULL_OUTPUT.TXT and EXPECTED.TXT do not match.
    exit /b 1
) ELSE (
    echo ********** TEST PASSED **********
    echo The contents of FULL_OUTPUT.TXT and EXPECTED.TXT match.
    exit /b 0
)