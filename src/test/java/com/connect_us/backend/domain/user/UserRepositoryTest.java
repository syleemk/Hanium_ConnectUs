package com.connect_us.backend.domain.user;

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
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        String email = "test";
        String password = "aaaa";
        String name = "asdf";
        Role role = Role.SELLER;
        Social social= Social.NONE;

        userRepository.save(User.builder()
                            .email(email)
                            .password(password)
                            .social(social)
                            .role(role)
                            .name(name)
                            .build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getRole()).isEqualTo(role);
    }
}
