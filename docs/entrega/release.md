# Procedimento de release candidate

Este procedimento é apenas uma referência futura. Nenhum release candidate, tag ou merge na `main` foi criado ou autorizado nesta entrega.

## Pré-condições

1. Revisar por pares o diff de `feat/interface-qualidade` contra `feat/backend`.
2. Executar `.\mvnw.cmd -B verify` antes de cada commit.
3. Executar `node scripts/verify-docs.mjs` quando todos os documentos estiverem presentes.
4. Conferir CI verde e artifacts vinculados ao SHA exato; não reutilizar execuções do repositório de referência.
5. Realizar inspeção manual da interface e decidir as pendências registradas em [defeitos](../defeitos/README.md).
6. Obter aprovação explícita dos responsáveis antes de merge, tag ou publicação de release.

## Integridade das evidências

Associar cada resultado ao SHA que efetivamente foi testado. Uma execução anterior não comprova o estado de commits posteriores. Não declarar Quality Gate externo sem resultado verificável para o mesmo SHA. Dados sintéticos e bancos temporários não devem ser incluídos no pacote de entrega.

Tags, releases e merges estão fora do escopo atual. Se forem autorizados no futuro, criar uma nova tag imutável para cada candidato; nunca mover ou substituir tag existente.
