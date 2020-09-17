package LockerRobot;

import java.util.List;

public class LockerRobotManager {
    private List<Locker> lockers;

    public LockerRobotManager(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        Locker locker = lockers.stream().filter(Locker::hasCapacity).findFirst().get();
        return locker.saveBag(bag);
    }
}
