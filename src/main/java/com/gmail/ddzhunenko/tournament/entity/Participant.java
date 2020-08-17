package com.gmail.ddzhunenko.tournament.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gmail.ddzhunenko.tournament.interfaces.Gridable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "participants")
public class Participant implements Serializable, Gridable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;
    @Column(name = "nickname", unique = true)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Tournament tournament;

    public Participant(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    // only by nickname comparing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return getNickname().equals(that.getNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickname());
    }
}
