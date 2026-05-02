🏨 Hotel Reservation System (Java CLI)

A console-based hotel reservation application built using core Java and Object-Oriented Programming principles. The system allows users to search, book rooms, manage customers, and handle reservations with proper validation and error handling.

🚀 Features
👤 Customer Features
Create a new account
View all personal reservations
Search for available rooms
Book rooms for selected dates
Automatic suggestion of alternative dates (+7 days) if no rooms are available
🛠 Admin Features
Add new rooms
View all rooms
View all customers
View all reservations
🧠 Key Concepts Used
Object-Oriented Programming (OOP)
Encapsulation
Abstraction (IRoom interface)
Inheritance (FreeRoom extends Room)
Polymorphism
Design Patterns
Singleton Pattern (Service & Resource layers)
Java Concepts
Collections (Map, List)
Exception Handling (try-catch)
Date handling (java.util.Date, Calendar)
Input validation
📂 Project Structure
src/
│
├── api/
│   ├── AdminResource.java
│   └── HotelResource.java
│
├── Models/
│   ├── Customer.java
│   ├── Room.java
│   ├── FreeRoom.java
│   ├── Reservation.java
│   ├── IRoom.java
│   └── RoomType.java
│
├── Service/
│   ├── CustomerService.java
│   └── ReservationService.java
│
├── AdminMenu.java
└── MainMenu.java
🔒 Design Highlights
Singleton Pattern
Ensures a single shared instance for:
CustomerService
ReservationService
HotelResource
AdminResource
Validation & Error Handling
Prevents:
Invalid email formats
Duplicate room numbers
Invalid room types
Past date bookings
Empty/null inputs
Uses try-catch blocks for graceful error handling
Reservation Logic
Prevents overlapping bookings
Allows adjacent bookings (check-out = next check-in)
Suggests new availability if rooms are unavailable
🧪 Example Flow
User creates an account
Searches for available rooms
If unavailable → system suggests rooms for +7 days
User selects room and confirms booking
Reservation is stored and can be viewed later
⚙️ How to Run
Compile the project:
javac MainMenu.java
Run the application:
java MainMenu
