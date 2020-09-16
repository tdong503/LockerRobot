package LockerRobot.Exceptions;

import static LockerRobot.Exceptions.ErrorMessages.FAKE_TICKET;

public class FakeTicketException extends RuntimeException {
    public FakeTicketException(String message) {
        super(message + FAKE_TICKET);
    }
}
