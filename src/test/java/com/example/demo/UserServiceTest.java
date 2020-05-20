package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.StatusChangedResponse;
import com.example.demo.model.User;
import com.example.demo.model.UserStatus;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import java.util.Optional;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  @BeforeEach
  public void testSetup() {
    userService = new UserServiceImpl(userRepository);
  }

  @Test
  public void should_return_user(){
    User user = new User();
    user.setID(1L);
    user.setName("name");
    user.setEmail("email");
    user.setUserStatus(UserStatus.OFFLINE);
    given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));
    assertEquals(userService.getUser(1L), user);
  }

  @Test
  public void get_user_which_doesnt_exist(){
    given(userRepository.findById(1L)).willReturn(Optional.empty());
    Assertions.assertThrows(UserNotFoundException.class, () -> {
      userService.getUser(1L);
    });
  }

  @Test
  public void should_save_user(){
    User user = new User();
    user.setID(1L);
    user.setName("name");
    user.setEmail("email");
    user.setUserStatus(UserStatus.OFFLINE);
    given(userRepository.save(user)).willReturn(user);
    assertEquals(userService.saveUser(user), user.getID());
  }

  @Test
  public void should_change_status(){
    Long ID = 1L;
    given(userRepository.findById(ID)).willReturn(Optional.of(getUser()));
    StatusChangedResponse status = StatusChangedResponse.builder()
                                                        .ID(ID)
                                                        .currentStatus(UserStatus.ONLINE)
                                                        .lastStatus(getUser().getUserStatus())
                                                        .build();
    assertEquals(userService.changeStatus(1L, UserStatus.ONLINE), status);
  }

  @Test
  public void should_throw_usernotfound(){
    Long ID = 1L;
    given(userRepository.findById(ID)).willReturn(Optional.empty());
    Assertions.assertThrows(UserNotFoundException.class, () -> {
      userService.getUser(1L);
    });
  }

  private User getUser(){
    User user = new User();
    user.setID(1L);
    user.setName("name");
    user.setEmail("email");
    user.setUserStatus(UserStatus.OFFLINE);
    return user;
  }








}
