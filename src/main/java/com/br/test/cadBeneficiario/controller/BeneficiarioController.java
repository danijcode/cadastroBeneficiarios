package com.br.test.cadBeneficiario.controller;

import com.br.test.cadBeneficiario.bean.Beneficiario;
import com.br.test.cadBeneficiario.dto.BeneficiarioDTO;
import com.br.test.cadBeneficiario.service.BeneficiarioService;
import com.br.test.cadBeneficiario.util.Mensagens;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/beneficiario")
@RequiredArgsConstructor
public class BeneficiarioController {
    
    private final BeneficiarioService beneficiarioService;

    @PostMapping()
    public ResponseEntity<?> addBeneficiario(@RequestBody BeneficiarioDTO beneficiario){
        try {
            beneficiarioService.save(beneficiario);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Mensagens.LISTA_BENEFICIARIO_SUCESSO);
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Beneficiario>> findByAll() {
        var listBeneficiario = beneficiarioService.findAll();
        if (listBeneficiario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listBeneficiario);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Beneficiario> findById(@PathVariable(name = "id") final String id) {
        var beneficiario = beneficiarioService.findById(Long.valueOf(id));
        if (beneficiario.isPresent()) {
            return ResponseEntity.ok(beneficiario.get());
        }
        return ResponseEntity.noContent().build();
    }
}
