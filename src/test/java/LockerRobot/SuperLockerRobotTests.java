package LockerRobot;

import LockerRobot.Exceptions.FakeTicketException;
import LockerRobot.Exceptions.NoCapacityException;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuperLockerRobotTests {
    private Types lockerType = Types.L;

    @Test
    void should_save_bag_in_has_most_free_capacity_locker_and_return_L_ticket_when_save_bag_given_a_L_bag_and_all_lockers_has_capacity_but_1st_has_most_free_capacity() {
        Bag bag = new Bag(Types.L);
        Locker firstLocker = new Locker(2, lockerType);
        Locker secondLocker = new Locker(2, lockerType);
        secondLocker.saveBag(new Bag(Types.L));

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(firstLocker, secondLocker));

        Ticket actual = superLockerRobot.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.L, actual.getTicketType());
        assertEquals(bag, firstLocker.pickUpBag(actual));
    }

    @Test
    void should_save_bag_in_has_most_free_capacity_locker_and_return_L_ticket_when_save_bag_given_a_L_bag_and_all_lockers_has_capacity_but_2nd_has_most_free_capacity() {
        Bag bag = new Bag(Types.L);
        Locker firstLocker = new Locker(2, lockerType);
        Locker secondLocker = new Locker(2, lockerType);
        firstLocker.saveBag(new Bag(Types.L));

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(firstLocker, secondLocker));

        Ticket actual = superLockerRobot.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.L, actual.getTicketType());
        assertEquals(bag, secondLocker.pickUpBag(actual));
    }

    @Test
    void should_save_bag_in_1st_locker_and_return_L_ticket_when_save_bag_given_a_L_bag_and_all_lockers_has_same_free_capacity() {
        Bag bag = new Bag(Types.L);
        Locker firstLocker = new Locker(2, lockerType);
        Locker secondLocker = new Locker(2, lockerType);

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(firstLocker, secondLocker));

        Ticket actual = superLockerRobot.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.L, actual.getTicketType());
        assertEquals(bag, firstLocker.pickUpBag(actual));
    }

    @Test
    void should_not_save_bag_and_return_error_when_save_bag_given_all_lockers_has_no_capacity() {
        Bag bag = new Bag(Types.L);
        Locker firstLocker = new Locker(0, lockerType);
        Locker secondLocker = new Locker(0, lockerType);

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(firstLocker, secondLocker));

        assertThrows(NoCapacityException.class, () -> superLockerRobot.saveBag(bag));
    }

    @Test
    void should_get_corresponding_bag_when_pick_up_bag_given_a_valid_ticket() {
        Bag bag = new Bag(Types.L);
        Locker firstLocker = new Locker(1, lockerType);

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(firstLocker));

        Ticket ticket = superLockerRobot.saveBag(bag);

        Bag actual = superLockerRobot.pickUpBag(ticket);

        assertEquals(bag, actual);
    }

    @Test
    void should_not_get_bag_and_return_error_when_pick_up_bag_given_a_fake_ticket() {
        Bag bag = new Bag(Types.L);
        Locker firstLocker = new Locker(1, lockerType);

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(firstLocker));
        superLockerRobot.saveBag(bag);

        Ticket ticket = new Ticket(Types.M);

        assertThrows(FakeTicketException.class, () -> superLockerRobot.pickUpBag(ticket));
    }
}
