package main.java.dao;

import main.java.db.DBConnection;
import main.java.model.Clinic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicDAO {

    // get all
    public List<Clinic> getAll(){
        List<Clinic> list = new ArrayList<>();
        String sql ="SELECT c.* , d.name AS department_name  FROM clinic c " +
            "JOIN department d ON c.department_id = d.id ";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Clinic clinic = new Clinic(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name")
                );
                list.add(clinic);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }

    public Integer getClinicId(String clinicName){
        Integer clinicId = null;
        String sql = " SELECT id FROM clinic WHERE name = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,clinicName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                clinicId = resultSet.getInt("id");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return clinicId;
    }


}
