package LockerRobot.Exceptions;

import static LockerRobot.Exceptions.ErrorMessages.NOT_MATCHED_TICKET;

public class TypeNotMatchedException extends RuntimeException {
    public TypeNotMatchedException(String message) {
        super(message + NOT_MATCHED_TICKET);
    }
}
