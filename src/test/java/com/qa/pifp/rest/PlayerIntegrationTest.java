package com.qa.pifp.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.pifp.data.Player;

/*Random port prevents clashing with existing use of 8080*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // set up the MockMVC object
// Insert schema before data in scripts!
@Sql(scripts = { "classpath:player-schema.sql",
		"classpath:player-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class PlayerIntegrationTest {

	@Autowired // inject the MockMVC object into this class
	private MockMvc mvc; // object for sending mock http requests

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		final Player testPlayer = new Player(null, "Nigel", 52, "FW", "Norway");
		String testPlayerAsJSON = this.mapper.writeValueAsString(testPlayer);

		final Player savedPlayer = new Player(2, "Nigel", 52, "FW", "Norway");
		String savedPlayerAsJSON = this.mapper.writeValueAsString(savedPlayer);

		// method, path, headers, body
		RequestBuilder req = post("/createPlayer").contentType(MediaType.APPLICATION_JSON).content(testPlayerAsJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedPlayerAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	/*
	 * This is a shorter version of the above test. A bit less readable but you
	 * don't need to remember the classes like RequestBuilder & ResultMatcher
	 */
//	@Test
	void testCreateAbridged() throws Exception {
		final String testPlayerAsJSON = this.mapper.writeValueAsString(new Player(null, "Nigel", 52, "FW", "Norway"));
		final String savedPlayerAsJSON = this.mapper.writeValueAsString(new Player(2, "Nigel", 52, "FW", "Norway"));

		this.mvc.perform(post("/createPlayer").contentType(MediaType.APPLICATION_JSON).content(testPlayerAsJSON))
				.andExpect(status().isCreated()).andExpect(content().json(savedPlayerAsJSON));
	}

	@Test
	void testGetById() throws Exception {
		final Player savedPlayer = new Player(1, "Pete", 27, "MF", "UK");
		String savedPlayerAsJSON = this.mapper.writeValueAsString(savedPlayer);

		RequestBuilder req = get("/getPlayer/" + savedPlayer.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(savedPlayerAsJSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedPlayerAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdatePlayer() throws Exception {
		final Player savedPlayer = new Player(1, "Pete", 27, "FW", "UK");
		String savedPlayerAsJSON = this.mapper.writeValueAsString(savedPlayer);

		RequestBuilder req = put("/updatePlayer/" + savedPlayer.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(savedPlayerAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(savedPlayerAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGetAllPlayers() throws Exception {
		final Player savedPlayer = new Player(1, "Pete", 27, "MF", "UK");
		String savedPlayerAsJSON = this.mapper.writeValueAsString(List.of(savedPlayer));

		RequestBuilder req = get("/getAllPlayers").contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedPlayerAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);

	}

	@Test
	void testDeleteById() throws Exception {
		this.mvc.perform(delete("/deletePlayer/1")).andExpect(status().isNoContent());
	}

	@Test
	void testDeleteAllPlayers() throws Exception {
		this.mvc.perform(delete("/deleteAllPlayers")).andExpect(status().isNoContent());
	}
}