@echo off 
:: Created at 2020/7/21 14:41.
:: @author Liangcheng Juves

:: Clean.
if exist ..\gous\target (
    rmdir ..\gous\target /s/q
)

if exist ..\gous\src\main\resources\jniLibs\gous.dll (
    del ..\gous\src\main\resources\jniLibs\gous.dll /s/q
)

if exist ..\gous.dll (
    del ..\gous.dll /s/q
)

if exist gous.dll (
    del gous.dll /s/q
)

if exist gous.exp (
    del gous.exp /s/q
)

if exist gous.lib (
    del gous.lib /s/q
)

if exist gous.obj (
    del gous.obj /s/q
)

:: Begin build.
cl -c gous.c -I include
link -DLL -out:gous.dll gous.obj

:: Move.
if exist gous.dll (
    mv gous.dll ..\gous\src\main\resources\jniLibs
)

echo "All is OK!"
