package domain.service;

import builders.ContaBuilder;
import domain.Conta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import services.ContaService;
import services.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
    @InjectMocks private ContaService service;
    @Mock private ContaRepository repository;

    @Test
    public void deveSalvarComSucesso(){
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.when(repository.salvar(contaToSave))
                .thenReturn(ContaBuilder.umaConta().agora());

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.id());
    }
}
