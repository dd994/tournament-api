package com.gmail.ddzhunenko.tournament;

import com.gmail.ddzhunenko.tournament.entity.Participant;
import com.gmail.ddzhunenko.tournament.entity.Tournament;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
class TournamentApplicationTests {

	private final RestTemplate restTemplate = new RestTemplate();

	private int tournamentId = 1;

	private final String url = "http://localhost:8082/tournament/";
	private final String createUrl = url+"/create";
	private final String addParticipant = tournamentId+"/participants/add";

	@Test
	void createTournamentTest(){
		Tournament tournament = new Tournament("my_tour");
		ResponseEntity<Tournament> result = restTemplate.postForEntity(createUrl, tournament,Tournament.class);
		Assert.assertEquals(201, result.getStatusCodeValue());
	}

	@Test
	void addParticipantToTournamentTest(){
		Participant participant =  new Participant("name1");
		ResponseEntity<Participant> result = restTemplate.postForEntity(url+addParticipant, participant,Participant.class);
		Assert.assertEquals(201, result.getStatusCodeValue());
	}

}
