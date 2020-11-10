package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Ivan", "Ivanov", (byte) 18);
        service.saveUser("Ilon", "Grey", (byte) 31);
        service.saveUser("Alex", "Petrov", (byte) 24);
        service.saveUser("Mike", "Anderson", (byte) 55);
        for(User user : service.getAllUsers()){
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}