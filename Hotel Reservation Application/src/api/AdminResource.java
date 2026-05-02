package api;

import Models.Customer;
import Models.IRoom;
import Service.CustomerService;
import Service.ReservationService;
import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static final AdminResource instance = new AdminResource();

    private static final CustomerService customerS = CustomerService.getInstance();
    private static final ReservationService reservationS = ReservationService.getInstance();

    private AdminResource() {}
    public static AdminResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String email) {
        return customerS.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        if(rooms == null) {
            throw new IllegalArgumentException("Rooms list can't be null");
        }
        for(IRoom room : rooms) {
            reservationS.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationS.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerS.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationS.printAllReservation();
    }
}
