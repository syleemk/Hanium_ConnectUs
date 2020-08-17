package com.connect_us.backend.domain.users;

import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @After
    public void cleanUp() {
        usersRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        String email = "test";
        String password = "aaaa";
        String name = "asdf";
        Role role = Role.SELLER;
        Social social= Social.NONE;

        usersRepository.save(Users.builder()
                            .email(email)
                            .password(password)
                            .social(social)
                            .role(role)
                            .name(name)
                            .build());

        List<Users> usersList = usersRepository.findAll();

        Users users = usersList.get(0);
        assertThat(users.getEmail()).isEqualTo(email);
        assertThat(users.getRole()).isEqualTo(role);
    }
}
