package LockerRobot;

import LockerRobot.Exceptions.FakeTicketException;
import LockerRobot.Exceptions.NoCapacityException;
import LockerRobot.Exceptions.TypeNotMatchedException;

import java.util.HashMap;

public class Locker implements Storable {
    private int capacity;
    private HashMap<Ticket, Bag> bags = new HashMap<>();
    private Types lockerType;

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
