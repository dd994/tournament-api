package com.gmail.ddzhunenko.tournament.controller;

import com.gmail.ddzhunenko.tournament.entity.Participant;
import com.gmail.ddzhunenko.tournament.entity.Tournament;
import com.gmail.ddzhunenko.tournament.service.TournamentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("tournament")
public class TournamentController {

    @Autowired
    TournamentServiceImpl tournamentService;

    @PostMapping("/create")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        tournamentService.createTournament(tournament);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/get/{id}")
    public Optional<Tournament> getTournament(@PathVariable("id") int id) {
        return tournamentService.getTournament(id);
}

    @PostMapping("/{id}/participants/add")
    public ResponseEntity<Participant> addParticipant(@PathVariable("id") int id, @RequestBody Participant participant) {
        tournamentService.addParticipant(id, participant);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/{id}/participants/del")
    public ResponseEntity<Participant> delParticipant(@PathVariable("id") int id, @RequestBody Participant participant) {
        tournamentService.removeParticipant(id, participant);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}/participants/get-all")
    public List getParticipants(@PathVariable("id") int id) {
        return tournamentService.getAllParticipants(id);
    }

    @GetMapping("/{id}/get-grid")
    public Map getGrid(@PathVariable("id") int id) {
        return tournamentService.getGrid(id);
    }

    @GetMapping("/{id}/start")
    public Map start(@PathVariable("id") int id) {
        return tournamentService.getResultGrid(id);
    }

    @GetMapping("/{id}/summary")
    public Map summary(@PathVariable("id") int id) {
        return tournamentService.summary(id);
    }

}
