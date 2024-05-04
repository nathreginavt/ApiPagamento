package com.fatecrl.pagamento.model;

import java.time.LocalDateTime;

public class Pagamento {
    private Long id;
    private Double valor;
    private String formaPagamento;
    private String status;
    private LocalDateTime dataPagamento;
    private Long idUsuario;
    private Long idCarrinho;
    private static Long nextId = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Long getIdCarrinho() {
        return idCarrinho;
    }
    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long generateNextId() {
        return nextId++;
    }
}
