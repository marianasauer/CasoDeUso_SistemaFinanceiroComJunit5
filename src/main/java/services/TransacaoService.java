package services;

import domain.Transacao;
import exception.ValidationException;

import services.repositories.TransacaoDAO;

import java.time.LocalDateTime;

public class TransacaoService {
    private TransacaoDAO dao;

    public Transacao salvar(Transacao transacao){
        if(getTime().getHour() > 10)
            throw new RuntimeException("Tente novamente amanhã");

        if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);

    }

    public LocalDateTime getTime(){
        return LocalDateTime.now();
    }
}
