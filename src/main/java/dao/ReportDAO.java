package main.java.dao;
import main.java.db.DBConnection;
import main.java.model.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    // add report

    public Report insert(Report report , String patientNationalId , String doctorName){
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO  = new DoctorDAO();
        int effictedRow = 0;
        String sql = "INSERT INTO reports (report_date , description , patient_id , doctor_id )" +
                "VALUES(?,?,?,?)";
        Integer patientId = patientDAO.getIdByNationalId(patientNationalId);
        Integer doctorId = doctorDAO.getIdByName(doctorName);
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1,report.getReportDate());
            statement.setString(2,report.getDescription());
            statement.setInt(3,patientId);
            statement.setInt(4,doctorId);
            effictedRow = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();

        }
        return (effictedRow > 0 )?report: null;
    }

    // delete report
    public boolean deleteReport(String patientNationalId){
        PatientDAO patientDAO = new PatientDAO();
        Integer patientId = patientDAO.getIdByNationalId(patientNationalId);
        int effictedRow = 0;
        String sql = "DELETE From reports WHERE patient_id = ? ";
        try(Connection connection = DBConnection.getConnection();
           PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,patientId);
            effictedRow = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return effictedRow >0;
    }
    //update report

    public Report update(Report report, String patientNationalId){
        PatientDAO patientDAO = new PatientDAO();
        Integer patientId = patientDAO.getIdByNationalId(patientNationalId);
        int effictedRow = 0;
        String sql ="UPDATE reports " +
                "SET report_date = ? , description = ? " +
                "WHERE patient_id = ? ";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDate(1,report.getReportDate());
            statement.setString(2,report.getDescription());
            statement.setInt(3,patientId);
            effictedRow = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (effictedRow>0)?report:null;
    }
    // get by national id
    public List<Report> getByPatientNationalId(String patientNationalId){
        PatientDAO patientDAO = new PatientDAO();
        List<Report> list = new ArrayList<>();
        Integer patientId = patientDAO.getIdByNationalId(patientNationalId);
        String sql = "SELECT r.id, r.report_date, r.description, " +
                "d.f_name AS doctorFirstName, d.l_name AS doctorLastName, " +
                "p.f_name AS patientFirstName, p.l_name AS patientLastName " +
                "FROM reports r " +
                "JOIN doctor d ON r.doctor_id = d.id " +
                "JOIN patient p ON r.patient_id = p.id " +
                "WHERE r.patient_id = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int reportId = resultSet.getInt("id");
                java.sql.Date reportDate = resultSet.getDate("report_date");
                String description = resultSet.getString("description");

                String doctorFName = resultSet.getString("doctorFirstName");
                String doctorLName = resultSet.getString("doctorLastName");
                String doctorName = doctorFName + " " + doctorLName;

                String patientFName = resultSet.getString("patientFirstName");
                String patientLName = resultSet.getString("patientLastName");
                String patientName = patientFName + " " + patientLName;
                Report report = new Report(
                        reportId,
                        patientName,
                        patientNationalId,
                        reportDate,
                        description,
                        doctorName
                );
                list.add(report);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }





}
