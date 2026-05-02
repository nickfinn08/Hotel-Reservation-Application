package Models;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format!!");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer() {}

    // Validation method (took help from web)
    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        email = email.trim();
        // Simple regex for format: name@domain.extension
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Customer{" + "First Name: " + firstName + ", Last Name: " + lastName
                + ", Email: " + email + '}';
    }
}
