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
| CI | A verificar na branch publicada | Nenhuma execução de CI é afirmada neste índice |
| Inspeção visual | Não executada | Ver [plano manual](../testes/plano.md) |

As medições locais identificam no JSON o SHA do código que foi testado (`46cc95df717dd7a5c276150316edeca70e6b7430`). Os commits posteriores até a preparação deste índice alteram documentação, não o backend ou os scripts medidos. Os resultados são restritos ao ambiente local e não comprovam qualidade de produção.

## Revisão e decisão

- Branch de integração: [`feat/interface-qualidade`](https://github.com/devViniSoares/reserva-salas/tree/feat/interface-qualidade), derivada de [`feat/backend`](https://github.com/devViniSoares/reserva-salas/tree/feat/backend).
- Revisão por pares: pendente.
- SonarCloud/Quality Gate externo: não verificado.
- Release/tag: nenhuma.
- [Relatório de qualidade](../relatorio-qualidade.md) e [decisão](../decisao-qualidade.md) registram os limites atuais.
