package com.gmail.ddzhunenko.tournament.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime finishTime;

}
