package lockerrobot;

import lockerrobot.Exceptions.FakeTicketException;
import lockerrobot.Exceptions.NoCapacityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockerTests {

    private final Types lockerType = Types.S;

    @Test
    void should_save_bag_and_return_S_ticket_when_save_bag_given_a_S_bag_and_locker_has_capacity() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(1, lockerType);

        Ticket actual = locker.saveBag(bag);

        assertNotNull(actual);
        assertEquals(Types.S, actual.getTicketType());
    }

    @Test
    void should_not_save_bag_when_save_bag_given_a_S_bag_and_locker_has_no_capacity() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(0, lockerType);

        assertThrows(NoCapacityException.class, () -> locker.saveBag(bag));
    }

    @Test
    void should_get_corresponding_bag_when_pick_up_bag_given_a_valid_ticket() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(1, lockerType);

        Ticket ticket = locker.saveBag(bag);

        Bag actual = locker.pickUpBag(ticket);

        assertEquals(bag, actual);
    }

    @Test
    void should_throw_error_when_pick_up_bag_given_a_fake_ticket() {
        Bag bag = new Bag(Types.S);
        Locker locker = new Locker(1, lockerType);
        locker.saveBag(bag);

        Ticket ticket = new Ticket(Types.S);

        assertThrows(FakeTicketException.class, () -> locker.pickUpBag(ticket));
    }
}
