package services;

import domain.Transacao;
import exception.ValidationException;
import services.external.ClockService;
import services.repositories.TransacaoDAO;

import java.time.LocalDateTime;
import java.util.Date;

public class TransacaoService {
    private TransacaoDAO dao;
    private ClockService clock;

    public Transacao salvar(Transacao transacao){
        if(clock.getCurrentTime().getHour() > 14)
            throw new RuntimeException("Tente novamente amanhã");

        if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);

    }
}
