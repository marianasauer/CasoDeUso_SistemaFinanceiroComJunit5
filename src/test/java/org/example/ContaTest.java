package org.example;

import builders.ContaBuilder;
import builders.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContaTest {
    @Test
    public void deveCriarContaValida(){
        //Criar uma conta
        Conta conta = ContaBuilder.umaConta().agora();
        //Assertivas em cima da conta
        Assertions.assertAll("Conta",
                () -> Assertions.assertEquals(1L, conta.id()),
                () -> Assertions.assertEquals("Conta Válida", conta.nome()),
                () -> Assertions.assertEquals(UsuarioBuilder.umUsuario().agora(), conta.usuario()));
    }
}
