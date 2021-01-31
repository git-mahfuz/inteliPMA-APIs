package com.infy.pma.api.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class EpicTest {

	@Test
	void creation() {
		
		Epic epic = new Epic(UUID.randomUUID(), "UX Design", new Date(), new Date());
		assertEquals(epic.getName(), "UX Design");
		assertThat(epic.getEpicId()).isNotNull();
		assertThat(epic.getName()).isEqualTo("UX Design");
	}

}
