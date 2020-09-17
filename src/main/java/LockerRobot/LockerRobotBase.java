package LockerRobot;

import LockerRobot.Exceptions.FakeTicketException;
import LockerRobot.Exceptions.TypeNotMatchedException;

import java.util.List;

public abstract class LockerRobotBase implements Storable {
    protected List<Locker> lockers;
    private Types robotType;

    public LockerRobotBase(List<Locker> lockers, Types robotType) {
        this.lockers = lockers;
        this.robotType = robotType;
    }

    public abstract Ticket saveBag(Bag bag);

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
        return false;
    }
}
