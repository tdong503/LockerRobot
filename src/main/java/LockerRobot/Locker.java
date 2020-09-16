package LockerRobot;

import LockerRobot.Exceptions.InvalidTicketException;
import LockerRobot.Exceptions.NoCapacityException;

import java.util.HashMap;

public class Locker {
    private int capacity;
    private HashMap<Ticket, Bag> bags = new HashMap<>();
    private Types lockerType;

    public Locker(int capacity, Types lockerType) {
        this.capacity = capacity;
        this.lockerType = lockerType;
    }

    public Ticket saveBag(Bag bag) {
        if(!this.hasCapacity()) {
            throw new NoCapacityException(this.getClass().getName());
        }

        Ticket ticket = new Ticket(lockerType);
        bags.put(ticket, bag);
        return ticket;
    }

    public Bag pickUpBag(Ticket ticket) {
        Bag bag = bags.get(ticket);
        if(bag == null) {
            throw new InvalidTicketException(ticket.getClass().getName());
        }

        return bag;
    }

    public boolean hasCapacity() {
        return bags.size() < capacity;
    }
}
