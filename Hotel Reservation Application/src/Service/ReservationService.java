package Service;

import java.util.*;
import Models.IRoom;
import Models.Reservation;
import Models.Customer;

public class ReservationService {

    private static final ReservationService instance = new ReservationService();
    private ReservationService() {}
    public static ReservationService getInstance() {
        return instance;
    }

    private static final Map<String, IRoom> rooms = new HashMap<>();
    private static final Collection<Reservation> reservation = new ArrayList<>();

    public final void addRoom(IRoom room) {
        String roomNumber = room.getRoomNumber();
        if (roomNumber == null || roomNumber.isEmpty()) {
            throw new IllegalArgumentException("Room number cannot be null or empty.");
        }

        if (rooms.containsKey(roomNumber)) {
            throw new IllegalArgumentException("Room number already exists!!");
        }
        rooms.put(roomNumber, room);
    }

    public final IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public final Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {

        if (!isRoomAvailable(room, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("No Rooms available for the selected dates");
        }

        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        ReservationService.reservation.add(reservation);
        return reservation;
    }

    public final Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        List<IRoom> available = new ArrayList<>();

        for (IRoom room : rooms.values()) {
            boolean isAvailable = true;

            for (Reservation r : reservation) {
                if (r.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                    if (!(checkOutDate.before(r.getCheckInDate()) ||
                            checkInDate.after(r.getCheckOutDate()))) {
                        isAvailable = false;
                        break;
                    }
                }
            }

            if (isAvailable) {
                available.add(room);
            }
        }
        return available;
    }

    public final Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> result = new ArrayList<>();

        for (Reservation r : reservation) {
            if (r.getCustomer().equals(customer)) {
                result.add(r);
            }
        }
        return result;
    }

    public final void printAllReservation() {
        for (Reservation r : reservation) {
            System.out.println(r);
        }
    }

    public final Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    private boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
        for (Reservation r : reservation) {
            if (r.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                Date existingCheckIn = r.getCheckInDate();
                Date existingCheckOut = r.getCheckOutDate();

                if (checkInDate.before(existingCheckOut) && checkOutDate.after(existingCheckIn)) {
                    return false;
                }
            }
        }
        return true;
    }
}