package org.example;

import builders.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Domínio: Usuário")
public class UsuarioTest {

    @Test
    @DisplayName("Deve criar um usuário válido")
    public void deveCriarUsuarioValido(){
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        Assertions.assertAll("Usuario",
                () -> assertEquals(1L, usuario.id()),
                () -> assertEquals("Usuário Válido", usuario.nome()),
                () -> assertEquals("user@mail.com", usuario.email()),
                () -> assertEquals("12345678", usuario.senha())
        );
    }

    @Test
    public void deveRejeitarUsuarioSemNome(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                UsuarioBuilder.umUsuario().comNome(null).agora());

        assertEquals("Nome é obrigatório", ex.getMessage());
    }
}
