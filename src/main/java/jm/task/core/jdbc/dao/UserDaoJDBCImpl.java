package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Util util;

    public UserDaoJDBCImpl() {
        util = new Util();
    }

    public void createUsersTable() {
        Statement statement = null;
        String SQL_CREATE = "CREATE TABLE IF NOT EXISTS jmpreproject.users (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try(Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            statement.execute(SQL_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        String SQL_DROP = "DROP TABLE IF EXISTS users;";
        try(Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            statement.execute(SQL_DROP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement statement = null;
        String SQL_UPDATE = "INSERT INTO users(name, lastName, age) "
                +"VALUES (?, ?, ?);";
        try(Connection connection = util.getConnection()) {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        PreparedStatement statement = null;
        String SQL_DELETE = "DELETE FROM users WHERE id = ?;";
        try(Connection connection = util.getConnection()) {
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Statement statement = null;
        List<User> userList = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM users;";
        try(Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        String SQL_TRUNCATE = "TRUNCATE TABLE users;";
        try(Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(SQL_TRUNCATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
