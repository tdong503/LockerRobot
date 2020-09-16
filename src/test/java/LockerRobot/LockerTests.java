package LockerRobot;

import LockerRobot.Exceptions.NoCapacityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LockerTests {

    @Test
    void should_save_bag_and_return_S_ticket_when_save_bag_given_a_S_bag_and_locker_has_capacity() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(1);

        Ticket actual = locker.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.S, actual.getTicketType());
    }

    @Test
    void should_not_save_bag_when_save_S_bag_given_a_S_bag_and_locker_has_no_capacity() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(0);

        assertThrows(NoCapacityException.class, () -> locker.saveBag(bag));
    }


}
