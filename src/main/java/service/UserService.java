package service;

import model.User;
import java.util.List;

public interface  UserService {
    void createUser(String name, String lastName, Integer age);
    void updateUser(Long id, String name, String lastName, Integer age);
    void deleteUser(Long id);
    List<User> getAllUsers();
}