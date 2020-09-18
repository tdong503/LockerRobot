package lockerrobot;

public interface Storable {
    Ticket saveBag(Bag bag);

    Bag pickUpBag(Ticket ticket);

    boolean hasCapacity();

    boolean isTicketContained(Ticket ticket);

    Types getType();
}
