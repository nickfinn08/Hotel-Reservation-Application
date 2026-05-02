package Models;

import java.util.Date;

public class Reservation {

    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Dates cannot be empty or null");
        }

        Date past = new Date();
        if (checkInDate.before(past)) {
            throw new IllegalArgumentException("Check-in date can't be before today");
        }

        if (!checkOutDate.after(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        if (room == null){
            throw new IllegalArgumentException("Room cannot be null");
        }

        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public final Customer getCustomer() {
        return customer;
    }

    public final IRoom getRoom() {
        return room;
    }

    public final Date getCheckInDate() {
        return checkInDate;
    }

    public final Date getCheckOutDate() {
        return checkOutDate;
    }

    public String toString(){
        return "Reservation{" + "Customer: " + customer + ", room = " + room +
                ", CheckInDate: " + checkInDate + ", CheckOutDate: " + checkOutDate +
                '}';
    }

}
