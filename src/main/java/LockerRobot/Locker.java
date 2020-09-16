package LockerRobot;

import LockerRobot.Exceptions.InvalidTicketException;
import LockerRobot.Exceptions.NoCapacityException;

import java.util.HashMap;

public class Locker {
    private int capacity;
    private HashMap<Ticket, Bag> bags = new HashMap<>();


    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket saveBag(Bag bag) {
        if(bags.size() == capacity) {
            throw new NoCapacityException(this.getClass().getName());
        }

        Ticket ticket = new Ticket(Types.S);
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
}
