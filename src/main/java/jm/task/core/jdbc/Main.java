package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 30);
        userService.saveUser("Name4", "LastName4", (byte) 35);

        userService.getAllUsers().stream().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
