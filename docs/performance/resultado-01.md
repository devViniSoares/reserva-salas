# Resultado de carga 01 — reserva-salas local

Executado em **10/11/2026 às 15:28 -03:00**, no checkout `reserva-salas`. O [JSON bruto](resultado-01.json) registra o SHA executado, ambiente e respostas medidas.

| Medida | Resultado |
|---|---|
| Ambiente | Windows, Node v25.4.0, Oracle JDK 23.0.2, servidor local e H2 descartável |
| Aquecimento | 10 consultas |
| Consultas medidas | 200, concorrência 10 |
| Duração | 1377,94 ms |
| p50 | 66,53 ms |
| p95 | 73,47 ms |
| Máximo | 80,94 ms |
| Vazão | 145,14 requisições/s |
| Respostas | 200 HTTP 200, zero erros |
| Disputa | 10 tentativas: 1 HTTP 201 e 9 HTTP 409 |
| Critérios locais | Aprovado: p95 ≤ 1500 ms, zero erros e apenas uma reserva criada |

Medição curta em uma instância e banco descartável; inclui o custo de autenticação e não comprova capacidade de produção, estabilidade prolongada ou execução em CI.
