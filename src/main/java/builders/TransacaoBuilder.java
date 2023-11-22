package builders;

import java.lang.Long;
import java.util.Arrays;
import java.time.LocalDate;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import domain.Conta;
import domain.Transacao;


public class TransacaoBuilder {
    private Transacao elemento;
    private TransacaoBuilder(){}

    public static TransacaoBuilder umTransacao() {
        TransacaoBuilder builder = new TransacaoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(TransacaoBuilder builder) {
        builder.elemento = new Transacao();
        Transacao elemento = builder.elemento;


        elemento.setId(0L);
        elemento.setDescricao("");
        elemento.setValor(0.0);
        elemento.setConta(null);
        elemento.setData(null);
        elemento.setStatus(false);
    }

    public TransacaoBuilder comId(Long param) {
        elemento.setId(param);
        return this;
    }

    public TransacaoBuilder comDescricao(String param) {
        elemento.setDescricao(param);
        return this;
    }

    public TransacaoBuilder comValor(Double param) {
        elemento.setValor(param);
        return this;
    }

    public TransacaoBuilder comConta(Conta param) {
        elemento.setConta(param);
        return this;
    }

    public TransacaoBuilder comData(LocalDate param) {
        elemento.setData(param);
        return this;
    }

    public TransacaoBuilder comStatus(Boolean param) {
        elemento.setStatus(param);
        return this;
    }

    public Transacao agora() {
        return elemento;
    }
}
