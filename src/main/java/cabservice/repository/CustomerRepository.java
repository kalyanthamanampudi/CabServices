package cabservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import cabservice.entity.AvailableCabs;
import cabservice.entity.Customer;
import cabservice.entity.CustomerDetails;
import cabservice.entity.History;
import cabservice.entity.User;

@Repository
public class CustomerRepository {

	@Autowired
	private Properties Customersql;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	NamedParameterJdbcTemplate template;

	private Customer c;

	public int addCustomer(Customer cu) {
		String sql = Customersql.getProperty("addCustomer");
		SqlParameterSource assign = new MapSqlParameterSource("name", cu.getName())
				.addValue("password", passwordEncoder.encode(cu.getPassword())).addValue("phone", cu.getPhone())
				.addValue("email", cu.getEmail()).addValue("gender", cu.getGender()).addValue("age", cu.getAge());
		return template.update(sql, assign);

	}

	public Optional<User> getUser(String email) {
		String sql = Customersql.getProperty("getUserName");
		SqlParameterSource assign = new MapSqlParameterSource("email", email);
		Optional<Customer> cu = Optional.ofNullable(template.queryForObject(sql, assign,
				(rs, c) -> new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
						rs.getLong("phone"), rs.getString("email"), rs.getString("gender"), rs.getInt("age"),
						rs.getString("role"))));
		if (cu.isPresent()) {
			c = cu.get();
		}
		Optional<User> user = Optional.ofNullable(new User(c.getEmail(), c.getPassword(), c.getRole()));
		return user;

	}

	public List<AvailableCabs> getAvailablecabs() {
		String sql = Customersql.getProperty("getAvailablecabs");
		List<AvailableCabs> cabsavailable = template.query(sql, new MapSqlParameterSource(),
				(rs, c) -> new AvailableCabs(rs.getInt("id"), rs.getString("name"), rs.getLong("phone"),
						rs.getString("email"), rs.getString("car_num")));
		System.out.println(cabsavailable);
		return cabsavailable;
	}

	public int bookCab(int id) {
		String sql = Customersql.getProperty("bookCab");
		SqlParameterSource assign = new MapSqlParameterSource("customer_id", c.getId()).addValue("driver_id", id);
		return template.update(sql, assign);
	}

	public CustomerDetails myDetails() {
		return new CustomerDetails(c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getGender(), c.getAge(),
				c.getRole());
	}

	public int cancelRide() {
		String sql = Customersql.getProperty("cancelride");
		SqlParameterSource assign = new MapSqlParameterSource("customer_id", c.getId());
		return template.update(sql, assign);
	}

	public int deleteAccount() {
		String sql = Customersql.getProperty("deleteaccount");
		SqlParameterSource assign = new MapSqlParameterSource("customer_id", c.getId());
		return template.update(sql, assign);
	}

	public List<History> getHistory() {
		String sql = Customersql.getProperty("gethistory");
		SqlParameterSource assign = new MapSqlParameterSource("customer_id", c.getId());
		return template.query(sql, assign, (rs, c) -> new History(rs.getDate("date"), rs.getTime("time"),
				rs.getInt("customer_id"), rs.getInt("driver_id")));
	}

	public int updateData(String field, String value) {
		String sql = Customersql.getProperty("update") + " " + field + " " + Customersql.getProperty("updatetext");
		MapSqlParameterSource params = new MapSqlParameterSource("value", value).addValue("id", c.getId());
		return template.update(sql, params);
	}

}
