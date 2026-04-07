package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()
        );  Statement statement = connection.createStatement()) {
            String sql = "SELECT ROUND(AVG(SALARIO),2)" +
                    " FROM EMPLEADO";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Double mediaSalario = resultSet.getDouble("ROUND(AVG(SALARIO),2)");
                System.out.println("Media salario de todos los empleados ->> "+ mediaSalario);
            }
        } catch (SQLException e){
            System.out.println("ERROR --> "+e.getMessage());
        }
    }
}
