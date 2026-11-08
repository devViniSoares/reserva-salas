# Reserva de Salas

Migração incremental do projeto Java de Organização de Recursos para o repositório [reserva-salas](https://github.com/devViniSoares/reserva-salas).

Nesta etapa há somente a base Java/Maven. **Interface, regras de reservas, segurança e testes ainda serão importados e revisados pelo grupo.** O projeto completo de referência está em [project_qualidade, branch pratica/entrega-final](https://github.com/devViniSoares/project_qualidade/tree/pratica/entrega-final), commit `f657387639c42635eb569dcf0ec5c0fc460f0e86`.

## Base publicada

- Java 21, Spring Boot e Maven Wrapper 3.9.11.
- Configuração inicial de dependências e JaCoCo.
- Classe de entrada `br.edu.senac.recursos.RecursosApplication`.
- Arquivos de Git para excluir bancos locais, senhas, dependências baixadas e builds.

Não foram copiados bancos, senhas, `target/`, `.tools/`, clones auxiliares ou histórico Git do repositório anterior.

## Conferir a compilação

Instale JDK 21 ou 23 e configure JAVA_HOME. No PowerShell, ajustando o caminho do JDK se necessário:

```powershell
git clone --branch pratica/base-java https://github.com/devViniSoares/reserva-salas.git
Set-Location reserva-salas
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
.\mvnw.cmd -B verify
```

Esta base ainda não contém testes. BUILD SUCCESS nesta etapa confirma a compilação e o empacotamento, não a validação funcional nem a cobertura da aplicação completa. O Maven pode ignorar o JaCoCo por ausência de dados de execução.

## Divisão planejada

| Responsável | Branch prevista | Escopo |
|---|---|---|
| Vini | `pratica/backend` | Modelos, schema/configuração, usuários, segurança, agenda e API |
| Ryan | `pratica/interface-qualidade` | Interface, testes, scripts, CI, contêiner e documentação atualizada |

A parte do Ryan começa a partir do backend publicado e conferido. Cada integrante deve revisar sua parte, executar as verificações e fazer os commits com sua própria conta. A migração reaproveita código existente assistido pelo agente; as contribuições novas e revisões devem ser descritas como realmente ocorreram.

O PR do repositório anterior permanece fechado. Nenhum PR novo, merge na main ou tag de release faz parte desta etapa. A main mantém apenas uma base de inicialização; o código inicial está na branch `pratica/base-java`.
