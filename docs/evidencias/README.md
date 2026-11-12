# Índice de evidências — reserva-salas

Este índice contém resultados gerados neste repositório. Nenhum resultado de teste, cobertura, persistência, carga ou CI do projeto de referência é apresentado como evidência do `reserva-salas`.

## Execuções locais novas

| Verificação | Evidência | Escopo |
|---|---|---|
| Maven verify | [Relatório de testes](../testes/relatorio.md) | 24 testes, sem falhas/erros; gate JaCoCo ≥ 80% aprovado |
| Persistência | [JSON local](../testes/persistencia-local.json) | JAR executado duas vezes e H2 descartável; resultado aprovado |
| Carga | [Relatório](../performance/resultado-01.md) e [JSON](../performance/resultado-01.json) | 200 respostas 200, p95 73,47 ms, 0 erros, 1 criação e 9 conflitos |
| Planos | [Testes](../testes/plano.md), [carga](../performance/plano.md) | Critérios e limitações declarados |
| Clone novo | [Estado](../entrega/validacao-clone.md) | Não executado |
| CI | [Execução de push aprovada](https://github.com/devViniSoares/reserva-salas/actions/runs/42877720769) no SHA `44060b8a01c802ae248c8d0d4f49bb595b341a4d` | Testes, JaCoCo, documentação, persistência, carga e artifact aprovados; Sonar ignorado |
| Inspeção visual | Não executada | Ver [plano manual](../testes/plano.md) |

As medições locais identificam no JSON o SHA do código testado (`46cc95df717dd7a5c276150316edeca70e6b7430`). O CI do SHA `44060b8a01c802ae248c8d0d4f49bb595b341a4d` também passou, incluindo o validador documental. O artifact `evidencias-44060b8a01c802ae248c8d0d4f49bb595b341a4d` foi publicado pelo [workflow](https://github.com/devViniSoares/reserva-salas/actions/runs/42877720769) e expira em 08/02/2027. O job Sonar foi ignorado; não há Quality Gate externo aprovado. Esses resultados não são CI da `main` e não comprovam qualidade de produção.

## Revisão e decisão

- Branch de integração: [`feat/interface-qualidade`](https://github.com/devViniSoares/reserva-salas/tree/feat/interface-qualidade), derivada de [`feat/backend`](https://github.com/devViniSoares/reserva-salas/tree/feat/backend).
- Revisão por pares: pendente.
- SonarCloud/Quality Gate externo: não verificado.
- Release/tag: nenhuma.
- [Relatório de qualidade](../relatorio-qualidade.md) e [decisão](../decisao-qualidade.md) registram os limites atuais.
