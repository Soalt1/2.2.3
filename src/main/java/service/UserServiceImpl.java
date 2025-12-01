package service;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements service.UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(String name, String lastName, Integer age) {
        validateUserData(name, lastName, age);
        User user = new User(name, lastName, age);
        userDao.addUser(user);
    }

    @Override
    public void updateUser(Long id, String name, String lastName, Integer age) {
        validateUserData(name, lastName, age);

        User user = userDao.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }

        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    private void validateUserData(String name, String lastName, Integer age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Age must be less than 150");
        }
    }
}