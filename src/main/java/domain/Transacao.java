package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Transacao {
    private Long id;
    private String descricao;
    private Double valor;
    private Conta conta;
    private LocalDate data;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao transacao)) return false;
        return Objects.equals(getDescricao(), transacao.getDescricao()) && Objects.equals(getValor(), transacao.getValor()) && Objects.equals(getConta(), transacao.getConta()) && Objects.equals(getData(), transacao.getData()) && Objects.equals(getStatus(), transacao.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescricao(), getValor(), getConta(), getData(), getStatus());
    }
}
