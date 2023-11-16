package domain.service;

import builders.UsuarioBuilder;
import domain.Usuario;
import infra.UsuarioDummyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UsuarioService;

public class UsuarioServiceTest {
    private UsuarioService service;
    @Test
    public void deveSalvarUsuarioComSucesso(){
        service = new UsuarioService(new UsuarioDummyRepository());
        Usuario user = UsuarioBuilder.umUsuario().comId(null).comEmail("outro9@email.com").agora();
        Usuario savedUser = service.salvar(user);
        Assertions.assertNotNull(savedUser.id());
    }
}
