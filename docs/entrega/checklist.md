# Checklist de entrega — reserva-salas

Data da revisão: 10/11/2026. Branch de integração: `feat/interface-qualidade`, baseada em `feat/backend`. Esta lista acompanha a entrega incremental; não representa aceite formal nem liberação para produção.

| Item | Estado/evidência | Próxima ação |
|---|---|---|
| Backend | Publicado na branch `feat/backend` | Revisão humana da integração |
| Interface | Commit separado publicado em `feat/interface-qualidade` | Inspeção visual desktop/mobile |
| Testes | 24 testes locais, zero falhas/erros; gate JaCoCo aprovado | Revisão por pares |
| Persistência | Ensaio local com JAR e H2 descartável passou após reinício | Repetir em clone novo, se exigido |
| Carga | 200 consultas locais, p95 73,47 ms, 0 erros, disputa 1 criação/9 conflitos | Interpretar somente como ensaio local |
| Especificação | [Requisitos](../requisitos.md), [API](../api.md), [arquitetura](../arquitetura.md), [RTM](../rtm.md) | Confirmar premissas com o grupo |
| Documentação | Planos e resultados deste checkout | Executar `node scripts/verify-docs.mjs` com todos os arquivos presentes |
| CI | Workflow ainda a verificar no SHA final | Conferir execução e artifacts após publicação |
| Revisão por pares | Não registrada | Revisar diff e registrar observações |
| SonarCloud | Não configurado/verificado | Não declarar Quality Gate |
| Release/tag | Nenhuma criada | Não criar tag nem fazer merge sem autorização |
| Produção | Não avaliada | Não tratar esta entrega como pronta para produção |

Comandos de compilação local e execução reproduzível serão documentados no [README](../../README.md). O estado final da branch deve ser conferido no [GitHub](https://github.com/devViniSoares/reserva-salas/tree/feat/interface-qualidade).
