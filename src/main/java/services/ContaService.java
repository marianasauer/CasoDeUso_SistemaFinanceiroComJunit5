package services;

import domain.Conta;
import exception.ValidationException;
import services.external.ContaEvent;
import services.repositories.ContaRepository;

import java.util.List;

public class ContaService {
    private ContaRepository repository;
    private ContaEvent event;

    public ContaService(ContaRepository repository, ContaEvent event) {
        this.repository = repository;
        this.event = event;
    }

    public Conta salvar(Conta conta){
        List<Conta> contas = repository.obterContasPorUsuario(conta.usuario().id());
        contas.stream().forEach(contaExistente -> {
            if (conta.nome().equalsIgnoreCase(contaExistente.nome()))
                throw new ValidationException("Usuário já possui uma conta com este nome");
        });
        Conta contaPersistida = repository.salvar(conta);
        try {
            event.dispatch(contaPersistida, ContaEvent.EventType.CREATED);
        } catch (Exception e) {
            repository.delete(contaPersistida);
            throw new RuntimeException("Falha na criação da conta, tente novamente");
        }
        return contaPersistida;
    }
}
