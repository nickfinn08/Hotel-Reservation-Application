import Service.CustomerService;
import api.HotelResource;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Models.*;

public class MainMenu {

    private static final HotelResource resource = HotelResource.getInstance();
    private static final AdminMenu menu = new AdminMenu();

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        boolean running = true;

        while (running) {
            menu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    menu.start();
                    break;
                case "5":
                    running = false;
                    System.out.println("Logging Out");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void menu(){
        System.out.println("\n===== Hotel Reservation Menu =====");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    private static void createAccount() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        try {
            resource.createACustomer(firstName, lastName, email);
            System.out.println("Account created successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createAccount(String email){
        System.out.println("Creating an account with email: " + email);

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        try {
            resource.createACustomer(firstName, lastName, email);
            System.out.println("Account created successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void seeMyReservations() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        try {
            Collection<Reservation> reservations = resource.getCustomersReservations(email);
            if (reservations.isEmpty()) {
                System.out.println("No reservations found.");
            } else {
                for (Reservation r : reservations) {
                    System.out.println(r);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void findAndReserveRoom() {
        CustomerService service = CustomerService.getInstance();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        Customer customer = new Customer();

        while(!customer.isValidEmail(email)){
            System.out.print("Invalid email. Please try again.\nEnter your email: ");
            email = scanner.nextLine();
        }
        if(service.getCustomer(email) == null) {
            createAccount(email);
        }
        try {
            System.out.print("Enter check-in date(Format: yyyy-MM-dd): ");
            Date checkIn = parseDate(scanner.nextLine());

            System.out.print("Enter check-out date(Format: yyyy-MM-dd): ");
            Date checkOut = parseDate(scanner.nextLine());

            Collection<IRoom> rooms = resource.findARoom(checkIn, checkOut);

            if (rooms.isEmpty()) {
                System.out.println("No rooms available for the selected dates.");

                Calendar cal = Calendar.getInstance();
                cal.setTime(checkIn);
                cal.add(Calendar.DATE, 7);
                Date newCheckIn = cal.getTime();
                cal.setTime(checkOut);
                cal.add(Calendar.DATE, 7);
                Date newCheckOut = cal.getTime();

                System.out.println("Searching for after 7 days");
                System.out.println("New dates: " + newCheckIn + " to " + newCheckOut);

                rooms = resource.findARoom(newCheckIn, newCheckOut);

                if (rooms.isEmpty()) {
                    System.out.println("Still no rooms available.");
                    return;
                }

                checkIn = newCheckIn;
                checkOut = newCheckOut;
            }

            System.out.println("Available rooms:");
            for (IRoom room : rooms) {
                System.out.println(room);
            }

            System.out.print("Enter room number to book: ");
            String roomNumber = scanner.nextLine();

            IRoom room = resource.getRoom(roomNumber);
            if (room == null) {
                System.out.println("Room not found.");
                return;
            }

            Reservation reservation = resource.bookARoom(
                    email, room, checkIn, checkOut
            );

            System.out.println("Reservation successful!");
            System.out.println(reservation);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Took help from web
    private static Date parseDate(String input) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        return sdf.parse(input);
    }
}