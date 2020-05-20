package com.example.demo.service;

import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.StatusChangedResponse;
import com.example.demo.model.User;
import com.example.demo.model.UserStatus;
import java.util.List;

public interface UserService {

  Long saveUser(User user) throws ServiceException;

  User getUser(Long ID);

  StatusChangedResponse changeStatus(Long ID, UserStatus userStatus);

  List<User> getAll();



}
