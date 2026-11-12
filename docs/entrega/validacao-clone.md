# Validação em clone novo

**Estado: não executada.**

As verificações registradas em [relatório de testes](../testes/relatorio.md), [persistência](../testes/persistencia-local.json) e [carga](../performance/resultado-01.md) foram feitas no checkout de trabalho deste repositório. Elas não são resultado de clone limpo.

Para validar um clone novo após a revisão, use a branch `feat/interface-qualidade`, configure JDK 21 ou superior e siga o README do próprio `reserva-salas`:

```powershell
git clone --branch feat/interface-qualidade https://github.com/devViniSoares/reserva-salas.git
Set-Location reserva-salas
.\mvnw.cmd -B verify
node scripts/verify-docs.mjs
```

Iniciar o JAR, validar persistência e anotar o SHA observado também são necessários para completar a verificação de entrega. Não copie `target/`, bancos, segredos ou resultados do repositório de referência. Até que o roteiro seja executado, não há evidência de clone novo.
