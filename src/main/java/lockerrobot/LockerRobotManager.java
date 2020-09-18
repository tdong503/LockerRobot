package lockerrobot;

import lockerrobot.Exceptions.FakeTicketException;
import lockerrobot.Exceptions.NoCapacityException;
import lockerrobot.Exceptions.SetupNotMatchedException;

import java.util.List;

public class LockerRobotManager {
    private final List<Storable> storeEquipments;

    public LockerRobotManager(List<Storable> storeEquipments) {
        validateSetup(storeEquipments);
        this.storeEquipments = storeEquipments;
    }

    public Ticket saveBag(Bag bag) {
        Storable locker = storeEquipments.stream().filter(l -> l.hasCapacity() && l.getType().equals(bag.getTypes())).findFirst().orElseThrow(() -> new NoCapacityException(this.getClass().getName()));
        return locker.saveBag(bag);
    }

    public Bag pickUpBag(Ticket ticket) {
        Storable locker = storeEquipments.stream().filter(l -> l.isTicketContained(ticket)).findFirst().orElseThrow(() -> new FakeTicketException(this.getClass().getName()));
        return locker.pickUpBag(ticket);
    }

    private void validateSetup(List<Storable> storeEquipments) {
        for (Storable storable :
                storeEquipments) {
            if (storable.getClass().equals(Locker.class) && storable.getType() != Types.S) {
                throw new SetupNotMatchedException(this.getClass().getName());
            }
        }
    }
}
