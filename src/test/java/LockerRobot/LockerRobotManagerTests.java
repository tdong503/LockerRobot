package LockerRobot;

import LockerRobot.Exceptions.NoCapacityException;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LockerRobotManagerTests {
    @Test
    void should_save_bag_and_return_S_ticket_when_save_bag_given_a_S_bag_and_locker_has_capacity() {
        Bag bag = new Bag(Types.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(new Locker(1, Types.S)));

        Ticket actual = lockerRobotManager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.S, actual.getTicketType());
    }

    @Test
    void should_not_save_bag_when_save_bag_given_a_S_bag_and_locker_has_no_capacity() {
        Bag bag = new Bag(Types.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(new Locker(0, Types.S)));

        assertThrows(NoCapacityException.class, () -> lockerRobotManager.saveBag(bag));
    }

    @Test
    void should_save_bag_and_return_M_ticket_when_save_bag_given_a_M_bag_and_a_PrimaryLockerRobot_has_capacity() {
        Bag bag = new Bag(Types.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(primaryLockerRobot));

        Ticket actual = lockerRobotManager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.M, actual.getTicketType());
    }
}
