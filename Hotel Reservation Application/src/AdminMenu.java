import java.util.*;
import Models.*;
import api.AdminResource;

public class AdminMenu {

    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;

        while (running) {
            menu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addRoom();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void menu() {
        System.out.println("\n===== Admin Menu =====");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    private void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private void addRoom() {
        try {
            String roomNumber;
            while (true) {
                System.out.print("Enter room number: ");
                roomNumber = scanner.nextLine().trim();
                if (roomNumber.isEmpty()) {
                    System.out.println("Room number cannot be empty!!");
                } else {
                    break;
                }
            }

            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());

            RoomType roomType = null;

            while (roomType == null) {
                System.out.print("Enter room type (1 for SINGLE, 2 for DOUBLE): ");
                try {
                    int num = Integer.parseInt(scanner.nextLine());
                    if (num == 1) {
                        roomType = RoomType.SINGLE;
                    } else if (num == 2) {
                        roomType = RoomType.DOUBLE;
                    } else {
                        System.out.println("Invalid input!!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input!!");
                }
            }

            IRoom room;

            if (price == 0) {
                room = new FreeRoom(roomNumber, 0.0, roomType);
            } else {
                room = new Room(roomNumber, price, roomType);
            }

            adminResource.addRoom(List.of(room));
            System.out.println("Room added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}