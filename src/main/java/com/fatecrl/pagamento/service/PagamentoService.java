package com.fatecrl.pagamento.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatecrl.pagamento.model.Pagamento;

@Service
public class PagamentoService {
    List<Pagamento> pagamentos = new ArrayList<Pagamento>();


    public PagamentoService() { }

    public Pagamento pegaPagamentoPorId(Long id){

        for (Pagamento pagamento : pagamentos) {
            if(pagamento.getId().equals(id)){
                return pagamento;
            }
        }
        return null;
    }

    public List<Pagamento> obtemPagamentos(){
        List<Pagamento> pgto = new ArrayList<Pagamento>(pagamentos);
        return pgto;
    }

    public Pagamento adicionaPagamento(Pagamento pagamento){
        Pagamento pgmto = new Pagamento();

        pgmto.setId(pagamento.generateNextId());
        pgmto.setValor(pagamento.getValor());
        pgmto.setFormaPagamento(pagamento.getFormaPagamento());
        pgmto.setDataPagamento(LocalDateTime.now());
        pgmto.setStatus("Pendente");
        pgmto.setIdCarrinho(pagamento.getIdCarrinho());
        pgmto.setIdUsuario(pagamento.getIdUsuario());
        
        pagamentos.add(pgmto);

        return pgmto;
    }

    public List<Pagamento> procuraPagamentoPorIdUsuario(Long idUsuario){
        List<Pagamento> pagamentosUsuario = new ArrayList<Pagamento>();

        for (Pagamento pagamento : pagamentos) {
            if(pagamento.getIdUsuario().equals(idUsuario)){
                pagamentosUsuario.add(pagamento);
            }
        }

        if(pagamentosUsuario.size() <= 0) return null;

        return pagamentosUsuario;
    }

    public Pagamento atualizaStatusPagamentoPorId(Long idPagamento, String status){
        Pagamento pgmto = new Pagamento();

        for (Pagamento pagamento : pagamentos) {
            if(pagamento.getId().equals(idPagamento)){
                pagamento.setStatus(status);
                pgmto = pagamento;
                break;
            }
        }

        if(pgmto.getId() == null) return null;
        
        return pgmto;
    }
}
