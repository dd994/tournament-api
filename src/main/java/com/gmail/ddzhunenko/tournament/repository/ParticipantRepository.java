package com.gmail.ddzhunenko.tournament.repository;

import com.gmail.ddzhunenko.tournament.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
}

