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

import cabservice.entity.Bookings;
import cabservice.entity.CustomerDetails;
import cabservice.entity.DriverDetails;
import cabservice.entity.History;
import cabservice.entity.Management;
import cabservice.entity.User;

@Repository
public class ManagementRepository {

	@Autowired
	private Properties Managersql;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	NamedParameterJdbcTemplate template;

	Management m;

	public int addManager(Management m) {
		String sql = Managersql.getProperty("addManager");
		SqlParameterSource assign = new MapSqlParameterSource("name", m.getName())
				.addValue("password", passwordEncoder.encode(m.getPassword())).addValue("phone", m.getPhone())
				.addValue("email", m.getEmail());
		return template.update(sql, assign);

	}

	public Optional<User> getUser(String email) {
		String sql = Managersql.getProperty("getUserName");
		SqlParameterSource assign = new MapSqlParameterSource("email", email);
		Optional<Management> man = Optional.ofNullable(
				template.queryForObject(sql, assign, (rs, c) -> new Management(rs.getInt("id"), rs.getString("name"),
						rs.getString("password"), rs.getLong("phone"), rs.getString("email"), rs.getString("role"))));
		if (man.isPresent())
			m = man.get();
		return Optional.ofNullable(new User(man.get().getEmail(), man.get().getPassword(), man.get().getRole()));
	}

	public int deleteclientaccount(int id) {
		String sql = Managersql.getProperty("deleteclientaccount");
		SqlParameterSource assign = new MapSqlParameterSource("id", id);
		return template.update(sql, assign);
	}

	public int deleteemployeeaccount(int id) {
		String sql = Managersql.getProperty("deleteemployeeaccount");
		SqlParameterSource assign = new MapSqlParameterSource("id", id);
		return template.update(sql, assign);
	}

	public List<Bookings> viewBookings() {
		String sql = Managersql.getProperty("bookings");
		return template.query(sql, new MapSqlParameterSource(),
				(rs, c) -> new Bookings(rs.getInt("id"), rs.getInt("customer_id"), rs.getInt("driver_id")));
	}

	public List<History> clientHistory(int id) {
		String sql = Managersql.getProperty("getclienthistory");
		SqlParameterSource assign = new MapSqlParameterSource("id", id);
		return template.query(sql, assign, (rs, c) -> new History(rs.getDate("date"), rs.getTime("time"),
				rs.getInt("customer_id"), rs.getInt("driver_id")));
	}

	public List<History> employeeHistory(int id) {
		String sql = Managersql.getProperty("getemployeehistory");
		SqlParameterSource assign = new MapSqlParameterSource("id", id);
		return template.query(sql, assign, (rs, c) -> new History(rs.getDate("date"), rs.getTime("time"),
				rs.getInt("customer_id"), rs.getInt("driver_id")));
	}

	public List<CustomerDetails> allCustomers() {
		String sql = Managersql.getProperty("getAllCustomers");
		return template.query(sql, new MapSqlParameterSource(),
				(rs, c) -> new CustomerDetails(rs.getInt("id"), rs.getString("name"), rs.getLong("phone"),
						rs.getString("email"), rs.getString("gender"), rs.getInt("age"), rs.getString("role")));
	}

	public List<DriverDetails> allEmployees() {
		String sql = Managersql.getProperty("getAllemployees");
		return template.query(sql, new MapSqlParameterSource(),
				(rs, c) -> new DriverDetails(rs.getInt("id"), rs.getString("name"), rs.getLong("phone"),
						rs.getString("email"), rs.getString("gender"), rs.getInt("age"), rs.getString("licence_num"),
						rs.getString("car_num"), rs.getInt("onduty"), rs.getString("role")));
	}

	public int updateData(String field, String value) {
		String sql = Managersql.getProperty("update") + " " + field + " " + Managersql.getProperty("updatetext");
		MapSqlParameterSource params = new MapSqlParameterSource("value", value).addValue("id", m.getId());
		return template.update(sql, params);
	}

}
