package com.gmail.ddzhunenko.tournament.repository;

import com.gmail.ddzhunenko.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

}
