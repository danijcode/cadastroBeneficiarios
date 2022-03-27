package com.br.test.cadBeneficiario.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;
import java.util.List;

@Data
@Builder
public class BeneficiarioDTO {
    private String nome;
    private Integer rendaFamiliarTotal;
    private List<DependenteDTO> dependentes;
    @Nullable
    private Integer pontuacao;
}
