package com.qa.pifp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.pifp.data.Player;
import com.qa.pifp.repo.PlayerRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PlayerServiceDBUnitTest {

	@Autowired
	private PlayerServiceDB service;

	@MockBean
	private PlayerRepo repo;

	@Test
	void testUpdate() { // Remember to override the equals() method in the Entity (Player)
		final Integer id = 1;
		Player oldPlayer = new Player(1, "Steven", 27, "GK", "Greenland");
		Optional<Player> optionalPlayer = Optional.of(oldPlayer);

		Player newPlayer = new Player(1, "Gregory", 27, "GK", "Greenland");

		Mockito.when(this.repo.findById(id)).thenReturn(optionalPlayer);
		Mockito.when(this.repo.save(newPlayer)).thenReturn(newPlayer);

		assertEquals(newPlayer, this.service.updatePlayer(newPlayer, oldPlayer.getId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newPlayer);
	}

	@Test
	void testCreate() {
		Player newPlayer = new Player(1, "Gregory", 27, "GK", "Greenland");

		Mockito.when(this.repo.save(newPlayer)).thenReturn(newPlayer);

		assertEquals(newPlayer, this.service.createPlayer(newPlayer));

		Mockito.verify(this.repo, Mockito.times(1)).save(newPlayer);
	}

	@Test
	void testGetPlayer() {
		final Integer id = 9001;
		Player playerToGet = new Player(id, "Captain Pugwash", 71, "On deck", "The sea");

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(playerToGet));

		assertEquals(playerToGet, this.service.getPlayerByid(id));

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testGetAllPlayers() {
		Player playerInList = new Player(9001, "Captain Pugwash", 71, "On deck", "The sea");
		List<Player> listToGet = new ArrayList<>();
		listToGet.add(playerInList);

		Mockito.when(this.repo.findAll()).thenReturn(listToGet);

		assertEquals(listToGet, this.service.getAllPlayers());

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

}