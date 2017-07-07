package ro.ace.ucv.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ro.ace.ucv.entity.Blog;
import ro.ace.ucv.entity.Item;
import ro.ace.ucv.entity.Role;
import ro.ace.ucv.entity.User;
import ro.ace.ucv.repository.BlogRepository;
import ro.ace.ucv.repository.ItemRepository;
import ro.ace.ucv.repository.RoleRepository;
import ro.ace.ucv.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		
		List<Role> roles = new ArrayList<Role>(); 
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		
		userRepository.save(userAdmin);
		
		Blog blogJavavids = new Blog();
		blogJavavids.setName("JavaVids");
		blogJavavids.setUrl("http://www.feedforall.com/sample.xml");
		blogJavavids.setUser(userAdmin);
		blogRepository.save(blogJavavids);
		
		Item item1 =  new Item();
		item1.setBlog(blogJavavids);
		item1.setTitle("first");
		item1.setLink("www.google.com");
		item1.setPublishedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		itemRepository.save(item1);
		
		Item item2 =  new Item();
		item2.setBlog(blogJavavids);
		item2.setTitle("second");
		item2.setLink("www.google.com");
		item2.setPublishedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		itemRepository.save(item2);
		
	}
}
