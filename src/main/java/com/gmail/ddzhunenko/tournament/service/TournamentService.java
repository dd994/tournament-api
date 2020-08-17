package com.gmail.ddzhunenko.tournament.service;

import com.gmail.ddzhunenko.tournament.entity.Participant;
import com.gmail.ddzhunenko.tournament.entity.Tournament;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TournamentService {

    void createTournament(Tournament tournament);
    void addParticipant(int tournamentId, Participant participant);
    Optional<Tournament> getTournament(int id);
    List getAllParticipants(int id);
    Map getGrid(int id);
    Map getResultGrid(int id);
    Map summary(int id);

}
