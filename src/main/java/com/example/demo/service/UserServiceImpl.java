package com.example.demo.service;
import static com.example.demo.TimeUtils.getTime;

import com.example.demo.exceptions.ServiceException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.StatusChangedResponse;
import com.example.demo.model.User;
import com.example.demo.model.UserStatus;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;


  @Override
  public Long saveUser(User user) throws ServiceException {
    if (isStatusSetOnline(user)){
      user.setLastUpdate(getTime());
    }
    return Optional.ofNullable(userRepository.save(user).getID())
                   .orElseThrow(() -> new ServiceException("Unable to save user"));
  }

  @Override
  public User getUser(Long ID) {
    return userRepository.findById(ID)
                         .orElseThrow(() -> new UserNotFoundException("User with ID " + ID + " doesn't exist"));
  }

  @Override
  public StatusChangedResponse changeStatus(Long ID, UserStatus userStatus){
    Optional<User> optionalUser = userRepository.findById(ID);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      if (isStatusSetOnline(user)) {
        user.setLastUpdate(getTime());
      }
      UserStatus current = user.getUserStatus();
      user.setUserStatus(userStatus);
      userRepository.save(user);
      return StatusChangedResponse.builder()
                                  .ID(ID)
                                  .currentStatus(userStatus)
                                  .lastStatus(current)
                                  .build();
    }
    throw new UserNotFoundException("User with ID " + ID + " doesn't exist");

  }

  public List<User> getAll(){
    return userRepository.findAll();
  }

  private boolean isStatusSetOnline(User user){
    if (Objects.nonNull(user.getUserStatus())){
      return user.getUserStatus()
                 .name()
                 .equals("ONLINE");
    }
    return false;

  }
}
