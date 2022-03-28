package com.br.test.cadBeneficiario.mock;

import com.br.test.cadBeneficiario.bean.Beneficiario;
import com.br.test.cadBeneficiario.bean.Dependente;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioMock {
    public static Beneficiario mockBeneficiario(){
        return Beneficiario.builder()
                .nome("Nome de teste")
                .pontuacao(5)
                .rendaFamiliarTotal(899)
                .id(1L)
                .dependentes(mockDependente())
                .build();
    }

    private static List<Dependente> mockDependente(){
        List<Dependente> dependentes = new ArrayList<>();
        Dependente dependente1 = Dependente.builder()
                .id(1L)
                .idade(10)
                .nome("Marcos")
                .build();
        Dependente dependente2 = Dependente.builder()
                .id(2L)
                .idade(25)
                .nome("Marcelo")
                .build();

        dependentes.add(dependente1);
        dependentes.add(dependente2);

        return dependentes;
    }
}
