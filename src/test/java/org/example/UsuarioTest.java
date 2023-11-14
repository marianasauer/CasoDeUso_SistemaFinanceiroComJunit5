package org.example;

import builders.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    public void deveRejeitarUsuarioSemEmail(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                UsuarioBuilder.umUsuario().comEmail(null).agora()
                );
        assertEquals("Email é obrigatório", ex.getMessage());
    }

    @Test
    public void deveRejeitarUsuarioSemSenha(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                UsuarioBuilder.umUsuario().comSenha(null).agora()
        );
        assertEquals("Senha é obrigatória", ex.getMessage());
    }

    @ParameterizedTest(name = "[{index}] - {4}")
    @CsvSource(value = {
            "1, NULL, user@mail.com, 123456, Nome é obrigatório",
            "1, Nome usuário, NULL, 123456, Email é obrigatório",
            "1, Nome usuário, user@mail.com, NULL, Senha é obrigatória"
    }, nullValues = "NULL")
    public void deveValidarCamposObrigatorios(Long id, String nome, String email, String senha, String mensagem){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                UsuarioBuilder.umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora());
        assertEquals(mensagem, ex.getMessage());
    }


}
