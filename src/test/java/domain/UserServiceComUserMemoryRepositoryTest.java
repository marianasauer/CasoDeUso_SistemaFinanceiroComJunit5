package domain;

import builders.UsuarioBuilder;
import exception.ValidationException;
import infra.UsuarioMemoryRepository;
import org.junit.jupiter.api.*;
import services.UsuarioService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {
    private UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    @Test
    @Order(1)
    public void deveSalvarUsuarioValido(){
       Usuario user = service.salvar(UsuarioBuilder.umUsuario().comId(null).agora());
        Assertions.assertNotNull(user.id());
//        Assertions.assertEquals(2L, user.id());
    }

    @Test
    @Order(2)
    public void deveRejeitarUsuarioExistente(){
      ValidationException ex =  Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(UsuarioBuilder.umUsuario().comId(null).agora()));
      Assertions.assertEquals("Usuário user@mail.com já cadastrado!", ex.getMessage());

    }
}
