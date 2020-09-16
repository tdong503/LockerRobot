package LockerRobot;

import LockerRobot.Exceptions.NoCapacityException;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimaryLockerRobotTests {
    private Types lockerType = Types.M;

    @Test
    void should_save_bag_in_1st_locker_and_return_M_ticket_when_save_bag_given_a_M_bag_and_all_lockers_has_capacity() {
        Bag bag = new Bag(Types.M);
        Locker firstLocker = new Locker(1, lockerType);
        Locker secondLocker = new Locker(1, lockerType);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(firstLocker, secondLocker));

        Ticket actual = primaryLockerRobot.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.M, actual.getTicketType());
        assertEquals(bag, firstLocker.pickUpBag(actual));
    }

    @Test
    void should_save_bag_in_2nd_locker_and_return_M_ticket_when_save_bag_given_a_M_bag_and_only_1st_locker_has_no_capacity() {
        Bag bag = new Bag(Types.M);
        Locker firstLocker = new Locker(0, lockerType);
        Locker secondLocker = new Locker(1, lockerType);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(firstLocker, secondLocker));

        Ticket actual = primaryLockerRobot.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.M, actual.getTicketType());
        assertEquals(bag, secondLocker.pickUpBag(actual));
    }

    @Test
    void should_not_save_bag_and_return_error_when_save_bag_given_a_M_bag_and_lockers_has_no_capacity() {
        Bag bag = new Bag(Types.M);
        Locker firstLocker = new Locker(0, lockerType);
        Locker secondLocker = new Locker(0, lockerType);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(firstLocker, secondLocker));

        assertThrows(NoCapacityException.class, () -> primaryLockerRobot.saveBag(bag));
    }

    @Test
    void should_get_corresponding_bag_when_pick_up_bag_given_a_valid_ticket() {
        Bag bag = new Bag(Types.M);
        Locker firstLocker = new Locker(1, lockerType);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(firstLocker));

        Ticket ticket = primaryLockerRobot.saveBag(bag);

        Bag actual = primaryLockerRobot.pickUpBag(ticket);

        assertEquals(bag, actual);
    }
}
