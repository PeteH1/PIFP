package com.qa.pifp.service;

import java.util.List;

import com.qa.pifp.data.Player;

public interface PlayerService {

	public Player createPlayer(Player player);

	public Player updatePlayer(Player player, int id);

	public List<Player> getAllPlayers();

	public Player getPlayerByid(int id);

	public void deletePlayerByid(int id);

	public void deleteAllPlayers();

}
