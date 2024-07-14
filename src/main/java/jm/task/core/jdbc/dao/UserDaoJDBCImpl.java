package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        connection = getConnection();
    }

    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE IF NOT EXISTS USERS(ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255), LASTNAME VARCHAR(255), AGE TINYINT, PRIMARY KEY (ID))";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS USERS";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM USERS";
        List<User> listUsers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM USERS";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
