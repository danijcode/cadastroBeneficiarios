package com.br.test.cadBeneficiario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DependenteDTO {
    private String nome;
    private Integer idade;
}
