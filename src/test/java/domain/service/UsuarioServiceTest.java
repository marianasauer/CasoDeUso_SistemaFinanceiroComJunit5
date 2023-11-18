package domain.service;

import builders.UsuarioBuilder;
import domain.Usuario;
import infra.UsuarioDummyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import services.UsuarioService;
import services.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioServiceTest {
    private UsuarioService service;

    @Test
    public void deveSalvarUsuarioPorEmail(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Optional<Usuario> user =  service.getUserByEmail("mail@mail.com");
        Assertions.assertTrue(user.isEmpty());
    }
}
