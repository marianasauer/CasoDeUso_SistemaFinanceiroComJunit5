package domain.service;

import builders.ContaBuilder;
import builders.TransacaoBuilder;
import domain.Conta;
import domain.Transacao;
import exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import services.TransacaoService;
import services.repositories.TransacaoDAO;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@EnabledIf(value = "isHoraValida")
@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @InjectMocks  private TransacaoService service;
    @Mock
    private TransacaoDAO dao;


    @Test
    public void deveSalvarTransacaoValida(){
        Transacao transacaoParaSalvar = TransacaoBuilder.umaTransacao().comId(null).agora();
        Transacao transacaoPersistida = TransacaoBuilder.umaTransacao().agora();
        Mockito.when(dao.salvar(transacaoParaSalvar)).thenReturn(
                TransacaoBuilder.umaTransacao().agora()
        );

        Transacao transacaoSalva = service.salvar(transacaoParaSalvar);
        Assertions.assertEquals(TransacaoBuilder.umaTransacao().agora(), transacaoSalva);
        Assertions.assertAll("Transacao",
                    () -> Assertions.assertEquals(1L, transacaoSalva.getId()),
                    () -> Assertions.assertEquals("Transação Válida", transacaoSalva.getDescricao()),
                    () -> {
                        Assertions.assertAll("Conta",
                                    () -> Assertions.assertEquals("Conta Válida", transacaoSalva.getConta().nome()),
                                    () -> {
                                        Assertions.assertAll("Usuário",
                                                () -> Assertions.assertEquals("Usuário Válido", transacaoSalva.getConta().usuario().nome()),
                                                () -> Assertions.assertEquals("12345678", transacaoSalva.getConta().usuario().senha())
                                        );
                                    }
                                );
                    }

                );
    }

    @ParameterizedTest(name = "{6}")
    @MethodSource(value = "cenariosObrigatorios")
    public void deveValidarCamposObrigatoriosAoSalvar(Long id, String descricao, Double valor, LocalDate data, Conta conta, Boolean status, String mensagem){
        String exMessage = Assertions.assertThrows(ValidationException.class, () -> {
            Transacao transacao = TransacaoBuilder.umaTransacao().comId(id).comDescricao(descricao).comValor(valor)
                    .comData(data).comConta(conta).comStatus(status).agora();
            service.salvar(transacao);
        }).getMessage();
        Assertions.assertEquals(mensagem, exMessage);
    }

    static Stream<Arguments> cenariosObrigatorios(){
        return Stream.of(
                Arguments.of(1L, null, 10D, LocalDate.now(), ContaBuilder.umaConta().agora(), true, "Descrição inexistente "),
                Arguments.of(1L, "Descrição", null, LocalDate.now(), ContaBuilder.umaConta().agora(), true, "Valor inexistente "),
                Arguments.of(1L, "Descrição", 10D, null, ContaBuilder.umaConta().agora(), true, "Data inexistente "),
                Arguments.of(1L, "Descrição", 10D, LocalDate.now(), null, true, "Conta inexistente ")

                );
    }

    public static boolean isHoraValida(){
        return LocalDateTime.now().getHour() > 14;
    }
}
