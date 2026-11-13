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
if (-not (Test-Path -LiteralPath $banco) -and [string]::IsNullOrEmpty($env:ADMIN_PASSWORD)) {
    do {
        $senhaSegura = Read-Host 'Defina a senha inicial do administrador (12 a 72 caracteres)' -AsSecureString
        $senha = [System.Net.NetworkCredential]::new('', $senhaSegura).Password
        $valida = $senha.Length -ge 12 -and $senha.Length -le 72 -and
            [Text.Encoding]::UTF8.GetByteCount($senha) -le 72
        if (-not $valida) { Write-Host 'A senha precisa ter de 12 a 72 caracteres e até 72 bytes UTF-8.' }
    } until ($valida)
    $env:ADMIN_PASSWORD = $senha
    $senha = $null
    $senhaSegura.Dispose()
}

Write-Host "Banco local: $banco"
Write-Host 'Abra http://127.0.0.1:8080 no navegador. Para parar, pressione Ctrl+C.'
& java -jar $jar
if ($LASTEXITCODE -ne 0) { throw 'A aplicação terminou com erro.' }
