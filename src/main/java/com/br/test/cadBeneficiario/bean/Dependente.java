package com.br.test.cadBeneficiario.bean;

import com.br.test.cadBeneficiario.dto.DependenteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;

    public Dependente(DependenteDTO dependenteDTO){
        nome = dependenteDTO.getNome();
        idade = dependenteDTO.getIdade();
    }
}
