package com.gmail.ddzhunenko.tournament.exceptions;

public class NotFoundNicknameException extends  Exception{

    public String toString() {
        return "Error. Nickname not exist.";
    }
}
