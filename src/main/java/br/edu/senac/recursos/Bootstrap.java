package br.edu.senac.recursos;

import br.edu.senac.recursos.Modelos.Perfil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Bootstrap implements ApplicationRunner {
    private final Usuarios usuarios;
    private final String email;
    private final String senha;
    public Bootstrap(Usuarios usuarios, @Value("${app.admin.email}") String email,
                     @Value("${app.admin.password}") String senha) {
        this.usuarios = usuarios; this.email = email; this.senha = senha;
    }
    @Override public void run(ApplicationArguments args) {
        if (senha.length() < 12 || senha.length() > 72)
            throw new IllegalStateException("Senha fixa do administrador inválida.");
        if (usuarios.listar().stream().anyMatch(u -> u.perfil() == Perfil.ADMIN && u.email().equals(Usuarios.normalizar(email)))) {
            usuarios.garantirSenhaAdmin(email, senha);
            return;
        }
        usuarios.criar("Administrador", email, senha, Perfil.ADMIN);
    }
}
