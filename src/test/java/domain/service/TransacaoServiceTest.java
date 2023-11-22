package domain.service;

import builders.TransacaoBuilder;
import domain.Transacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import services.TransacaoService;
import services.repositories.TransacaoDAO;

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
}
