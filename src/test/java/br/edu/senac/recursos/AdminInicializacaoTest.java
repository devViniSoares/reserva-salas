package br.edu.senac.recursos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import br.edu.senac.recursos.Modelos.Perfil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AdminInicializacaoTest {
    private static final String EMAIL = "admin@exemplo.test";
    private static final String SENHA_ANTIGA = "senha-antiga-teste";
    private static final String SENHA_CONFIGURADA = "senha-configurada-teste";

    private final EmbeddedDatabase banco =
        new EmbeddedDatabaseBuilder().generateUniqueName(true)
            .setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final Usuarios usuarios = new Usuarios(new JdbcTemplate(banco), encoder);

    @AfterEach
    void fechaBanco() {
        banco.shutdown();
    }

    @Test
    void atualizaSenhaDoAdministradorExistenteEReutilizaHashNoProximoInicio() {
        var anterior = usuarios.criar("Admin existente", EMAIL, SENHA_ANTIGA, Perfil.ADMIN);
        var bootstrap = new Bootstrap(usuarios, " ADMIN@EXEMPLO.TEST ", SENHA_CONFIGURADA);
        var args = new DefaultApplicationArguments();

        bootstrap.run(args);

        var hashAtualizado = usuarios.loadUserByUsername(EMAIL).getPassword();
        assertThat(encoder.matches(SENHA_CONFIGURADA, hashAtualizado)).isTrue();
        assertThat(encoder.matches(SENHA_ANTIGA, hashAtualizado)).isFalse();
        assertThat(usuarios.listar()).containsExactly(anterior);

        bootstrap.run(args);

        assertThat(usuarios.loadUserByUsername(EMAIL).getPassword()).isEqualTo(hashAtualizado);
    }

    @Test
    void rejeitaAtualizacaoDeSenhaQuandoEmailNaoPertenceAUmAdministrador() {
        usuarios.criar("Usuário", EMAIL, SENHA_ANTIGA, Perfil.USUARIO);
        var hashOriginal = usuarios.loadUserByUsername(EMAIL).getPassword();

        assertThatThrownBy(() -> usuarios.garantirSenhaAdmin(EMAIL, SENHA_CONFIGURADA))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Administrador padrão não encontrado.");

        assertThat(usuarios.loadUserByUsername(EMAIL).getPassword()).isEqualTo(hashOriginal);
    }
}
