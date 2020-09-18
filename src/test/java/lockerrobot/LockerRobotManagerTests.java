package lockerrobot;

import lockerrobot.Exceptions.FakeTicketException;
import lockerrobot.Exceptions.NoCapacityException;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockerRobotManagerTests {
    @Test
    void should_save_bag_and_return_S_ticket_when_save_bag_given_a_S_bag_and_locker_has_capacity() {
        Bag bag = new Bag(Types.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(new Locker(1, Types.S)));

        Ticket actual = lockerRobotManager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.S, actual.getTicketType());
    }

    @Test
    void should_not_save_bag_and_return_error_when_save_bag_given_a_S_bag_and_locker_has_no_capacity() {
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

    @Test
    void should_not_save_bag_and_return_error_when_save_bag_given_a_M_bag_and_a_PrimaryLockerRobot_has_no_capacity() {
        Bag bag = new Bag(Types.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(0, Types.M)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(primaryLockerRobot));

        assertThrows(NoCapacityException.class, () -> lockerRobotManager.saveBag(bag));
    }

    @Test
    void should_save_bag_and_return_L_ticket_when_save_bag_given_a_L_bag_and_a_SuperLockerRobot_has_capacity() {
        Bag bag = new Bag(Types.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(superLockerRobot));

        Ticket actual = lockerRobotManager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.L, actual.getTicketType());
    }

    @Test
    void should_not_save_bag_and_return_error_when_save_bag_given_a_L_bag_and_a_SuperLockerRobot_has_no_capacity() {
        Bag bag = new Bag(Types.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(0, Types.M)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(superLockerRobot));

        assertThrows(NoCapacityException.class, () -> lockerRobotManager.saveBag(bag));
    }

    @Test
    void should_save_bag_and_return_S_ticket_when_save_bag_given_a_S_bag_and_a_locker_and_a_PrimaryLockerRobot_and_a_SuperLockerRobot_and_all_has_capacity() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(1, Types.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(locker, primaryLockerRobot, superLockerRobot));

        Ticket actual = lockerRobotManager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.S, actual.getTicketType());
        assertEquals(bag, locker.pickUpBag(actual));
    }

    @Test
    void should_save_bag_and_return_M_ticket_when_save_bag_given_a_M_bag_and_a_locker_and_a_PrimaryLockerRobot_and_a_SuperLockerRobot_and_all_has_capacity() {
        Bag bag = new Bag(Types.M);
        Locker locker = new Locker(1, Types.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(locker, primaryLockerRobot, superLockerRobot));

        Ticket actual = lockerRobotManager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.M, actual.getTicketType());
        assertEquals(bag, primaryLockerRobot.pickUpBag(actual));
    }

    @Test
    void should_save_bag_and_return_L_ticket_when_save_bag_given_a_L_bag_and_a_locker_and_a_PrimaryLockerRobot_and_a_SuperLockerRobot_and_all_has_capacity() {
        Bag bag = new Bag(Types.L);
        Locker locker = new Locker(1, Types.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(locker, primaryLockerRobot, superLockerRobot));

        Ticket actual = lockerRobotManager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.L, actual.getTicketType());
        assertEquals(bag, superLockerRobot.pickUpBag(actual));
    }

    @Test
    void should_get_corresponding_bag_when_pick_up_bag_given_a_locker_and_a_valid_ticket_generated_by_locker() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(1, Types.S);

        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(locker));

        Ticket ticket = lockerRobotManager.saveBag(bag);

        Bag actual = lockerRobotManager.pickUpBag(ticket);

        assertEquals(bag, actual);
    }

    @Test
    void should_not_get_bag_and_return_error_when_pick_up_bag_given_a_locker_and_a_PrimaryLockerRobot_and_a_SuperLockerRobot_and_a_fake_ticket() {
        Bag bag = new Bag(Types.M);
        Locker locker = new Locker(1, Types.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(ImmutableList.of(locker, primaryLockerRobot, superLockerRobot));

        Ticket ticket = new Ticket(Types.M);

        assertThrows(FakeTicketException.class, () -> primaryLockerRobot.pickUpBag(ticket));
    }
}
