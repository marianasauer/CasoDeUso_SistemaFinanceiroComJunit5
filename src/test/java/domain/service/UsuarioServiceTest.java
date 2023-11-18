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
    public void deveRetornarEmptyQuandoUsuarioInexistente(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Mockito.when(repository.getUserByEmail("mail@mail.com")).thenReturn(Optional.empty());

        Optional<Usuario> user =  service.getUserByEmail("mail@mail.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Mockito.when(repository.getUserByEmail("mail@mail.com"))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));

        Optional<Usuario> user =  service.getUserByEmail("mail@mail.com");
        Assertions.assertTrue(user.isPresent());
        System.out.println(user);

    //    Mockito.verify(repository, Mockito.atLeastOnce()).getUserByEmail("mail@mail.com");
    //    Mockito.verify(repository, Mockito.never()).getUserByEmail("outro@mail.com");
    //    Mockito.verifyNoMoreInteractions(repository);

    }
}
