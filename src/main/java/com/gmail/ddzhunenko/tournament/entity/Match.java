package com.gmail.ddzhunenko.tournament.entity;

import com.gmail.ddzhunenko.tournament.interfaces.Gridable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Match implements Gridable {

    private Participant firstPlayer;
    private Participant secondPlayer;
    private int firstPlayerScore;
    private int secondPlayerScore;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;

}
