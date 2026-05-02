package Models;

public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, Double RoomPrice, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber: " + getRoomNumber() +
                ", price: 0.0" +
                ", roomType: " + getRoomType() +
                '}';
    }
}
