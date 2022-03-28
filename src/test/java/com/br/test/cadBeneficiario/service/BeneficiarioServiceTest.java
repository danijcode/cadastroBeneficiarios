package com.br.test.cadBeneficiario.service;

import com.br.test.cadBeneficiario.bean.Beneficiario;
import com.br.test.cadBeneficiario.bean.Dependente;
import com.br.test.cadBeneficiario.mock.BeneficiarioMock;
import com.br.test.cadBeneficiario.util.Mensagens;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeneficiarioServiceTest {

    Beneficiario beneficiario =  BeneficiarioMock.mockBeneficiario();

    @Test
    void testTemDependente(){
        assertTrue(beneficiario.getDependentes().size() > 0);
    }

    @Test
    void testPodeReceberBeneficio(){
        assertTrue(beneficiario.getPontuacao() > 0);
    }

    @Test
    void testeGanhaCincoPontos(){
        assertTrue(beneficiario.getRendaFamiliarTotal() <= Mensagens.RENDA_FAMILIAR_5_PONTOS);
    }

    @Test
    void testeGanhaTresPontos(){
        assertFalse(
               beneficiario.getRendaFamiliarTotal() > Mensagens.RENDA_FAMILIAR_5_PONTOS
               && beneficiario.getRendaFamiliarTotal() <= Mensagens.RENDA_FAMILIAR_3_PONTOS
        );
    }

    @Test
    void testPontuaDependendeMenorDeIdade(){
        Dependente dependente = beneficiario.getDependentes().get(0);
        assertTrue(dependente.getIdade() <= Mensagens.MAIOR_IDADE);
    }

    @Test
    void testNaoPontuaPorDependendeMaiorDeIdade(){
        Dependente dependente = beneficiario.getDependentes().get(1);
        assertFalse(dependente.getIdade() <= Mensagens.MAIOR_IDADE);
    }
}
