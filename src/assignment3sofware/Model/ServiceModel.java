/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3sofware.Model;

/**
 *
 * @author andre
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andre
 */
public class ServiceModel implements IServiceModel {

   private static final String URL = "jdbc:mysql://localhost:3306/carservicedb"; //NEW ADJUSTED
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Hoanduong05";//YOUR PASSWORD
    private Connection connection = null; // manages connection
    private PreparedStatement searchCustomerAndVehicleByName = null; // select query
    private PreparedStatement searchCustomerAndVehicleByPhone = null; // select query

    // constructor
    public ServiceModel() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //search customer and vehicle by name sql statement
            searchCustomerAndVehicleByName = connection.prepareStatement("SELECT * FROM Customers As C left Join Vehicles As V on C.CustomerID=V.CustomerID where FIRSTNAME=? and LASTNAME=?");
            //search customer and vehicle by phone sql statement
            searchCustomerAndVehicleByPhone = connection.prepareStatement("SELECT * FROM Customers As C left Join Vehicles As V on C.CustomerID=V.CustomerID where Phone =?");

        } // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
    } // end PersonQueries constructor

   

    
    public List<Vehicle> searchCustomerAndVehicleByName(String first, String last) {
        ResultSet resultSet = null;
        List<Vehicle> result = new ArrayList<>();

        try {
            searchCustomerAndVehicleByName.setString(1, first);
            searchCustomerAndVehicleByName.setString(2, last);

            resultSet = searchCustomerAndVehicleByName.executeQuery();//execute query

            while (resultSet.next()) {
                 int customerID = resultSet.getInt("CUSTOMERID");//get customer id
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String phone = resultSet.getString("PHONE");
                String address = resultSet.getString("ADDRESS");

                Customer c = new Customer(customerID, firstName, lastName, phone, address);

                String vehicleNumber = resultSet.getString("VehicleNumber");
                String vehicleModel = resultSet.getString("VehicleModel");
                String vehicleBrand = resultSet.getString("VehicleBrand");
                int vehicleYear = resultSet.getInt("VehicleYear");
                int vehicleKm = resultSet.getInt("VehicleKilometers");

                Vehicle v = new Vehicle(vehicleNumber, vehicleModel, vehicleBrand, vehicleYear, vehicleKm, c);
                result.add(v);
            } // end while

        } catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch
        return result;
    }

    public List<Vehicle> searchCustomerAndVehicleByPhone(String p) {
        ResultSet resultSet = null;
        List<Vehicle> result = new ArrayList<>();

        try {
            searchCustomerAndVehicleByPhone.setString(1, p);
            resultSet = searchCustomerAndVehicleByPhone.executeQuery();//execute query

            while (resultSet.next()) {
                int customerID = resultSet.getInt("CUSTOMERID");//get customer id
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String phone = resultSet.getString("PHONE");
                String address = resultSet.getString("ADDRESS");

                Customer c = new Customer(customerID, firstName, lastName, phone, address);

                String vehicleNumber = resultSet.getString("VehicleNumber");
                String vehicleModel = resultSet.getString("VehicleModel");
                String vehicleBrand = resultSet.getString("VehicleBrand");
                int vehicleYear = resultSet.getInt("VehicleYear");
                int vehicleKm = resultSet.getInt("VehicleKilometers");

                Vehicle v = new Vehicle(vehicleNumber, vehicleModel, vehicleBrand, vehicleYear, vehicleKm, c);
                result.add(v);

            } // end while

        } catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch

        return result;
    }

 public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close

    
    
} // end class PersonQueries
