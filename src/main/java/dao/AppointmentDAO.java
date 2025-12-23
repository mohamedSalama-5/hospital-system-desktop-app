package main.java.dao;
import main.java.db.DBConnection;
import main.java.model.Appointment;
import main.java.model.Clinic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    // add appointment
    public Appointment insert(Appointment appointment , String patientNationalId , String clinicName ){
        int effectedRow =0;
        PatientDAO patientDAO = new PatientDAO();
         ClinicDAO clinicDAO = new ClinicDAO();
         int patientId = patientDAO.getIdByNationalId(patientNationalId);
         int clinicId = clinicDAO.getClinicId(clinicName);
         String patientName = patientDAO.getPatientNameByNationalId(patientNationalId);
         String sql = "INSERT INTO appointment (appointment_date , status , patient_id , clinic_id )" +
                 "VALUES(? , ? , ? ,? ) ";

         try(Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
             statement.setDate(1,appointment.getAppointmentDate());
             statement.setString(2,appointment.getStatus());
             statement.setInt(3,patientId);
             statement.setInt(4,clinicId);
              effectedRow = statement.executeUpdate();
             if (effectedRow > 0) {
                 // Get generated appointment id
                 ResultSet generatedKeys = statement.getGeneratedKeys();
                 if (generatedKeys.next()) {
                     int appointmentId = generatedKeys.getInt(1);
                     appointment.setAppointmentId(appointmentId);
                 }
                 appointment.setPatientId(patientId);
                 appointment.setClinicId(clinicId);
                 appointment.setClinicName(clinicName);
                 appointment.setPatientName(patientName);
                 return appointment;
             }
         }catch (SQLException e){
             e.printStackTrace();
         }
        return null;
    }

    // update appointment
    public Appointment updateAppointment(Appointment newAppointment){
        String sql = "UPDATE appointment SET appointment_date = ?, status = ? WHERE id = ?";
        int effectedRow = 0;

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setDate(1, newAppointment.getAppointmentDate());
            statement.setString(2, newAppointment.getStatus());
            statement.setInt(3, newAppointment.getId());

            effectedRow = statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return (effectedRow > 0) ? newAppointment : null;
    }

    // delete appointment

    public boolean deleteByAppointmentId(int appointmentId) {
        String sql = "DELETE FROM appointment WHERE id = ?";
        int effectedRow = 0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            effectedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effectedRow > 0;
    }

    // get by patientNationalId

    public List<Appointment> getAppointmentByNationalId(String patientNationalId){
        PatientDAO patientDAO = new PatientDAO();
        List<Appointment> list = new ArrayList<>();
        Integer patientId = patientDAO.getIdByNationalId(patientNationalId);
        if (patientId == null) return list;

        String sql = "SELECT a.id, a.appointment_date, a.status, " +
                "c.name AS clinic_name, " +
                "p.f_name AS patient_f_name, p.l_name AS patient_l_name " +
                "FROM appointment a " +
                "JOIN clinic c ON a.clinic_id = c.id " +
                "JOIN patient p ON a.patient_id = p.id " +
                "WHERE a.patient_id = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String patientName = resultSet.getString("patient_f_name") + " " + resultSet.getString("patient_l_name");
                Appointment appointment = new Appointment(
                        resultSet.getInt("id"),
                        resultSet.getDate("appointment_date"),
                        resultSet.getString("status"),
                        resultSet.getString("clinic_name")
                );
                appointment.setPatientName(patientName);
                list.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // get all appointment
    public List<Appointment> getAllAppointment(){
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.id, a.appointment_date, a.status, " +
                "c.name AS clinic_name, " +
                "p.f_name AS patient_f_name, p.l_name AS patient_l_name " +
                "FROM appointment a " +
                "JOIN clinic c ON a.clinic_id = c.id " +
                "JOIN patient p ON a.patient_id = p.id";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String patientName = resultSet.getString("patient_f_name") + " " + resultSet.getString("patient_l_name");
                Appointment appointment = new Appointment(
                        resultSet.getInt("id"),
                        resultSet.getDate("appointment_date"),
                        resultSet.getString("status"),
                        resultSet.getString("clinic_name")
                );
                appointment.setPatientName(patientName);
                list.add(appointment);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


}
