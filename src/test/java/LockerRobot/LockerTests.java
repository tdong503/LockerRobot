package LockerRobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LockerTests {

    @Test
    void should_save_bag_and_return_S_ticket_when_save_bag_given_locker_has_capacity() {
        Bag bag = new Bag();
        Locker locker = new Locker(1);

        Ticket actual = locker.saveBag(bag);

        assertNotNull(actual);
    }
}
