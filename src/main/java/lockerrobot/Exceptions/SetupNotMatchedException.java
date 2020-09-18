package lockerrobot.Exceptions;

import static lockerrobot.Exceptions.ErrorMessages.NOT_MATCHED_SETUP;

public class SetupNotMatchedException extends RuntimeException {
    public SetupNotMatchedException(String message) {
        super(message + NOT_MATCHED_SETUP);
    }
}
