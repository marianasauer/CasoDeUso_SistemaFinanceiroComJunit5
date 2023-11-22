package services;

import domain.Transacao;
import exception.ValidationException;
import services.repositories.TransacaoDAO;

public class TransacaoService {
    private TransacaoDAO dao;

    public Transacao salvar(Transacao transacao){
        if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);

    }
}
