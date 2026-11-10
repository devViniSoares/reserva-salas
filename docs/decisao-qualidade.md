# Decisão de qualidade

**Decisão: bloquear release formal e uso em produção; preparar a branch para revisão humana.**

Data: 10/11/2026. Branch analisada: `feat/interface-qualidade`, baseada em `feat/backend`. Nenhuma tag, release ou merge foi criado. Esta decisão é um registro técnico, não uma assinatura ou aprovação humana.

## Critérios

| Critério | Evidência | Estado |
|---|---|---|
| Build, testes e gate JaCoCo | [Relatório local](testes/relatorio.md), 24 testes e gate mínimo atendido | Aprovado localmente |
| Persistência após reinício | [JSON local](testes/persistencia-local.json) | Aprovado no ambiente medido |
| Carga local | [Resultado](performance/resultado-01.md), 200 respostas válidas e disputa 1/9 | Aprovado nos critérios locais |
| Documentação | Planos e documentos revisados; verificação automática deve ser executada com o conjunto completo | Pendente de execução final |
| CI do SHA final | Ainda não avaliada neste documento | Pendente |
| Clone limpo | [Procedimento e estado](entrega/validacao-clone.md) | Não executado |
| Revisão por pares/aceite de requisitos | Sem registro | Pendente |
| Inspeção visual e acessibilidade | [Roteiro](testes/plano.md) | Pendente |
| SonarCloud/Quality Gate externo | Sem configuração ou resultado comprovado | Não atendido |

## Riscos não aceitos

Não há aceite de risco atribuído a integrante. Permanecem documentados: sincronização de reserva limitada a uma JVM; ausência de avaliação de exposição pública, HTTPS e rate limiting; listas sem paginação; e verificações manuais/CI ainda pendentes. Veja [registro de pendências](defeitos/README.md).

## Autoria e aprovação

Este registro não inventa nomes, assinaturas, aceite de requisitos ou revisão por pares. A equipe deve registrar sua própria aprovação após avaliar o diff, os resultados e os riscos.

## Ações de publicação

O objetivo desta etapa é disponibilizar commits separados na branch `feat/interface-qualidade` para revisão. Não abrir PR automaticamente, não fazer merge na `main`, não criar tags e não declarar prontidão de produção.
