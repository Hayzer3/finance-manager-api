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
    /// seiola
    public List<Transacao> listarTodas() {
        return repository.findAll();
    }

    public Transacao salvar(Transacao transacao) {
        // Regra simples: se for despesa, garantimos que o valor seja salvo negativo ou tratado no front
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
}