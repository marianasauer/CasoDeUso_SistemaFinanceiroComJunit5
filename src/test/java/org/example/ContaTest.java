package org.example;

import builders.ContaBuilder;
import builders.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import Exception.ValidationException;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static builders.ContaBuilder.umaConta;

public class ContaTest {
    @Test
    public void deveCriarContaValida(){
        //Criar uma conta
        Conta conta = umaConta().agora();
        //Assertivas em cima da conta
        Assertions.assertAll("Conta",
                () -> Assertions.assertEquals(1L, conta.id()),
                () -> Assertions.assertEquals("Conta Válida", conta.nome()),
                () -> Assertions.assertEquals(UsuarioBuilder.umUsuario().agora(), conta.usuario()));
    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void deveRejeitarContaInvalida(Long id, String nome, Usuario usuario, String mensagem){
        String errorMessage = Assertions.assertThrows(ValidationException.class, () ->
                umaConta().comId(id).comNome(nome).comUsuario(usuario).agora()).getMessage();
        Assertions.assertEquals(mensagem, errorMessage);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, UsuarioBuilder.umUsuario().agora(), "Nome é obrigatório"),
                Arguments.of(1L, "Conta Válida", null, "Usuário é obrigatório")

                );
    }

    }
