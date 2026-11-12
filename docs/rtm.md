# Matriz de rastreabilidade

IDs definidos em [requisitos](requisitos.md). Testes em [RecursosTest.java](../src/test/java/br/edu/senac/recursos/RecursosTest.java). Resultados do projeto de referência não são evidência deste repositório; resultados locais são vinculados somente após serem gerados aqui.

| Requisito | Implementação | Teste/evidência | Situação |
|---|---|---|---|
| RF01, RNF01 | [Seguranca](../src/main/java/br/edu/senac/recursos/Seguranca.java), [Usuarios](../src/main/java/br/edu/senac/recursos/Usuarios.java) | acessoPublicoESeguranca, csrfObrigatorio, usuarioNaoAlteraRecursos, senhasPersistidasComHash | Passou localmente |
| RF02 | Usuarios e Api | cadastroNaoPermiteEscaladaNemDuplicata, validacaoCadastro | Passou localmente |
| RF03, RN04, RN05 | [Agenda](../src/main/java/br/edu/senac/recursos/Agenda.java) | gestorGerenciaRecursos, validaRecursoEParametros, bloqueiaSobreposicaoEConservaHistorico | Passou localmente |
| RF04 | Agenda e Api | gestorGerenciaRecursos | Passou localmente |
| RF05 | Agenda.disponivel | disponibilidadeEReservaComCancelamento | Passou localmente |
| RF06, RN01 | Agenda.reservar | bloqueiaSobreposicaoEConservaHistorico, concorrenciaNaoDuplicaReserva | Passou localmente |
| RN02 | Agenda.reservar | bloqueiaConflitoDoUsuarioEPermiteIntervaloAdjacente | Passou localmente |
| RN03 | Agenda.validarIntervalo | validaDuracaoDaReserva, naoReservaNoPassadoNemSemDatas | Passou localmente |
| RF07 | Agenda.reservas | privacidadeEPermissoesDeCancelamento | Passou localmente |
| RF08, RN06 | Agenda.cancelar | disponibilidadeEReservaComCancelamento, privacidadeEPermissoesDeCancelamento | Passou localmente |
| RF09 | Api.usuarios e novoUsuario | somenteAdminGerenciaUsuarios | Passou localmente |
| RF10, RNF06 | [Interface](../src/main/resources/static/index.html), [JavaScript](../src/main/resources/static/app.js) | Teste HTTP da página e sintaxe JS no lote de Interface; inspeção visual não executada | Parcial |
| RN07 | Agenda.reservar | concorrenciaNaoDuplicaReserva: reserva ocupa todo o recurso | Passou localmente |
| RNF02 | Monitor e TransactionTemplate | concorrenciaNaoDuplicaReserva: 12 disputas, 1 sucesso | Passou localmente |
| RNF03 | [pom.xml](../pom.xml) | `.\mvnw.cmd -B verify`: 24 testes e gate JaCoCo aprovados localmente | Aprovado localmente; CI ainda não executada |
| RNF04 | [Script de carga](../scripts/carga.mjs) | Resultado local a ser gerado no lote de planos e resultados | Pendente |
| RNF05 | [Schema](../src/main/resources/schema.sql), Agenda | Testes de integração aprovados; execução do script de persistência a ser registrada no lote de planos e resultados | Parcial |

[BootstrapTest](../src/test/java/br/edu/senac/recursos/BootstrapTest.java) também valida senha inicial obrigatória, reutilização de administrador e ausência de SQL nas respostas de erro.
