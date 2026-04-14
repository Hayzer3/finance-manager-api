package com.fiap.finance_api.service;

import com.fiap.finance_api.model.Transacao;
import com.fiap.finance_api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;
    public List<Transacao> listarTodas() {
        return repository.findAll();
    }

    public Transacao salvar(Transacao transacao) {
        return repository.save(transacao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
    public Double calcularSaldo() {
        return repository.findAll().stream()
                .mapToDouble(t -> t.getTipo().equalsIgnoreCase("RECEITA") ? t.getValor() : -t.getValor())
                .sum();
    }
    public Map<String, Double> retornarResumo() {
        List<Transacao> todas = repository.findAll();

        Double receitas = todas.stream()
                .filter(t -> "RECEITA".equalsIgnoreCase(t.getTipo()))
                .mapToDouble(Transacao::getValor).sum();

        Double despesas = todas.stream()
                .filter(t -> "DESPESA".equalsIgnoreCase(t.getTipo()))
                .mapToDouble(Transacao::getValor).sum();

        Map<String, Double> resumo = new HashMap<>();
        resumo.put("receitas", receitas);
        resumo.put("despesas", despesas);
        resumo.put("saldo", receitas - despesas);

        return resumo;
    }
    // No seu Service ou Controller Java
    public Transacao atualizar(Long id, Transacao novaTransacao) {
        Transacao existente = repository.findById(id).get();

        existente.setDescricao(novaTransacao.getDescricao());
        existente.setValor(novaTransacao.getValor());
        existente.setTipo(novaTransacao.getTipo()); // VERIFIQUE SE ESSA LINHA EXISTE!
        existente.setCategoria(novaTransacao.getCategoria());

        return repository.save(existente);
    }
}