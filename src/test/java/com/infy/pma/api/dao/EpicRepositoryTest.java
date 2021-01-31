package com.infy.pma.api.dao;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.pma.api.entities.Epic;

@DataJpaTest
@RunWith(SpringRunner.class)
class EpicRepositoryTest {

	@Autowired
	private EpicRepository epicRepository;

	@Test
	void findByEpicName() {

		this.epicRepository.save(new Epic("UX Design", new Date(), new Date()));

		List<Epic> epics = this.epicRepository.findByName("UX Design");
		Assertions.assertThat(epics.iterator().next().getEpicId()).isNotNull();
		Assertions.assertThat(epics.size()).isEqualTo(1);
		Assertions.assertThat(epics.iterator().next().getName()).isEqualTo("UX Design");
	}

}
