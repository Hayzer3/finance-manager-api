package com.fiap.finance_api.dto;

import com.fiap.finance_api.model.Transacao;

import java.time.LocalDate;

public record TransacaoDTO(
        Long id,
        String descricao,
        Double valor,
        LocalDate data,
        String tipo,
        String categoria
) {

    public TransacaoDTO(Transacao entity) {
        this(entity.getId(), entity.getDescricao(), entity.getValor(),
                entity.getData(), entity.getTipo(), entity.getCategoria());
    }
}