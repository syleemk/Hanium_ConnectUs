package com.connect_us.backend.service;

import com.connect_us.backend.config.auth.dto.UserDto;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.user.User;
import com.connect_us.backend.domain.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void saveTest() throws Exception{
        //given
        UserDto user = new UserDto();
        user.setEmail("aaa@aaa.com");
        user.setPassword("1234");
        user.setName("aaa");
        user.setRole(Role.USER);
        //when
        Long saveId = userService.save(user);
        //then
       //assertEquals(user,userRepository.findById(saveId));
        List<User> userList = userRepository.findAll();
        assertThat(userList.get(0).getEmail().equals("aaa@aaa.com"));
        System.out.println(userList.get(0).getEmail());
        System.out.println(userList.get(0).getPassword());
    }

    @Test
    public void loadByUsernameTest() throws Exception{
        //given
        String e = "aaa@aaa.com";
        //when

        //then

        }

}