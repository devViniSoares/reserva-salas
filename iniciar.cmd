@echo off
powershell.exe -NoProfile -ExecutionPolicy Bypass -File "%~dp0scripts\iniciar.ps1"
if errorlevel 1 (
  echo.
  echo Nao foi possivel iniciar a aplicacao. Veja a mensagem acima.
  pause
)
