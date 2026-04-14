package com.fiap.finance_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "TB_TRANSACAO")
@Data // Usando Lombok para evitar boilerplate
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double valor;
    private LocalDate data;


    // "RECEITA" ou "DESPESA"
    private String tipo;

    private String categoria; // Ex: Alimentação, Lazer, Salário
}
