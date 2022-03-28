package com.br.test.cadBeneficiario;

import com.br.test.cadBeneficiario.service.BeneficiarioServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
		BeneficiarioServiceTest.class
})
class CadBeneficiarioApplicationTests {

	@Test
	void contextLoads() {
	}

}
