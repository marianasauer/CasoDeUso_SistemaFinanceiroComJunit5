package services;

import domain.Conta;
import exception.ValidationException;
import services.repositories.ContaRepository;

import java.util.List;

public class ContaService {
    private ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public Conta salvar(Conta conta){
        List<Conta> contas = repository.obterContasPorUsuario(conta.usuario().id());
        contas.stream().forEach(contaExistente -> {
            if (conta.nome().equalsIgnoreCase(contaExistente.nome()))
                throw new ValidationException("Usuário já possui uma conta com este nome");
        });
        return repository.salvar(conta);
    }
}
