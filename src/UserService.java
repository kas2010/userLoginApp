import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class UserService {
    private static Connection getConnect() throws ClassNotFoundException, SQLException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "user_login";
        String dbUsername = "root";
        String dbPassword = "12345";

        Class.forName(dbDriver).getDeclaredConstructor().newInstance();
        Connection conn = DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPassword);
        return conn;
    }

    public static User getUser(String login, String password) {
        try (Connection connection = getConnect()) {
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from users where login ='"
                        + login + "' and password = '" + password + "'");
                resultSet.first();
                User user = new User(resultSet.getString("id"),
                        resultSet.getString("surname"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("password"),
                        resultSet.getString("born"),
                        resultSet.getString("counter"));

                return user;

            } catch (SQLException e) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static User createUser(User user) {
        try (Connection connection = getConnect()) {
            try {

                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into users(" +
                        "name, surname, login, password, born, counter) values (" +
                        "'" + user.getName() + "', " +
                        "'" + user.getSurname() + "', " +
                        "'" + user.getLogin()+ "', " +
                        "'" + user.getPassword() + "', " +
                        "'" + user.getBorn().toString() + "', " +
                        + user.getCounter() + ")");

                ResultSet resultSet = statement.executeQuery("select id from users where login = '" + user.getLogin() + "'");
                resultSet.first();
                user.setId(resultSet.getLong("id"));

                return user;

            } catch (SQLException e) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> validate(User user) {
        List<String> errors = new ArrayList<>();

        boolean isError = false;

        if (UserService.checkNotNull(user)) {
            errors.add("Not all fields are completed. Please fill them.");
            return errors;
        }

        int passwordLength = 5;

        if (UserService.checkPasswordLength(user, passwordLength)) {
            errors.add("Password must be longer than " + passwordLength + ".");
            isError = true;
        }

        if (UserService.checkPassword(user)) {
            errors.add("Passwords do not match.");
            isError = true;
        }

        int minAge = 5;

        if (UserService.checkYoungAge(user, minAge)) {
            errors.add("Age too young!");
            isError = true;
        }

        int maxAge = 150;

        if (UserService.checkOldAge(user, maxAge)) {
            errors.add("Age too old!");
            isError = true;
        }

        if (isError) {
            errors.add("There are errors in the form. Correct them please.");
        }

        return errors;
    }

    public static boolean checkNotNull(User user) {
        if (user.getName() == null ||
                user.getSurname() == null ||
                user.getLogin() == null ||
                user.getPassword() == null ||
                user.getRepassword() == null ||
                user.getBorn() == null ) {
            return true;
        }

        return false;
    }

    public static boolean checkPassword(User user) {
        if (user.getPassword() == null || user.getRepassword() == null) {
            return true;
        }

        if (!user.getPassword().equals(user.getRepassword())) {
            return true;
        }

        return false;
    }

    public static boolean checkPasswordLength(User user, int minPasswordLength) {
        if (user.getPassword() == null || user.getRepassword() == null) {
            return true;

        }

        if (user.getPassword().length() < minPasswordLength) {
            return true;
        }

        return false;
    }

    public static boolean checkYoungAge(User user, int minAge) {
        if (user.getBorn() == null) {
            return true;
        }

        int age = Period.between(user.getBorn(), LocalDate.now()).getYears();

        if (age < minAge) {
            return true;
        }

        return false;
    }

    public static boolean checkOldAge(User user, int maxAge) {
        if (user.getBorn() == null) {
            return true;
        }

        int age = Period.between(user.getBorn(), LocalDate.now()).getYears();

        if (age > maxAge) {
            return true;
        }

        return false;
    }

    public static void setCounter(Long user_id, Integer counter) {
        try (Connection connection = getConnect()) {
            try {

                Statement statement = connection.createStatement();
                statement.executeUpdate("update users set counter = " + counter + " where id = " + user_id);

            } catch (SQLException e) {}
        } catch (Exception e) {}
    }
}
