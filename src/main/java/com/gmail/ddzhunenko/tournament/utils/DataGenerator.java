package com.gmail.ddzhunenko.tournament.utils;

import com.gmail.ddzhunenko.tournament.entity.Match;
import com.gmail.ddzhunenko.tournament.entity.Participant;
import com.gmail.ddzhunenko.tournament.entity.Tournament;
import com.gmail.ddzhunenko.tournament.interfaces.Gridable;

import java.util.*;

public class DataGenerator {

    private final int multiple = 8;

    public Map<Integer, Gridable> setGrid(Tournament tournament) {

        Map<String, Integer> counts = getEliminationTypeCounts(tournament.getParticipantsCount());
        Map<Integer, Gridable> map = new LinkedHashMap<>();

        List<Participant> nicknames = tournament.getParticipants();

        int pair = counts.get("pair");
        int single = counts.get("single");

        for (int i = 1; i <= pair; i++) {

            Participant participant1 = null;
            Participant participant2 = null;

            if (nicknames.size() > 1) {
                int number1 = new Random().nextInt(nicknames.size());
                participant1 = nicknames.get(number1);
                nicknames.remove(number1);

                int number2 = new Random().nextInt(nicknames.size());
                participant2 = nicknames.get(number2);
                nicknames.remove(number2);
            }

            Match match = new Match(participant1, participant2, 0, 0, null, null);

            map.put(i, match);
        }

        if (single > 0) {
            for (int i = pair + 1; i <= pair + single; i++) {
                int number1 = new Random().nextInt(nicknames.size());
                Participant participant = nicknames.get(number1);
                nicknames.remove(number1);
                map.put(i, participant);
            }
        }
        return map;
    }


    private Map<String, Integer> getEliminationTypeCounts(int count) {
        int pairCount = 0;
        int gridSize = maxGridStartSize(count);

        if (count % multiple == 0 && count % 3 != 0) {
            pairCount = count / 2;
            count = 0;
        } else {
            while (count + pairCount != gridSize) {
                count = count - 2;
                pairCount++;
            }
        }

        return new HashMap<>(Map.of("single", count, "pair", pairCount));
    }

    private int maxGridStartSize(int n) {
        int result = 0;
        int count = 0;

        for (int i = 1; n > result; i++) {
            result = (int) Math.pow(2, i);
            count = i;
        }
        return (int) Math.pow(2, count - 1);
    }

    public int generateScore() {
        return (int) (Math.random() * 1000);
    }


}

