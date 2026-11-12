# Relatório de testes — reserva-salas

## Verificação do código

Executada em **10/11/2026 às 15:29 -03:00**.

| Item | Resultado |
|---|---|
| Fonte executada | `46cc95df717dd7a5c276150316edeca70e6b7430` |
| Ambiente | Windows, Oracle JDK 23.0.2, Maven Wrapper, H2 em memória |
| Comando | `.\mvnw.cmd -B verify` |
| Resultado | BUILD SUCCESS |
| Testes | 24: 21 em `RecursosTest` e 3 em `BootstrapTest` |
| Falhas, erros e ignorados | 0, 0 e 0 |
| JaCoCo | Gate de cobertura de linhas de 80% aprovado |

O relatório foi gerado nesta execução no checkout `reserva-salas`; não reutiliza resultados do projeto de referência. Os relatórios detalhados do Maven ficam em `target/` e não são versionados.

## Persistência

O [resultado local de persistência](persistencia-local.json) foi produzido pelo script [persistencia.mjs](../../scripts/persistencia.mjs) em 10/11/2026, contra o JAR real e um H2 em arquivo descartável. Recurso, reserva e login foram preservados após reinício, sem senha de bootstrap na segunda execução. O SHA medido está no JSON.

## Limitações

O teste de clone limpo e a inspeção visual manual não foram executados. Não houve execução de CI neste relatório; portanto, estes resultados não representam um workflow remoto nem validam uma implantação.
