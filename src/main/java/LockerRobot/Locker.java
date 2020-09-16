package LockerRobot;

import java.util.HashMap;

public class Locker {
    private int capacity;
    private HashMap<Ticket, Bag> bags = new HashMap<>();


    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket saveBag(Bag bag) {
        Ticket ticket = new Ticket();
        bags.put(ticket, bag);
        return ticket;
    }
}
