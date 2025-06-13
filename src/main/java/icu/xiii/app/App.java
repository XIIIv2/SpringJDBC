package icu.xiii.app;

import icu.xiii.app.config.AppContext;
import icu.xiii.app.dao.customer.CustomerDao;
import icu.xiii.app.dto.customer.CustomerDtoRequest;
import icu.xiii.app.entity.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

        CustomerDao customerDao = context.getBean(CustomerDao.class);

        customerDao.create(new CustomerDtoRequest(
                0L,
                "John Doe",
                "john.doe@example.com",
                "123-45-6789"
        ));

        customerDao.create(new CustomerDtoRequest(
                0L,
                "Jane Doe",
                "jane.doe@example.com",
                "123-67-4589"
        ));

        customerDao.create(new CustomerDtoRequest(
                0L,
                "Bob Smith",
                "bob.smith@example.com",
                "123-89-6745"
        ));

        System.out.println("All customers:");
        customerDao.fetchAll().ifPresent(list -> list.forEach(System.out::println));

        Customer customer = customerDao.fetchById(3L).orElse(null);
        if (customer != null) {
            System.out.println("Found customer by ID: " + customer);
            customer.setEmail("new.email@example.com");
            if (customerDao.updateById(customer.getId(), new CustomerDtoRequest(
                    customer.getId(),
                    customer.getFullName(),
                    customer.getEmail(),
                    customer.getSocialSecurityNumber()))) {
                System.out.println("Customer updated: " + customer);
            }
        }

        if (customerDao.deleteById(2L)) {
            System.out.println("Customer with ID 2 has been deleted.");
        }

        System.out.println("All customers:");
        customerDao.fetchAll().ifPresent(list -> list.forEach(System.out::println));

        context.close();
    }
}
