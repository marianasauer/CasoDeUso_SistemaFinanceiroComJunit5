package domain.service;

import builders.UsuarioBuilder;
import domain.Usuario;
import exception.ValidationException;
import infra.UsuarioDummyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import services.UsuarioService;
import services.repositories.UsuarioRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock private UsuarioRepository repository;
    @InjectMocks private UsuarioService service;


//    @AfterEach
//   public void tearDown(){
//       Mockito.verify(repository).getUserByEmail(userToSave.email());
//    }

    @Test
    public void deveRetornarEmptyQuandoUsuarioInexistente(){

        when(repository.getUserByEmail("mail@mail.com")).thenReturn(Optional.empty());

        Optional<Usuario> user =  service.getUserByEmail("mail@mail.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){

        when(repository.getUserByEmail("mail@mail.com"))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));
        ;

        Optional<Usuario> user =  service.getUserByEmail("mail@mail.com");
        System.out.println(user);
        Assertions.assertTrue(user.isPresent());




        Mockito.verify(repository, Mockito.atLeastOnce()).getUserByEmail("mail@mail.com");
        Mockito.verify(repository, Mockito.never()).getUserByEmail("outroEmail@mail.com");
        Mockito.verifyNoMoreInteractions(repository);

    }

    @Test
    public void deveSalvarUsuarioComSucesso(){

        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).agora();

        when(repository.getUserByEmail(userToSave.email()))
                        .thenReturn(Optional.empty());
        when(repository.salvar(userToSave)).thenReturn(UsuarioBuilder.umUsuario().agora());

        Usuario savedUser =   service.salvar(userToSave);
        Assertions.assertNotNull(savedUser.id());

        Mockito.verify(repository).getUserByEmail(userToSave.email());
    //    Mockito.verify(repository).salvar(userToSave);
    }

    @Test
    public void deveRejeitarUsuarioExistente(){
        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).agora();
        when(repository.getUserByEmail(userToSave.email()))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));

        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(userToSave));
        Assertions.assertTrue(ex.getMessage().endsWith("já cadastrado!"));

        Mockito.verify(repository, Mockito.never()).salvar(userToSave);


    }

}
