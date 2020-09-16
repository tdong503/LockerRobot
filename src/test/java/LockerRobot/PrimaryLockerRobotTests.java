package LockerRobot;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}