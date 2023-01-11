import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayroll {

    List<Employee> employeeList;

    // Here we are connecting database(MySql) using java program :-
    public Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "Bharat@123";
        Connection connection;
        System.out.println("Connecting To Database : " + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection Is Successful : " + connection + "\n");
        return connection;
    }

    // Here we are getting the data from the database and added in the arraylist :-
    public void getData(ResultSet resultSet) throws SQLException {
        employeeList = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("Id"));
            employee.setName(resultSet.getString("Name"));
            employee.setSalary(resultSet.getDouble("Salary"));
            employee.setStart(resultSet.getDate("Start").toLocalDate());

            employeeList.add(employee);
        }
    }

    // Here we are give the queries and execute it for retrieve the data :-
    public void retrieveData() {
        employeeList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from employee_payroll";
            ResultSet resultSet = statement.executeQuery(sql);
            getData(resultSet);
            employeeList.forEach(System.out::println);
            System.out.println("Data Retrieve Successfully.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}