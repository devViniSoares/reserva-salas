package br.edu.senac.recursos;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.support.TransactionTemplate;

class SalasIniciaisTest {
    @Test void cadastraSalasUmaVezMesmoAposExclusao() {
        var source = new EmbeddedDatabaseBuilder().generateUniqueName(true)
            .setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build();
        try {
            var db = new JdbcTemplate(source);
            var salas = new SalasIniciais(db, new TransactionTemplate(new DataSourceTransactionManager(source)));
            var args = new DefaultApplicationArguments();

            salas.run(args);
            assertThat(db.queryForObject("SELECT COUNT(*) FROM recursos", Long.class)).isEqualTo(5);
            assertThat(db.queryForList("SELECT nome FROM recursos ORDER BY nome", String.class))
                .contains("Sala de Projetos", "Sala de Reuniões", "Auditório");

            db.update("DELETE FROM recursos WHERE nome=?", "Auditório");
            salas.run(args);
            assertThat(db.queryForObject("SELECT COUNT(*) FROM recursos", Long.class)).isEqualTo(4);
            assertThat(db.queryForObject("SELECT COUNT(*) FROM inicializacoes", Long.class)).isEqualTo(1);
        } finally {
            source.shutdown();
        }
    }

    @Test void preservaSalasDeBancoExistente() {
        var source = new EmbeddedDatabaseBuilder().generateUniqueName(true)
            .setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build();
        try {
            var db = new JdbcTemplate(source);
            db.update("INSERT INTO recursos(nome,tipo,localizacao,capacidade,descricao,ativo) VALUES (?,?,?,?,?,?)",
                "Sala já cadastrada", "SALA", "Bloco Real", 18, "Cadastro anterior", true);
            var salas = new SalasIniciais(db, new TransactionTemplate(new DataSourceTransactionManager(source)));

            salas.run(new DefaultApplicationArguments());
            assertThat(db.queryForList("SELECT nome FROM recursos", String.class)).containsExactly("Sala já cadastrada");
            assertThat(db.queryForObject("SELECT COUNT(*) FROM inicializacoes", Long.class)).isEqualTo(1);
        } finally {
            source.shutdown();
        }
    }
}
