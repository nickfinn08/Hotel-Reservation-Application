package api;

import Models.IRoom;
import Models.Reservation;
import Service.*;
import Models.Customer;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final HotelResource instance = new HotelResource();

    private static final CustomerService customerS = CustomerService.getInstance();
    private static final ReservationService reservationS = ReservationService.getInstance();

    private HotelResource() {}
    public static HotelResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String email) {
        return customerS.getCustomer(email);
    }

    public void createACustomer(String firstName, String lastName, String email) {
        customerS.addCustomer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationS.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = customerS.getCustomer(customerEmail);
        return reservationS.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = customerS.getCustomer(customerEmail);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return reservationS.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationS.findRooms(checkIn, checkOut);
    }

}
