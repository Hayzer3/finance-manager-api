package com.fiap.finance_api.repository;

import com.fiap.finance_api.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    // Aqui podemos criar métodos customizados depois, como buscar por mês
}