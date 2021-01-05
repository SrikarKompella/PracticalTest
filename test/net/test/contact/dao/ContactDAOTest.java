package net.test.contact.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import net.test.contact.modal.Contact;

class ContactDAOTest {
	
	private DriverManagerDataSource datasource;
	private ContactDAO dao;
	
	@BeforeEach
	void setupBeforeEach() {
		datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/contactdb?allowPublicKeyRetrieval=true&useSSL=false");
		datasource.setUsername("root");
		datasource.setPassword("abcd1234");
		dao = new ContactDAOImplementation(datasource);
	}

	@Test
	void testSave() {
		
		Contact contact = new Contact("Robert Lewandowski","robert@bayern.com","Munich GE","135345");
		int result = dao.save(contact);
		assertTrue(result>0);
	}

	@Test
	void testUpdate() {
		Contact contact = new Contact(1, "Robert Lewandowski","robert.lewa@bayern.com","Munich GE","13534553234");
		int result = dao.update(contact);
		assertTrue(result>0);
	}

	@Test
	void testGet() {
		Integer idInteger = 3;
		Contact contact = dao.get(idInteger);
		if(contact!=null) {
			System.out.print(contact);
		}
		assertNotNull(contact);
	}

	@Test
	void testDelete() {
		Integer id = 2;
		int result = dao.delete(id);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<Contact> contactList = dao.list();
		for(Contact contact: contactList) {
			System.out.println(contact);
		}
		assertTrue(!contactList.isEmpty());
	}

}
