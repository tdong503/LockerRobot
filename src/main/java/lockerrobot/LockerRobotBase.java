package lockerrobot;

import lockerrobot.Exceptions.FakeTicketException;
import lockerrobot.Exceptions.SetupNotMatchedException;
import lockerrobot.Exceptions.TypeNotMatchedException;

import java.util.List;

public abstract class LockerRobotBase implements Storable {
    protected List<Locker> lockers;
    private final Types robotType;

    public LockerRobotBase(List<Locker> lockers, Types robotType) {
        validateSetup(lockers, robotType);
        this.lockers = lockers;
        this.robotType = robotType;
    }

    public Bag pickUpBag(Ticket ticket) {
        if(ticket.getTicketType() != this.robotType) {
            throw new TypeNotMatchedException(this.getClass().getName());
        }

        Locker locker = lockers.stream().filter(l -> l.isTicketContained(ticket)).findFirst().orElseThrow(() -> new FakeTicketException(this.getClass().getName()));
        return locker.pickUpBag(ticket);
    }

    public boolean hasCapacity() {
        return lockers.stream().anyMatch(Locker::hasCapacity);
    }

    @Override
    public boolean isTicketContained(Ticket ticket) {
        return lockers.stream().anyMatch(l -> l.isTicketContained(ticket));
    }

    private void validateSetup(List<Locker> lockers, Types robotType) {
        for (Locker locker :
                lockers) {
            if (locker.getType() != robotType) {
                throw new SetupNotMatchedException(this.getClass().getName());
            }
        }
    }
}
