package core;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws Exception; // Retrieve all users
    User getUserById(int userId) throws Exception; // Retrieve user by ID
    void addUser(User user) throws Exception; // Add a new user
    void updateUser(User user) throws Exception; // Update an existing user
    void deleteUser(int userId) throws Exception; // Delete a user by ID
}
