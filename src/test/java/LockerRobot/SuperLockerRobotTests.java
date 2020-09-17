package LockerRobot;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
