package LockerRobot;

import LockerRobot.Exceptions.NoCapacityException;

import java.util.List;
import java.util.Optional;

public class LockerRobotManager {
    private List<Locker> lockers;
    private List<PrimaryLockerRobot> primaryLockerRobots;

    public LockerRobotManager(List<Locker> lockers, List<PrimaryLockerRobot> primaryLockerRobots) {
        this.lockers = lockers;
        this.primaryLockerRobots = primaryLockerRobots;
    }

    public Ticket saveBag(Bag bag) {
        if(primaryLockerRobots != null) {
            Optional<PrimaryLockerRobot> primaryLockerRobot = primaryLockerRobots.stream().filter(PrimaryLockerRobot::hasCapacity).findFirst();
            if(primaryLockerRobot.isPresent()) {
                return primaryLockerRobot.get().saveBag(bag);
            }
        }

        Locker locker = lockers.stream().filter(Locker::hasCapacity).findFirst().orElseThrow(() -> new NoCapacityException(this.getClass().getName()));
        return locker.saveBag(bag);
    }
}
