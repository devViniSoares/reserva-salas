package br.edu.senac.recursos;

import static br.edu.senac.recursos.Modelos.*;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Order(2)
public class SalasIniciais implements ApplicationRunner {
    private static final String CHAVE = "salas_demonstracao_v1";
    private static final List<DadosRecurso> SALAS = List.of(
        new DadosRecurso("Sala de Projetos", Tipo.SALA, "Bloco A", 12, "Projetor e quadro branco", true),
        new DadosRecurso("Sala de Reuniões", Tipo.SALA, "Bloco A", 8, "Mesa de reunião e videoconferência", true),
        new DadosRecurso("Sala Multiuso", Tipo.SALA, "Bloco B", 30, "Espaço para oficinas e apresentações", true),
        new DadosRecurso("Laboratório de Informática", Tipo.SALA, "Bloco C", 24, "Computadores para atividades práticas", true),
        new DadosRecurso("Auditório", Tipo.SALA, "Bloco Principal", 80, "Palco, projetor e sistema de som", true)
    );

    private final JdbcTemplate db;
    private final TransactionTemplate tx;

    public SalasIniciais(JdbcTemplate db, TransactionTemplate tx) {
        this.db = db;
        this.tx = tx;
    }

    @Override public void run(ApplicationArguments args) {
        tx.executeWithoutResult(status -> {
            if (db.queryForObject("SELECT COUNT(*) FROM inicializacoes WHERE chave=?", Long.class, CHAVE) > 0) return;
            if (db.queryForObject("SELECT COUNT(*) FROM recursos", Long.class) == 0) {
                for (DadosRecurso sala : SALAS) {
                    db.update("INSERT INTO recursos(nome,tipo,localizacao,capacidade,descricao,ativo) VALUES (?,?,?,?,?,?)",
                        sala.nome(), sala.tipo().name(), sala.localizacao(), sala.capacidade(), sala.descricao(), sala.ativo());
                }
            }
            db.update("INSERT INTO inicializacoes(chave) VALUES (?)", CHAVE);
        });
    }
}
