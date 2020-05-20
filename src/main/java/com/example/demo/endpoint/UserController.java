package com.example.demo.endpoint;

import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.StatusChangedResponse;
import com.example.demo.model.User;
import com.example.demo.model.UserStatus;
import com.example.demo.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Data
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Long saveUser(@RequestBody @Valid User userJson){
    return userService.saveUser(userJson);

  }

  @RequestMapping(value = "user", method = RequestMethod.GET)
  public User getUser(@RequestParam("ID") Long ID) {
    return userService.getUser(ID);
  }

  @RequestMapping(value = "user", method = RequestMethod.PUT)
  public StatusChangedResponse changeStatus(@RequestParam ("ID") Long ID, @RequestParam("userStatus") String userStatus){
    UserStatus userStatus1;
    try {
      userStatus1 = UserStatus.valueOf(userStatus.toUpperCase());
    }catch (Exception e){
      throw new ServiceException("Status can be only ONLINE, AWAY, OFFLINE");
    }
   return userService.changeStatus(ID, userStatus1);
  }

  @RequestMapping(value = "users", method = RequestMethod.GET)
  public List<User> getAll(){
    return userService.getAll();
  }


}
