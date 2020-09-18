package lockerrobot;

public class Ticket {
    private Types ticketType;

    public Ticket(Types ticketType) {
        this.ticketType = ticketType;
    }

    public Types getTicketType() {
        return ticketType;
    }
}
