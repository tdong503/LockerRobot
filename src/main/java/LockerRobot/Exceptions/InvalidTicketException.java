package LockerRobot.Exceptions;

import static LockerRobot.Exceptions.ErrorMessages.FAKE_TICKET;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException(String message) {
        super(message + FAKE_TICKET);
    }
}
