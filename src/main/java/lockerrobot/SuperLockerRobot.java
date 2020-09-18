package lockerrobot;

import lockerrobot.Exceptions.NoCapacityException;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot extends LockerRobotBase {

    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers, Types.L);
    }

    @Override
    public Ticket saveBag(Bag bag) {
        Locker locker = lockers.stream().filter(Locker::hasCapacity).max(Comparator.comparingInt(Locker::freeCapacityRate)).orElseThrow(() -> new NoCapacityException(this.getClass().getName()));
        return locker.saveBag(bag);
    }
}
