package icu.xiii.app.service.customer;

import icu.xiii.app.dao.customer.CustomerDao;
import icu.xiii.app.dto.customer.CustomerDtoRequest;
import icu.xiii.app.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public Customer create(CustomerDtoRequest request) {
        Objects.requireNonNull(request, "Parameter [request] must not be null!");
        if (customerDao.create(request)) {
            return customerDao.getLastEntity().orElse(null);
        }
        return null;
    }

    @Override
    public List<Customer> fetchAll() {
        return customerDao.fetchAll().orElse(Collections.emptyList());
    }

    @Override
    public Customer fetchById(Long id) {
        Objects.requireNonNull(id, "Parameter [id] must not be null!");
        return customerDao.fetchById(id).orElse(null);
    }

    @Override
    public Customer updateById(Long id, CustomerDtoRequest request) {
        Objects.requireNonNull(request, "Parameter [request] must not be null!");
        Objects.requireNonNull(id, "Parameter [id] must not be null!");
        if (customerDao.fetchById(id).isPresent()) {
            customerDao.updateById(id, request);
        }
        return customerDao.fetchById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id, "Parameter [id] must not be null!");
        if (customerDao.fetchById(id).isPresent()) {
            return customerDao.deleteById(id);
        }
        return false;
    }

    @Override
    public Customer getLastEntity() {
        return customerDao.getLastEntity().orElse(null);
    }
}
