# Changelog

## Não lançado

- Importada e revisada a interface web de reserva de salas e equipamentos.
- Adicionados testes de integração, segurança, regras de reserva e concorrência.
- Adicionados scripts de carga, persistência após reinício e validação documental.
- Documentados requisitos, API, arquitetura, RTM, planos, resultados e pendências.
- Preparado workflow Java/Node e imagem de contêiner.

Verificações locais próprias: `.\mvnw.cmd -B verify` com 24 testes aprovados; persistência após reinício aprovada; carga local com 200 respostas HTTP 200, p95 de 73,47 ms e disputa com uma criação e nove conflitos. Esses resultados não comprovam execução de CI, SonarCloud ou prontidão para produção.

Nenhum release candidate foi publicado. Revisão por pares, clone limpo e inspeção visual continuam pendentes.
