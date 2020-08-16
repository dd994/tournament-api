package com.gmail.ddzhunenko.tournament.exceptions;

public class NotUniqueNicknameException extends Exception {

    public String toString() {
        return "Error. Nickname already used.";
    }
}
