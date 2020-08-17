package com.gmail.ddzhunenko.tournament.service;

import com.gmail.ddzhunenko.tournament.entity.Match;
import com.gmail.ddzhunenko.tournament.interfaces.Gridable;
import com.gmail.ddzhunenko.tournament.utils.DataGenerator;
import com.gmail.ddzhunenko.tournament.entity.Participant;
import com.gmail.ddzhunenko.tournament.entity.Tournament;
import com.gmail.ddzhunenko.tournament.exceptions.NotFoundNicknameException;
import com.gmail.ddzhunenko.tournament.exceptions.NotUniqueNicknameException;
import com.gmail.ddzhunenko.tournament.repository.TournamentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;

    private DataGenerator dataGenerator = new DataGenerator();
    private Map<String, Map> database = new HashMap<>();

    @Override
    public void createTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    @Override
    public void addParticipant(int tournamentId, Participant participant) {
        try {
            Tournament tournament = tournamentRepository.getOne(tournamentId);
            if (tournament.hasParticipant(participant)) {
                throw new NotUniqueNicknameException();
            } else {
                participant.setTournament(tournament);
                tournament.addParticipant(participant);
                tournamentRepository.save(tournament);
            }
        } catch (NotUniqueNicknameException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    public void removeParticipant(int tournamentId, Participant participant) {
        try {
            Tournament tournament = tournamentRepository.getOne(tournamentId);
            if (!tournament.hasParticipant(participant)) {
                throw new NotFoundNicknameException();
            } else {
                tournament.removeParticipant(participant);
                tournamentRepository.save(tournament);
            }
        } catch (NotFoundNicknameException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    @Override
    public Optional<Tournament> getTournament(int id) {
        return tournamentRepository.findById(id);
    }

    @Override
    public List getAllParticipants(int id) {
        Tournament tournament = tournamentRepository.getOne(id);
        return tournament.getParticipants();
    }

    @Override
    public Map getGrid(int id) {
        Tournament tournament = tournamentRepository.getOne(id);
        Map grid = dataGenerator.setGrid(tournament);
        database.put(tournament.getName(), grid);
        return grid;
    }

    @Override
    public Map getResultGrid(int id) {
        Tournament tournament = tournamentRepository.getOne(id);
        Map<Integer, Match> grid = database.get(tournament.getName());

        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i + 1) instanceof Match) {
                grid.get(i + 1).setStartTime(LocalDateTime.now());
                grid.get(i + 1).setFirstPlayerScore(dataGenerator.generateScore());
                grid.get(i + 1).setSecondPlayerScore(dataGenerator.generateScore());
                grid.get(i + 1).setFinishTime(LocalDateTime.now().plusHours(2));
            }
        }

        database.put(tournament.getName(), grid);
        return grid;
    }

    @Override
    public Map summary(int id) {
        Tournament tournament = tournamentRepository.getOne(id);
        Map<Integer, Match> grid = database.get(tournament.getName());
        Map<Integer, Gridable> result= new LinkedHashMap<>();
        List<Gridable> tempList = new ArrayList<>();

        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i + 1) instanceof Match) {
                if (grid.get(i + 1).getFirstPlayerScore() > grid.get(i + 1).getSecondPlayerScore()) {
                    tempList.add(grid.get(i + 1).getFirstPlayer());
                    removeParticipant(tournament.getId(), grid.get(i + 1).getSecondPlayer());
                    tournamentRepository.save(tournament);
                } else {
                    tempList.add(grid.get(i + 1).getSecondPlayer());
                    removeParticipant(tournament.getId(), grid.get(i + 1).getFirstPlayer());
                    tournamentRepository.save(tournament);
                }
            } else {
                tempList.add(grid.get(i + 1));
            }
        }

        int i = 1;
        while (tempList.size()>1){
            Participant participant1 = (Participant) tempList.get(0);
            Participant participant2 = (Participant) tempList.get(1);

            Match match = new Match(participant1, participant2, 0, 0, null, null);
            result.put(i, match);
            i++;
            tempList.remove(participant1);
            tempList.remove(participant2);
        }

        database.put(tournament.getName(), result);
        return result;
    }

}
