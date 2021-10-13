package com.qa.pifp.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.pifp.data.Player;
import com.qa.pifp.service.PlayerService;

@RestController
public class PlayerController {

	private PlayerService service;

	public PlayerController(PlayerService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createPlayer")
	public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
		Player responseBody = this.service.createPlayer(player);
		ResponseEntity<Player> response = new ResponseEntity<Player>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updatePlayer/{id}")
	public ResponseEntity<Player> updatePlayer(@RequestBody Player player, @PathVariable int id) {
		Player responseBody = this.service.updatePlayer(player, id);
		return new ResponseEntity<Player>(responseBody, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getAllPlayers")
	public List<Player> getAllPlayers() {
		return this.service.getAllPlayers();
	}

	@GetMapping("/getPlayer/{id}")
	public Player getPlayerByid(@PathVariable int id) {
		return this.service.getPlayerByid(id);
	}

	@DeleteMapping("/deletePlayer/{id}")
	public ResponseEntity<?> deletePlayerByid(@PathVariable int id) {
		this.service.deletePlayerByid(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteAllPlayers")
	public ResponseEntity<?> deleteAllPlayers() {
		this.service.deleteAllPlayers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
