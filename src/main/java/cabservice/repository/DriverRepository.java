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

import cabservice.entity.BookedUserInfo;
import cabservice.entity.Driver;
import cabservice.entity.DriverDetails;
import cabservice.entity.History;
import cabservice.entity.User;

@Repository
public class DriverRepository {

	@Autowired
	private Properties Driversql;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	NamedParameterJdbcTemplate template;

	Driver d;

	public int addDriver(Driver dr) {
		String sql = Driversql.getProperty("addDriver");
		SqlParameterSource assign = new MapSqlParameterSource("name", dr.getName())
				.addValue("password", passwordEncoder.encode(dr.getPassword())).addValue("phone", dr.getPhone())
				.addValue("email", dr.getEmail()).addValue("gender", dr.getGender()).addValue("age", dr.getAge())
				.addValue("licence_num", dr.getLicence_num()).addValue("car_num", dr.getCar_num());
		return template.update(sql, assign);

	}

	public Optional<User> getUser(String email) {
		String sql = Driversql.getProperty("getUserName");
		SqlParameterSource assign = new MapSqlParameterSource("email", email);
		Optional<Driver> dr = Optional.ofNullable(template.queryForObject(sql, assign,
				(rs, c) -> new Driver(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
						rs.getLong("phone"), rs.getString("email"), rs.getString("gender"), rs.getInt("age"),
						rs.getString("licence_num"), rs.getString("car_num"), rs.getInt("onduty"),
						rs.getString("role"))));
		if (dr.isPresent()) {
			d = dr.get();
		}
		Optional<User> user = Optional.ofNullable(new User(d.getEmail(), d.getPassword(), d.getRole()));
		return user;
	}
	
	public BookedUserInfo bookedBy() {
		String sql = Driversql.getProperty("bookedBy");
		System.out.println(d.getId());
		SqlParameterSource assign = new MapSqlParameterSource("id", d.getId());
		return template.queryForObject(sql, assign,
					(rs, c) -> new BookedUserInfo(rs.getString("name"), rs.getLong("phone"), rs.getString("email")));
		
	}

	public DriverDetails myDetails() {
		return new DriverDetails(d.getId(), d.getName(), d.getPhone(), d.getEmail(), d.getGender(), d.getAge(),
				d.getLicence_num(), d.getCar_num(),d.getOnduty(), d.getRole());
	}

	public int onduty() {
		String sql = Driversql.getProperty("setduty");
		SqlParameterSource assign = new MapSqlParameterSource("id", d.getId()).addValue("value", 1);
		return template.update(sql, assign);
	}

	public int offduty() {
		String sql = Driversql.getProperty("setduty");
		SqlParameterSource assign = new MapSqlParameterSource("id", d.getId()).addValue("value", 0);
		return template.update(sql, assign);
	}

	public int inservice() {
		String sql = Driversql.getProperty("inservice");
		SqlParameterSource assign = new MapSqlParameterSource("id", d.getId());
		return template.queryForObject(sql, assign, Integer.class);
	}

	public int droped() {
		String sql = Driversql.getProperty("droped");
		SqlParameterSource assign = new MapSqlParameterSource("id", d.getId());
		return template.update(sql, assign);
	}

	public int deleteAccount() {
		String sql = Driversql.getProperty("deleteaccount");
		SqlParameterSource assign = new MapSqlParameterSource("id", d.getId());
		return template.update(sql, assign);
	}
	
	public List<History> getHistory() {
		String sql = Driversql.getProperty("gethistory");
		SqlParameterSource assign = new MapSqlParameterSource("id", d.getId());
		return template.query(sql, assign, (rs,c)-> new History(rs.getDate("date"),rs.getTime("time"),rs.getInt("customer_id"),rs.getInt("driver_id")));
	}
	
	public int updateData(String field, String value) {
		String sql = Driversql.getProperty("update") + " " + field + " " + Driversql.getProperty("updatetext");
		MapSqlParameterSource params = new MapSqlParameterSource("value", value).addValue("id", d.getId());
		return template.update(sql, params);
	}

}
