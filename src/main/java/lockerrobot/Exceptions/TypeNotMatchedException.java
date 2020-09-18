package lockerrobot.Exceptions;

import static lockerrobot.Exceptions.ErrorMessages.NOT_MATCHED_TICKET;

public class TypeNotMatchedException extends RuntimeException {
    public TypeNotMatchedException(String message) {
        super(message + NOT_MATCHED_TICKET);
    }
}
