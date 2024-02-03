package jfs.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jfs.backend.config.AppConstant;
import jfs.backend.entity.Role;
import jfs.backend.repository.RoleRepo;

@SpringBootApplication
public class NewBloggerAppWithSwagger3Application implements CommandLineRunner{

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(NewBloggerAppWithSwagger3Application.class, args);

	}
	
	@Override
	public void run(String... args) throws Exception {

		try {

			Role role = new Role();
			role.setId(AppConstant.ROLE_ADMIN);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstant.ROLE_USER);
			role1.setName("ROLE_USER");

			List<Role> roles = List.of(role, role1);

			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName());
			});
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}

	}

}
