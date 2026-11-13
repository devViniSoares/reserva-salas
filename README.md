# Reserva de Salas

Aplicação Java para reservar salas e equipamentos, com API REST e interface em português. Repositório: [devViniSoares/reserva-salas](https://github.com/devViniSoares/reserva-salas).

## Funcionalidades

- Cadastro e login com perfis Usuário, Gestor e Administrador.
- Catálogo de recursos com busca, filtro e administração.
- Cinco salas de demonstração cadastradas automaticamente na primeira execução com banco vazio.
- Disponibilidade, reservas futuras de até 12 horas e cancelamento com histórico.
- Proteção contra conflitos de recurso e sobreposição das reservas do usuário.
- Interface web, autenticação HTTP Basic e proteção CSRF.

## Branches

- `feat/backend`: backend publicado pelo Vini.
- `feat/interface-qualidade`: interface, testes, documentação, scripts, contêiner e CI, baseada em `feat/backend`.

Os commits desta entrega estão separados por lote. A branch é destinada à revisão; não houve merge na `main`, tag ou PR automático.

## Pré-requisitos

- JDK 21 ou superior, com `JAVA_HOME` apontando para o JDK.
- Internet no primeiro build para obter Maven Wrapper e dependências.
- Node.js 22 ou superior para scripts de documentação e carga.
- Porta 8080 livre para execução local.

## Clonar, testar e validar documentos

Linux/macOS/Git Bash:

```bash
git clone --branch feat/interface-qualidade https://github.com/devViniSoares/reserva-salas.git
cd reserva-salas
./mvnw -B verify
node scripts/verify-docs.mjs
```

Windows PowerShell:

```powershell
git clone --branch feat/interface-qualidade https://github.com/devViniSoares/reserva-salas.git
Set-Location reserva-salas
.\mvnw.cmd -B verify
node scripts/verify-docs.mjs
```

O `verify` compila, executa os testes e empacota o JAR em `target/`. O gate JaCoCo exige pelo menos 80% de linhas cobertas. Os testes usam H2 em memória e dados sintéticos. Não é necessário instalar Maven, Docker nem banco externo.

## Executar

Para a demonstração local, a conta administrativa é fixa: e-mail `admin@exemplo.test` e senha `AdminDemo2026!`. A aplicação grava apenas o hash da senha no banco e ajusta a conta existente para essa senha ao iniciar.

No Windows, abra `iniciar.cmd` com dois cliques. Ele compila o projeto quando necessário e inicia o Java na pasta do projeto. Depois, abra `http://127.0.0.1:8080` e entre com a conta administrativa acima.

PowerShell:

```powershell
java -jar target/organizacao-recursos-1.0.0-SNAPSHOT.jar
```

Abra `http://127.0.0.1:8080`. O primeiro acesso cria o administrador; usuários podem cadastrar-se pela interface. Os dados são armazenados em `data/recursos.mv.db`. Faça backup com a aplicação parada e não abra o mesmo banco em várias instâncias.

Na primeira execução com a tabela de recursos vazia, o sistema cadastra Sala de Projetos, Sala de Reuniões, Sala Multiuso, Laboratório de Informática e Auditório. A inicialização é registrada no banco e não se repete após reiniciar ou excluir uma sala. Um banco que já tenha recursos é preservado sem incluir essas salas de demonstração. Cada instalação local recebe seu próprio banco; reservas e cadastros feitos em outra máquina não são copiados pelo Git.

| Variável | Padrão | Uso |
|---|---|---|
| `DB_URL` | `jdbc:h2:file:./data/recursos;DB_CLOSE_ON_EXIT=FALSE;WRITE_DELAY=0` | URL do H2 |
| `DB_PASSWORD` | vazio | Senha opcional do banco |
| `PORT` | `8080` | Porta HTTP |
| `BIND_ADDRESS` | `127.0.0.1` | Endereço de escuta |

O padrão local limita a escuta a localhost. A senha administrativa fixa está publicada no repositório; use esta configuração somente para demonstração local. HTTP Basic, falta de rate limiting e sincronização limitada a uma instância impedem tratar este protótipo como pronto para exposição pública ou produção.

## Contêiner

Com Docker instalado, a imagem compila e executa o JAR como usuário não root. A forma recomendada usa Compose e o volume persistente `reserva-salas-main-data`. No PowerShell:

```powershell
docker compose up --build -d
docker compose logs -f
```

A aplicação fica em `http://127.0.0.1:8080`. Para atualizar após mudanças na branch, execute `docker compose up --build -d`; a imagem será reconstruída e o volume preservado. As cinco salas de demonstração são inseridas quando a base está vazia, no primeiro início que inclui essa funcionalidade. Em uma base já existente, os recursos cadastrados são mantidos.

Para migrar de um contêiner iniciado manualmente com o nome `reserva-salas-main`, pare e remova somente o contêiner antes de iniciar pelo Compose. O volume de dados tem o mesmo nome e será reutilizado:

```powershell
docker stop reserva-salas-main
docker rm reserva-salas-main
docker compose up --build -d
```

`docker compose down` mantém o volume; não use `docker compose down --volumes` se quiser conservar o banco. A conta administrativa de demonstração usa as credenciais fixas descritas acima, inclusive em um banco já existente.

O contêiner escuta internamente em `0.0.0.0` para permitir o encaminhamento de porta do Docker; não publique o serviço na rede sem configurar os controles de segurança e operação necessários.

## Scripts de validação

Execute os ensaios somente contra instância e banco descartáveis. O script de carga cria dados sintéticos; defina `BASE_URL` para o servidor de teste:

```powershell
$env:DB_URL = 'jdbc:h2:mem:carga;DB_CLOSE_DELAY=-1'
$env:PORT = '8081'
$processoCarga = $null
try {
    $processoCarga = Start-Process -FilePath "$env:JAVA_HOME\bin\java.exe" -ArgumentList '-jar','target/organizacao-recursos-1.0.0-SNAPSHOT.jar' -PassThru
    $env:BASE_URL = 'http://127.0.0.1:8081'
    # Aguarde /api/saude responder antes de iniciar a carga.
    node scripts/carga.mjs
} finally {
    if ($null -ne $processoCarga) { Stop-Process -Id $processoCarga.Id -ErrorAction SilentlyContinue }
    Remove-Item Env:DB_URL,Env:PORT,Env:BASE_URL -ErrorAction SilentlyContinue
}
```

Por padrão, a carga grava em `performance-local/resultado.json`, diretório ignorado pelo Git. O workflow publica resultados com o SHA avaliado. A execução de persistência (`node scripts/persistencia.mjs`) cria banco e logs temporários em `.tools/`; use porta 8082 livre ou configure `TEST_PORT`.

## Documentação

- [API](docs/api.md) · [Requisitos](docs/requisitos.md) · [RTM](docs/rtm.md)
- [Arquitetura](docs/arquitetura.md)
- [Plano e relatório de testes](docs/testes/plano.md) · [persistência](docs/testes/persistencia-local.json)
- [Plano e resultado de carga](docs/performance/plano.md) · [resultado](docs/performance/resultado-01.md)
- [Defeitos e pendências](docs/defeitos/README.md) · [relatório de qualidade](docs/relatorio-qualidade.md)
- [Checklist](docs/entrega/checklist.md) · [validação de clone](docs/entrega/validacao-clone.md) · [evidências](docs/evidencias/README.md)
- [Decisão de qualidade](docs/decisao-qualidade.md) · [procedimento de release](docs/entrega/release.md) · [changelog](CHANGELOG.md)

## CI

O [workflow](.github/workflows/ci.yml) roda Maven verify, valida documentação, testa persistência e executa carga em push para `main` e branches `feat/**`. Os artifacts são nomeados com o SHA avaliado. Verifique a execução na branch; resultados locais não substituem CI.

O job SonarCloud depende das variables `SONAR_PROJECT_KEY` e `SONAR_ORGANIZATION` e do secret `SONAR_TOKEN`. Sem as variables ele é ignorado, o que não equivale a Quality Gate aprovado. Não inclua tokens no código.

## Estrutura

```text
src/main/java/br/edu/senac/recursos/   API, segurança e regras
src/main/resources/                  interface, configuração e schema
src/test/                            testes de integração e concorrência
scripts/                             carga, persistência e validação documental
.github/workflows/ci.yml              automação Java e Node
docs/                                especificação, resultados e entrega
```
