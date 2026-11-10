# Relatório de qualidade — reserva-salas

## Escopo e método

Este relatório resume as verificações feitas no checkout do repositório [reserva-salas](https://github.com/devViniSoares/reserva-salas). O backend foi integrado a partir da branch `feat/backend`; a interface e os artefatos de qualidade estão na `feat/interface-qualidade`. O projeto de referência serviu somente para localizar materiais a revisar. Seus resultados não foram usados como evidência.

## Evidências novas

| Atributo | Evidência | Limite |
|---|---|---|
| Correção e segurança da API | 24 testes Maven aprovados; cobertura de linhas acima do gate mínimo de 80% | Não é auditoria de segurança |
| Concorrência | Teste automatizado com 12 chamadas ao serviço: uma reserva aceita | Garantia limitada a uma instância |
| Persistência | [Ensaio com o JAR](testes/persistencia-local.json): recurso, reserva e login preservados após reinício | Banco H2 descartável, execução local |
| Desempenho | [Ensaio de carga](performance/resultado-01.md): 200 respostas HTTP 200, p95 73,47 ms, zero erros | Amostra curta; não extrapolar para produção |
| Interface | Página e JavaScript importados e revisados | Inspeção visual/accessibilidade manual pendente |
| Reprodutibilidade | `.\mvnw.cmd -B verify` aprovado neste checkout | Clone novo ainda não validado |
| CI | Workflow ainda será verificado após a publicação do lote de CI | Não há resultado remoto declarado aqui |
| Manutenibilidade | Componentes e testes Java presentes | SonarCloud não configurado/verificado |

Os resultados locais citados identificam o SHA efetivamente medido nos JSONs. O sucesso do build não foi interpretado como prova de CI ou de qualidade de produção.

## Parecer

Os testes locais apoiam a continuidade para revisão da branch `feat/interface-qualidade` baseada em `feat/backend`. A entrega **não está aprovada para release ou produção** enquanto permanecerem pendentes revisão por pares, verificação do workflow no SHA final, clone novo e inspeção visual. Consulte [pendências](defeitos/README.md), [índice de evidências](evidencias/README.md) e [decisão](decisao-qualidade.md).
