package com.qa.pifp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.pifp.data.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {

//	List<Player> findByName(String name);

}
