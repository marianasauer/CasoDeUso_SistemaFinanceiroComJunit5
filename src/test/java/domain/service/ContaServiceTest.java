package domain.service;

import builders.ContaBuilder;
import domain.Conta;
import exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import services.ContaService;
import services.external.ContaEvent;
import services.repositories.ContaRepository;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
    @InjectMocks private ContaService service;
    @Mock private ContaRepository repository;
    @Mock private ContaEvent event;

    @Test
    public void deveSalvarPrimeiraContaComSucesso(){
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.salvar(contaToSave))
                .thenReturn(ContaBuilder.umaConta().agora());
        Mockito.doNothing().when(event).dispatch(ContaBuilder.umaConta().agora(), ContaEvent.EventType.CREATED);

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.id());
    }

    @Test
    public void deveSalvarSegundaContaComSucesso(){
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.obterContasPorUsuario(contaToSave.usuario().id()))
                .thenReturn(Arrays.asList(ContaBuilder.umaConta().comNome("Outra conta").agora()));
        Mockito.when(repository.salvar(contaToSave))
                .thenReturn(ContaBuilder.umaConta().agora());

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.id());
    }

    @Test
    public void deveRejeitarContaRepetida(){
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.obterContasPorUsuario(contaToSave.usuario().id()))
                        .thenReturn(Arrays.asList(ContaBuilder.umaConta().agora()));
//        Mockito.when(repository.salvar(contaToSave))
//                .thenReturn(ContaBuilder.umaConta().agora());

        String mensagem = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(contaToSave)).getMessage();
        Assertions.assertEquals("Usuário já possui uma conta com este nome", mensagem);
    }



}
