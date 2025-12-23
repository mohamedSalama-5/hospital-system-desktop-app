package main.java.dao;
import main.java.db.DBConnection;

import java.sql.*;

public class DepartmentDAO {

    //get id by name
    public Integer getIdByName(String name){
        String sql  = "SELECT id FROM department WHERE name = ?";
        Integer departmentId = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                departmentId = resultSet.getInt("id");
            }
        }catch (SQLException e){
            throw new RuntimeException("Failed get data from ",e);
        }
        return departmentId;
    }

}
