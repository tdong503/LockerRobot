package LockerRobot;

import LockerRobot.Exceptions.FakeTicketException;
import LockerRobot.Exceptions.NoCapacityException;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot {
    private List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        Locker locker = lockers.stream().filter(Locker::hasCapacity).max(Comparator.comparingInt(Locker::freeCapacityRate)).orElseThrow(() -> new NoCapacityException(this.getClass().getName()));
        return locker.saveBag(bag);
    }

    public Bag pickUpBag(Ticket ticket) {
        Locker locker = lockers.stream().filter(l -> l.isTicketContained(ticket)).findFirst().orElseThrow(() -> new FakeTicketException(this.getClass().getName()));
        return locker.pickUpBag(ticket);
    }
}