package main.java.dao;
import main.java.db.DBConnection;
import main.java.model.Nurse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NurseDAO {

    public Nurse insert(Nurse nurse) {
        String sql = "INSERT INTO nurse (f_name , l_name , national_id , specialization , salary , email , phone_number , employment_date , birth_date , department_id) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?) ";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            setStatement(statement,nurse);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                nurse.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurse;
    }

    public Nurse updateByNationalId(String nationalId , Nurse newData){
        String sql = "UPDATE nurse " +
                "SET f_name = ? , l_name = ? , national_id = ? , specialization = ? , salary = ? , email = ? , phone_number = ? , employment_date = ? , birth_date = ? , department_id = ? " +
                "WHERE national_id = ? ";
        int row =0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            setStatement(statement,newData);
            statement.setString(11,nationalId);
            row = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (row>0)?newData:null;
    }

    public Nurse findByNationalId(String national_id){
        String sql = "SELECT n.id , n.f_name , n.l_name , n.national_id , n.specialization , n.salary , n.email , n.phone_number , n.employment_date , n.birth_date , n.department_id ," +
                " department.name AS department_name FROM nurse n " +
                "JOIN department ON n.department_id = department.id " +
                "WHERE n.national_id = ?";
        Nurse nurse = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,national_id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                nurse = new Nurse(
                        result.getInt("id"),
                        result.getString("f_name"),
                        result.getString("l_name"),
                        result.getString("national_id"),
                        result.getString("email"),
                        result.getString("phone_number"),
                        result.getDate("birth_date"),
                        result.getString("specialization"),
                        result.getDouble("salary"),
                        result.getDate("employment_date"),
                        result.getInt("department_id"),
                        result.getString("department_name")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return nurse;
    }

    public List<Nurse> searchByName(String name){
        String sql = "SELECT n.id, n.f_name, n.l_name, n.national_id, n.specialization, n.salary, n.email, n.phone_number, n.employment_date, n.birth_date, n.department_id, department.name AS department_name " +
                "FROM nurse n " +
                "JOIN department ON n.department_id = department.id " +
                "WHERE n.f_name iLIKE ? OR n.l_name iLIKE ?" +
                "ORDER BY n.f_name, n.l_name ";
        List<Nurse> list = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            String searchName = "%"+name+"%";
            statement.setString(1,searchName);
            statement.setString(2,searchName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Nurse nurse = new Nurse(
                        resultSet.getInt("id"),
                        resultSet.getString("f_name"),
                        resultSet.getString("l_name"),
                        resultSet.getString("national_id"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("specialization"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("employment_date"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name")
                );
                list.add(nurse);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(String nationalId){
        String sql = "DELETE FROM nurse WHERE national_id = ?";
        int row = 0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,nationalId);
            row = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return row >0;
    }

    public void setStatement(PreparedStatement statement , Nurse nurse)throws SQLException{
        statement.setString(1, nurse.getFirstName());
        statement.setString(2, nurse.getLastName());
        statement.setString(3, nurse.getNationalId());
        statement.setString(4, nurse.getSpecialization());
        statement.setDouble(5, nurse.getSalary());
        statement.setString(6, nurse.getEmail());
        statement.setString(7, nurse.getPhoneNumber());
        statement.setDate(8, nurse.getEmploymentDate());
        statement.setDate(9, nurse.getBirthDate());
        statement.setInt(10, nurse.getDepartmentId());
    }

}
