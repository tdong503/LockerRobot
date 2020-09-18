package lockerrobot;

import lockerrobot.Exceptions.NoCapacityException;

import java.util.List;

public class LockerRobotManager {
    private final List<Storable> storeEquipments;

    public LockerRobotManager(List<Storable> storeEquipments) {
        this.storeEquipments = storeEquipments;
    }

    public Ticket saveBag(Bag bag) {
        Storable locker = storeEquipments.stream().filter(l -> l.hasCapacity() && l.getType().equals(bag.getTypes())).findFirst().orElseThrow(() -> new NoCapacityException(this.getClass().getName()));
        return locker.saveBag(bag);
    }
}
