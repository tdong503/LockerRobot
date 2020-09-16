package LockerRobot;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot {
    private List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        Locker locker = lockers.stream().filter(Locker::hasCapacity).max(Comparator.comparingInt(Locker::freeCapacityRate)).get();
        return locker.saveBag(bag);
    }
}
