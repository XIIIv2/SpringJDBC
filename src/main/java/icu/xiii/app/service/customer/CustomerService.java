package icu.xiii.app.service.customer;

import icu.xiii.app.dto.customer.CustomerDtoRequest;
import icu.xiii.app.entity.Customer;
import icu.xiii.app.service.BaseService;

import java.util.List;

public interface CustomerService extends BaseService<Customer, CustomerDtoRequest> {
    Customer create(CustomerDtoRequest request);
    List<Customer> fetchAll();
    Customer fetchById(Long id);
    Customer updateById(Long id, CustomerDtoRequest request);
    boolean deleteById(Long id);
    Customer getLastEntity();
}
