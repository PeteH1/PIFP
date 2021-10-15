package com.qa.pifp.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.pifp.data.Player;
import com.qa.pifp.exception.PlayerNotFoundException;
import com.qa.pifp.repo.PlayerRepo;

@Primary
@Service
public class PlayerServiceDB implements PlayerService {

	private PlayerRepo repo;

	public PlayerServiceDB(PlayerRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Player createPlayer(Player player) {
		return this.repo.save(player);
	}

	@Override
	public Player updatePlayer(Player player, int id) {
		Player toUpdate = this.repo.findById(id).get();

		toUpdate.setName(player.getName());
		toUpdate.setAge(player.getAge());
		toUpdate.setPosition(player.getPosition());
		toUpdate.setNationality(player.getNationality());

		return this.repo.save(toUpdate);
	}

	@Override
	public List<Player> getAllPlayers() {
		return this.repo.findAll();
	}

	@Override
	public Player getPlayerByid(int id) {
		return this.repo.findById(id).orElseThrow(PlayerNotFoundException::new);
	}

	@Override
	public void deletePlayerByid(int id) {
		this.repo.deleteById(id);
	}

	@Override
	public void deleteAllPlayers() {
		this.repo.deleteAll();
	}

}
