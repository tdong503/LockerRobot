package LockerRobot.Exceptions;

import static LockerRobot.Exceptions.ErrorMessages.NO_CAPACITY;

public class NoCapacityException extends RuntimeException {
    public NoCapacityException(String message) {
        super(message + NO_CAPACITY);
    }
}
