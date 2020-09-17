package LockerRobot;

import LockerRobot.Exceptions.NoCapacityException;

import java.util.List;

public class LockerRobotManager {
    private List<Storable> storeEquipments;

    public LockerRobotManager(List<Storable> storeEquipments) {
        this.storeEquipments = storeEquipments;
    }

    public Ticket saveBag(Bag bag) {
        Storable locker = storeEquipments.stream().filter(Storable::hasCapacity).findFirst().orElseThrow(() -> new NoCapacityException(this.getClass().getName()));
        return locker.saveBag(bag);
    }
}
