package LockerRobot;

import LockerRobot.Exceptions.NoCapacityException;

import java.util.List;

public class PrimaryLockerRobot extends LockerRobotBase {

    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers, Types.M);
    }

    @Override
    public Ticket saveBag(Bag bag) {
        Locker locker = lockers.stream().filter(Locker::hasCapacity).findFirst().orElseThrow(() -> new NoCapacityException(this.getClass().getName()));
        return locker.saveBag(bag);
    }
}
