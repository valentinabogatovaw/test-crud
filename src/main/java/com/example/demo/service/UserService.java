package com.example.demo.service;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.StatusChangedResponse;
import com.example.demo.model.User;
import com.example.demo.model.UserStatus;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;

public interface UserService {

  Optional<Long> saveUser(User user) throws ServiceException;

  Optional<User> getUser(Long ID);

  StatusChangedResponse changeStatus(Long ID, UserStatus userStatus);

  List<User> getAll();



}
