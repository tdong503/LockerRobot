package LockerRobot;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerRobotManagerTests {
    @Test
    void should_save_bag_and_return_S_ticket_when_save_bag_given_a_S_bag_and_locker_has_capacity() {
        Bag bag = new Bag(Types.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(new Locker(1, Types.S)));

        Ticket actual = lockerRobotManager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.S, actual.getTicketType());
    }
}
