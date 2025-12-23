package main.java.dao;
import main.java.model.Department;
import main.java.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    // add
    public Department insert(Department department){
        String sql = "INSERT INTO department (name , work_hours ) " +
                "VALUES (? , ? ) ";
        int affectedRows = 0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1,department.getName());
            statement.setInt(2,department.getWorkHours());
            affectedRows = statement.executeUpdate();
            if(affectedRows == 0){return null;}
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    department.setDepartmentId(rs.getInt(1));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return (affectedRows > 0)?department : null;
    }

    // delete
    public boolean delete(String departmentName){
        int affectedRows = 0;
        String sql ="DELETE FROM department WHERE name = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,departmentName);
            affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRows >0;
    }


    // view
    public List<Department> getAll(){
        String sql = " SELECT * FROM department";
        List<Department> list = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Department department = new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("work_hours"),
                        resultSet.getDate("created_at")
                );
                list.add(department);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //search
    public List<Department> searchByName(String name){
        List<Department>list = new ArrayList<>();
        String sql = "SELECT * FROM department WHERE name iLIKE ? ";
        String searchName = "%"+name+"%";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,searchName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Department department = new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("work_hours"),
                        resultSet.getDate("created_at")
                );
                list.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

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
