package com.fatecrl.pagamento.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.pagamento.model.Pagamento;
import com.fatecrl.pagamento.service.PagamentoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pagamentos")

public class PagamentoController {
    
    @Autowired
    private PagamentoService _pagamentoService;    

    @Operation(summary = "Obtém informações através do ID do pagamento", description = "Retorna todos os dados do pagamento em questão")
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> obtemPagamentoPorId(@PathVariable Long id){
        if(id == null){
            return ResponseEntity.badRequest().build();
        } 

        Pagamento pagamento = _pagamentoService.pegaPagamentoPorId(id);
        
        if(pagamento == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pagamento);
    }

    @Operation(summary = "Obtém informações de todos os pagamentos cadastrados", description = "Retorna todos os pagamentos que estão cadastrados no sistema")
    @GetMapping("/obtemPagamentos")
    public ResponseEntity<List<Pagamento>> obtemTodosPagamentos(){
        return ResponseEntity.ok(_pagamentoService.obtemPagamentos());
    }

    @Operation(summary = "Cria um novo pagamento no sistema", description = "Campos obrigatórios: VALOR, FORMAPAGAMENTO, IDCARRINHO E IDUSUARIO")
    @PostMapping("/")
    public ResponseEntity<Pagamento> adicionaPagamento(@RequestBody Pagamento pagamento){
        if(pagamento.getValor() == null) {
            return ResponseEntity.badRequest().build();
        }

        if(pagamento.getFormaPagamento().isEmpty() || pagamento.getFormaPagamento() == null){
            return ResponseEntity.badRequest().build();
        }

        if(pagamento.getIdCarrinho() == null) {
            return ResponseEntity.badRequest().build();
        }

        if(pagamento.getIdUsuario() == null){
            return ResponseEntity.badRequest().build();
        }
        
        Pagamento pgmto =  _pagamentoService.adicionaPagamento(pagamento);

        URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(pgmto.getId())
						.toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Cadastra um pagamento no sistema", description = "Campos obrigatórios: VALOR, FORMAPAGAMENTO, IDCARRINHO E IDUSUARIO")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Pagamento>> obtemPagamentosPorIdUsuario(@PathVariable Long idUsuario){
        if(idUsuario == null){
            return ResponseEntity.badRequest().build();
        } 

        List<Pagamento> pagamentosUsuario = _pagamentoService.procuraPagamentoPorIdUsuario(idUsuario);

        if(pagamentosUsuario == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(pagamentosUsuario);
    }

    @Operation(summary = "Atualiza o status do pagamento", description = "Necessário passar o id do pagamento por query e o status através do body")
    @PutMapping("/{id}/status")
    public ResponseEntity<Pagamento> atualizaStatusPagamento(@PathVariable Long id, @RequestParam String status){
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        
        Pagamento pgmto = _pagamentoService.atualizaStatusPagamentoPorId(id, status);

        if(pgmto == null){  
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pgmto);
    }
}
