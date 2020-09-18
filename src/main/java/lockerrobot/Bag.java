package lockerrobot;

public class Bag {
    private Types bagType;

    public Bag(Types type) {
        this.bagType = type;
    }

    public Types getTypes() {
        return this.bagType;
    }
}
