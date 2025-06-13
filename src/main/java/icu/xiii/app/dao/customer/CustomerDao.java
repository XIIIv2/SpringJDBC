package icu.xiii.app.dao.customer;

import icu.xiii.app.dao.BaseDao;
import icu.xiii.app.dto.customer.CustomerDtoRequest;
import icu.xiii.app.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao extends BaseDao<Customer, CustomerDtoRequest> {
    boolean create(CustomerDtoRequest request);
    Optional<List<Customer>> fetchAll();
    Optional<Customer> fetchById(Long id);
    boolean updateById(Long id, CustomerDtoRequest request);
    boolean deleteById(Long id);
    Optional<Customer> getLastEntity();
}
