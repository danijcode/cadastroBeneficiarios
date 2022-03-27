package com.br.test.cadBeneficiario.service;

import com.br.test.cadBeneficiario.bean.Beneficiario;
import com.br.test.cadBeneficiario.bean.Dependente;
import com.br.test.cadBeneficiario.dto.BeneficiarioDTO;
import com.br.test.cadBeneficiario.dto.DependenteDTO;
import com.br.test.cadBeneficiario.repository.BeneficiarioRepository;
import com.br.test.cadBeneficiario.util.Pontuacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeneficiarioService {
    private final BeneficiarioRepository beneficiarioRepository;


    public void save(BeneficiarioDTO beneficiarioDTO) {
        Beneficiario newBeneficiario = Beneficiario.builder()
                .nome(beneficiarioDTO.getNome())
                .rendaFamiliarTotal(beneficiarioDTO.getRendaFamiliarTotal())
                .dependentes(buildDependentes(beneficiarioDTO.getDependentes()))
                .pontuacao(setPontuacaoBeneficiario(beneficiarioDTO))
                .build();

        beneficiarioRepository.save(newBeneficiario);
    }

    private List<Dependente> buildDependentes(List<DependenteDTO> dependentes){
        return dependentes.stream().map(Dependente::new).collect(Collectors.toList());
    }

    private Integer setPontuacaoBeneficiario(BeneficiarioDTO beneficiarioDTO){
        var pontuacao = 0;

        if(beneficiarioDTO.getRendaFamiliarTotal() <= 900){
            pontuacao += Pontuacao.CINCO_PONTOS;
        }
        if(beneficiarioDTO.getRendaFamiliarTotal() > 900 && beneficiarioDTO.getRendaFamiliarTotal() <= 1500){
            pontuacao += Pontuacao.TRES_PONTOS;
        }

        pontuacao +=pontuacaoPorDependentes(beneficiarioDTO.getDependentes());

        return pontuacao;
    }

    private Integer pontuacaoPorDependentes(List<DependenteDTO> dependentes){
        List<DependenteDTO> listDependentes = dependentes.stream().filter((DependenteDTO dependente) ->
                dependente.getIdade() <= 18
        ).collect(Collectors.toList());

        if(listDependentes.size() >= 3){
            return 3;
        }
        return 2;
    }

    public List<Beneficiario> findAll() {
        return beneficiarioRepository.findAll();
    }

    public Optional<Beneficiario> findById(Long id) {
        return beneficiarioRepository.findById(id);
    }
}
