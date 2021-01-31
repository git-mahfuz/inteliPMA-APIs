package com.infy.pma.api.jpa;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.pma.api.entities.Epic;

@DataJpaTest
@RunWith(SpringRunner.class)
class EpicJpaTest {
	
	@Autowired
	private TestEntityManager tem;

	@Test
	public void mapping() {
		
		Epic epic = this.tem.persistAndFlush(
					new Epic("Designing the UX Journey", new Date(), new Date())
				); 
		Assertions.assertThat(epic.getName()).isEqualTo("Designing the UX Journey");
		Assertions.assertThat(epic.getEpicId()).isNotNull();
	}

}
