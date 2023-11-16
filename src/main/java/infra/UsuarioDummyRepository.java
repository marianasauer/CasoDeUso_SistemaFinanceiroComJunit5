package infra;


import builders.UsuarioBuilder;
import domain.Usuario;
import services.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioDummyRepository implements UsuarioRepository {
    @Override
    public Usuario salvar(Usuario usuario) {
        return UsuarioBuilder.umUsuario()
                .comNome(usuario.nome())
                .comEmail(usuario.email())
                .comSenha(usuario.senha())
                .agora();
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        if ("user@mail.com".equals(email))
            return Optional.of(UsuarioBuilder.umUsuario().comEmail(email).agora());
        return Optional.empty();
    }
}
