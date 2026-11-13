$ErrorActionPreference = 'Stop'
$projeto = (Resolve-Path -LiteralPath (Join-Path $PSScriptRoot '..')).Path
Set-Location -LiteralPath $projeto

$jar = Join-Path $projeto 'target\organizacao-recursos-1.0.0-SNAPSHOT.jar'
$compilar = -not (Test-Path -LiteralPath $jar)
if (-not $compilar) {
    $dataJar = (Get-Item -LiteralPath $jar).LastWriteTimeUtc
    $fontes = @(Get-Item -LiteralPath (Join-Path $projeto 'pom.xml')) +
        @(Get-ChildItem -LiteralPath (Join-Path $projeto 'src\main') -Recurse -File)
    $compilar = @($fontes | Where-Object { $_.LastWriteTimeUtc -gt $dataJar }).Count -gt 0
}

if ($compilar) {
    Write-Host 'Preparando a aplicação...'
    & (Join-Path $projeto 'mvnw.cmd') -B -q -DskipTests package
    if ($LASTEXITCODE -ne 0) { throw 'Falha ao compilar a aplicação.' }
}

# O diretório é fixado antes de iniciar o Java para reutilizar o mesmo H2 local.
Remove-Item Env:DB_URL -ErrorAction SilentlyContinue
$env:BIND_ADDRESS = '127.0.0.1'
$env:PORT = '8080'
$banco = Join-Path $projeto 'data\recursos.mv.db'

Write-Host "Banco local: $banco"
Write-Host 'Abra http://127.0.0.1:8080 no navegador. Para parar, pressione Ctrl+C.'
& java -jar $jar
if ($LASTEXITCODE -ne 0) { throw 'A aplicação terminou com erro.' }
