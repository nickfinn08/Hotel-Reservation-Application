package Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import Models.Customer;

public class CustomerService {

    private static final CustomerService instance = new CustomerService();
    private static final Map<String, Customer> customers = new HashMap<>();
    private CustomerService() {}
    public static CustomerService getInstance() {
        return instance;
    }

    public final void addCustomer(String firstName, String lastName, String email) {
        email = normalize(email);
        if (!customers.containsKey(email)) {
            Customer customer = new Customer(firstName, lastName, email);
            customers.put(email, customer);
        }
    }

    public final Customer getCustomer(String customerEmail) {
        return customers.get(normalize(customerEmail));
    }

    public final Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    private String normalize(String email) {
        return email.trim().toLowerCase();
    }
}