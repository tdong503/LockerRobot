package lockerrobot;

import lockerrobot.Exceptions.SetupNotMatchedException;
import lockerrobot.Exceptions.TypeNotMatchedException;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidateTests {
    @Test
    void should_not_get_bag_and_throw_error_when_locker_get_bag_given_a_M_ticker_generated_by_PrimaryLockerRobot() {
        Bag bag = new Bag(Types.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));
        Ticket ticket = primaryLockerRobot.saveBag(bag);

        Locker locker = new Locker(1, Types.S);

        assertThrows(TypeNotMatchedException.class, () -> locker.pickUpBag(ticket));
    }

    @Test
    void should_not_get_bag_and_throw_error_when_locker_get_bag_given_a_L_ticket_generated_by_SuperLockerRobot() {
        Bag bag = new Bag(Types.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));
        Ticket ticket = superLockerRobot.saveBag(bag);

        Locker locker = new Locker(1, Types.S);

        assertThrows(TypeNotMatchedException.class, () -> locker.pickUpBag(ticket));
    }

    @Test
    void should_not_get_bag_and_throw_error_when_PrimaryLockerRobot_get_bag_given_a_S_ticket_generated_by_locker() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(1, Types.S);
        Ticket ticket = locker.saveBag(bag);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));

        assertThrows(TypeNotMatchedException.class, () -> primaryLockerRobot.pickUpBag(ticket));
    }

    @Test
    void should_not_get_bag_and_throw_error_when_PrimaryLockerRobot_get_bag_given_a_L_ticket_generated_by_SuperLockerRobot() {
        Bag bag = new Bag(Types.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));
        Ticket ticket = superLockerRobot.saveBag(bag);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));

        assertThrows(TypeNotMatchedException.class, () -> primaryLockerRobot.pickUpBag(ticket));
    }

    @Test
    void should_not_get_bag_and_throw_error_when_SuperLockerRobot_get_bag_given_a_S_ticket_generated_by_locker() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(1, Types.S);
        Ticket ticket = locker.saveBag(bag);

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));

        assertThrows(TypeNotMatchedException.class, () -> superLockerRobot.pickUpBag(ticket));
    }

    @Test
    void should_not_get_bag_and_throw_error_when_SuperLockerRobot_get_bag_given_a_M_ticket_generated_by_PrimaryLockerRobot() {
        Bag bag = new Bag(Types.M);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(ImmutableList.of(new Locker(1, Types.M)));
        Ticket ticket = primaryLockerRobot.saveBag(bag);

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(ImmutableList.of(new Locker(1, Types.L)));

        assertThrows(TypeNotMatchedException.class, () -> superLockerRobot.pickUpBag(ticket));
    }

    @Test
    void should_throw_error_when_setup_LockerRobotManager_given_a_LockerRobotManager_and_a_M_locker() {
        Locker locker = new Locker(1, Types.M);

        List<Storable> storableList = ImmutableList.of(locker);

        assertThrows(SetupNotMatchedException.class, () -> new LockerRobotManager(storableList));
    }

    @Test
    void should_throw_error_when_setup_LockerRobotManager_given_a_LockerRobotManager_and_a_L_locker() {
        Locker locker = new Locker(1, Types.L);

        List<Storable> storableList = ImmutableList.of(locker);

        assertThrows(SetupNotMatchedException.class, () -> new LockerRobotManager(storableList));
    }

    @Test
    void should_throw_error_when_setup_PrimaryLockerRobot_given_a_S_locker() {
        Locker locker = new Locker(1, Types.S);

        List<Locker> lockers = ImmutableList.of(locker);

        assertThrows(SetupNotMatchedException.class, () -> new PrimaryLockerRobot(lockers));
    }

    @Test
    void should_throw_error_when_setup_PrimaryLockerRobot_given_a_L_locker() {
        Locker locker = new Locker(1, Types.L);

        List<Locker> lockers = ImmutableList.of(locker);

        assertThrows(SetupNotMatchedException.class, () -> new PrimaryLockerRobot(lockers));
    }

    @Test
    void should_throw_error_when_setup_SuperLockerRobot_given_a_S_locker() {
        Locker locker = new Locker(1, Types.S);

        List<Locker> lockers = ImmutableList.of(locker);

        assertThrows(SetupNotMatchedException.class, () -> new SuperLockerRobot(lockers));
    }

    @Test
    void should_throw_error_when_setup_SuperLockerRobot_given_a_M_locker() {
        Locker locker = new Locker(1, Types.M);

        List<Locker> lockers = ImmutableList.of(locker);

        assertThrows(SetupNotMatchedException.class, () -> new SuperLockerRobot(lockers));
    }
}
