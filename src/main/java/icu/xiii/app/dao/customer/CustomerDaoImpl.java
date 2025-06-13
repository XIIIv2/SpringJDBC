package icu.xiii.app.dao.customer;

import icu.xiii.app.dto.customer.CustomerDtoRequest;
import icu.xiii.app.entity.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository("CustomerDaoImpl")
public class CustomerDaoImpl implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setFullName(rs.getString("full_name"));
        customer.setEmail(rs.getString("email"));
        customer.setSocialSecurityNumber(rs.getString("social_security_number"));
        return customer;
    };

    @Override
    public boolean create(CustomerDtoRequest request) {
        String sql = "INSERT INTO customers (full_name, email, social_security_number) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Customer customer = new Customer(request);
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, customer.getFullName());
                ps.setString(2, customer.getEmail());
                ps.setString(3, customer.getSocialSecurityNumber());
                return ps;
            }, keyHolder);

            if (keyHolder.getKey() != null) {
                customer.setId(keyHolder.getKey().longValue());
            }
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Customer> fetchById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, customerRowMapper, id));
    }

    @Override
    public boolean updateById(Long id, CustomerDtoRequest request) {
        String sql = "UPDATE customers SET full_name = ?, email = ?, social_security_number = ? WHERE id = ?";
        Customer customer = new Customer(id, request);
        try {
            jdbcTemplate.update(sql,
                    customer.getFullName(),
                    customer.getEmail(),
                    customer.getSocialSecurityNumber(),
                    customer.getId());
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<List<Customer>> fetchAll() {
        String sql = "SELECT * FROM customers";
        Optional<List<Customer>> customers;
        try {
            customers = Optional.of(jdbcTemplate.query(sql, customerRowMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return customers;
    }

    @Override
    public Optional<Customer> getLastEntity() {
        String sql = "SELECT * FROM products ORDER BY id DESC LIMIT 1";
        Optional<Customer> customer;
        try {
            customer = Optional.ofNullable(jdbcTemplate.queryForObject(sql, customerRowMapper));
        } catch (DataAccessException e) {
            customer = Optional.empty();
        }
        return customer;
    }
}
