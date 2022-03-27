package com.br.test.cadBeneficiario.repository;


import com.br.test.cadBeneficiario.bean.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {}
