package LockerRobot;

import LockerRobot.Exceptions.NoCapacityException;

import java.util.List;

public class PrimaryLockerRobot {
    private List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        Locker locker = lockers.stream().filter(Locker::hasCapacity).findFirst().orElseThrow(() -> new NoCapacityException(this.getClass().getName()));
        return locker.saveBag(bag);
    }

    public Bag pickUpBag(Ticket ticket) {
        Locker locker = lockers.stream().filter(l -> l.isTicketContained(ticket)).findFirst().get();
        return locker.pickUpBag(ticket);
    }
}
