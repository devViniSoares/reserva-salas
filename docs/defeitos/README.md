# Defeitos e pendências

Revisão do estado em 10/11/2026. A suíte local não revelou falhas nos 24 testes executados. Isso não equivale a uma auditoria completa nem demonstra ausência de defeitos.

| ID | Tipo | Descrição | Estado | Ação |
|---|---|---|---|---|
| P01 | Pendência de validação | Inspeção visual desktop/mobile não executada | Aberta | Seguir o roteiro em [plano de testes](../testes/plano.md) |
| P02 | Pendência de entrega | CI da branch e artifacts ainda precisam ser conferidos no SHA final | Aberta | Verificar workflow após publicação; não reutilizar CI de outro repositório |
| P03 | Pendência de revisão | Revisão por pares e aceite das premissas não registrados | Aberta | Revisar diff da branch `feat/interface-qualidade` contra `feat/backend` |
| P04 | Limitação arquitetural | Exclusão de reservas é sincronizada em uma instância da aplicação | Documentada | Não inferir garantia distribuída ou múltiplas JVMs |
| P05 | Limitação de implantação | Servidor local sem avaliação de HTTPS, rate limiting ou hardening para internet | Documentada | Não expor a produção sem análise operacional e de segurança |
| P06 | Limitação funcional | Listas não possuem paginação | Documentada | Avaliar conforme volume e uso reais |
| P07 | Pendência de entrega | Clone limpo não foi validado | Aberta | Executar o procedimento em [validação de clone](../entrega/validacao-clone.md) |

Não foram copiados defeitos, correções ou estados de validação do projeto de referência. As pendências acima são riscos e tarefas de verificação, não bugs confirmados.
