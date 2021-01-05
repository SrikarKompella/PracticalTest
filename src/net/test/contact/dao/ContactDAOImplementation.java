package net.test.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.test.contact.modal.Contact;

public class ContactDAOImplementation implements ContactDAO {

	private JdbcTemplate jdbcTemplate;

	public ContactDAOImplementation(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Contact c) {
		String sql = "INSERT into Contact (name,email,address,phone) VALUES (?,?,?,?)";

		return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getTelephone());
	}

	@Override
	public int update(Contact c) {
		String sql = "UPDATE Contact SET name=?,email=?,address=?,phone=? WHERE contact_id=?";
		return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getTelephone(), c.getId());
	}

	@Override
	public Contact get(Integer id) {
		String sql = "SELECT * FROM Contact WHERE contact_id=" + id;
		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String phone = rs.getString("phone");

					return new Contact(name, email, address, phone);
				}
				return null;
			}
		};

		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String sqlString = "DELETE from Contact WHERE contact_id="+id;
		return jdbcTemplate.update(sqlString);
	}

	@Override
	public List<Contact> list() {
			String sqlString = "SELECT * FROM Contact";
			RowMapper<Contact> rowMapper = new RowMapper<Contact>() {

				@Override
				public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
					Integer id = rs.getInt("contact_id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String phone = rs.getString("phone");

					return new Contact(id,name, email, address, phone);
				}
				
			};
			return jdbcTemplate.query(sqlString, rowMapper);
	}

}
