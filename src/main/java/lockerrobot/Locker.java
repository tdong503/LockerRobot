package lockerrobot;

import lockerrobot.Exceptions.FakeTicketException;
import lockerrobot.Exceptions.NoCapacityException;
import lockerrobot.Exceptions.TypeNotMatchedException;

import java.util.HashMap;

public class Locker implements Storable {
    private final int capacity;
    private final HashMap<Ticket, Bag> bags = new HashMap<>();
    private final Types lockerType;

    public Locker(int capacity, Types lockerType) {
        this.capacity = capacity;
        this.lockerType = lockerType;
    }

    @Override
    public Ticket saveBag(Bag bag) {
        if (!this.hasCapacity()) {
            throw new NoCapacityException(this.getClass().getName());
        }

        Ticket ticket = new Ticket(lockerType);
        bags.put(ticket, bag);
        return ticket;
    }

    @Override
    public Bag pickUpBag(Ticket ticket) {
        if (ticket.getTicketType() != this.lockerType) {
            throw new TypeNotMatchedException(this.getClass().getName());
        }

        Bag bag = bags.get(ticket);
        if (bag == null) {
            throw new FakeTicketException(ticket.getClass().getName());
        }

        return bag;
    }

    @Override
    public boolean hasCapacity() {
        return bags.size() < capacity;
    }

    @Override
    public boolean isTicketContained(Ticket ticket) {
        return bags.containsKey(ticket);
    }

    public int freeCapacityRate() {
        return (capacity - bags.size()) / capacity;
    }
}
