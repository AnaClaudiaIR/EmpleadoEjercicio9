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

            String sql = "SELECT SALARIO, COUNT(*)" +
                    " FROM EMPLEADO" +
                    " GROUP BY SALARIO" +
                    " ORDER BY COUNT(*) DESC";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               Double salario = resultSet.getDouble("SALARIO");
               int count = resultSet.getInt("COUNT(*)"); //Número de personas que tienen cada salario
                System.out.println("Salario: "+salario + " ->> " + count);
            }
        } catch (SQLException e){
            System.out.println("ERROR --> "+e.getMessage());
        }
    }
}
