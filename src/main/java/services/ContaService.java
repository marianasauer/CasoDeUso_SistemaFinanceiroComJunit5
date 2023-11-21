package services;

import domain.Conta;
import services.repositories.ContaRepository;

public class ContaService {
    private ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public Conta salvar(Conta conta){
        return repository.salvar(conta);
    }
}
