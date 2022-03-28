package com.br.test.cadBeneficiario.service;

import com.br.test.cadBeneficiario.bean.Beneficiario;
import com.br.test.cadBeneficiario.bean.Dependente;
import com.br.test.cadBeneficiario.dto.BeneficiarioDTO;
import com.br.test.cadBeneficiario.dto.DependenteDTO;
import com.br.test.cadBeneficiario.repository.BeneficiarioRepository;
import com.br.test.cadBeneficiario.util.Mensagens;
import com.br.test.cadBeneficiario.util.Pontuacao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

        if(beneficiarioDTO.getRendaFamiliarTotal() <= Mensagens.RENDA_FAMILIAR_5_PONTOS){
            pontuacao += Pontuacao.CINCO_PONTOS;
        }
        if(beneficiarioDTO.getRendaFamiliarTotal() > Mensagens.RENDA_FAMILIAR_5_PONTOS &&
                beneficiarioDTO.getRendaFamiliarTotal() <= Mensagens.RENDA_FAMILIAR_3_PONTOS){
            pontuacao += Pontuacao.TRES_PONTOS;
        }

        pontuacao +=pontuacaoPorDependentes(beneficiarioDTO.getDependentes());

        return pontuacao;
    }

    private Integer pontuacaoPorDependentes(List<DependenteDTO> dependentes){
        List<DependenteDTO> listDependentes = dependentes.stream().filter((DependenteDTO dependente) ->
                dependente.getIdade() <= Mensagens.MAIOR_IDADE
        ).collect(Collectors.toList());

        if(listDependentes.size() >= Mensagens.TRES){
            return Pontuacao.TRES_PONTOS;
        }
        if(listDependentes.size() > Mensagens.ZERO){
            return Pontuacao.DOIS_PONTOS;
        }
        return Mensagens.ZERO;
    }

    public List<Beneficiario> findAll() {
         var listaBeneficiario =  beneficiarioRepository.findAll(Sort.by(Sort.Direction.DESC, "pontuacao"));
         return listaBeneficiario.stream().filter(
                    (Beneficiario beneficiario) -> beneficiario.getPontuacao() != 0
                 ).collect(Collectors.toList());
    }

    public Optional<Beneficiario> findById(Long id) {
        return beneficiarioRepository.findById(id);
    }
}
