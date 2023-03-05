package com.example.oraclecrudwithfaces;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.jdbc.OracleDriver;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

@Data
@AllArgsConstructor
@Named
@SessionScoped
public class DAO implements Serializable {
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    private int returned;
    private Driver driver=null;
    private Connection connection=null;
    private String query="";
    public DAO(){
        try {
            driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            System.out.println("Driver is connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int delete(int id){
        try {
            query="delete from hai where id=?";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            returned=preparedStatement.executeUpdate();
            if(returned>0){
                //System.out.println("Deletion done");
                return returned;
            }
//            else{
//                System.out.println("Deletion failed");
//            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int update(Hai hai){
        try {
            query="update hai set name=?,price=? where id=?";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,hai.getName());
            preparedStatement.setInt(2,hai.getPrice());
            preparedStatement.setInt(3,hai.getId());
            returned=preparedStatement.executeUpdate();
            if(returned>0)
                return returned;
            else
                return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Hai readOne(int id){
        try {
            query="select * from hai where id=?";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            //ArrayList<Object> row=new ArrayList<>();
            Hai hai=new Hai();
            if(resultSet.next()){
                //System.out.println(resultSet.getInt("id")+" "+resultSet.getString("name")+" "+resultSet.getInt("price"));
                hai.setId(resultSet.getInt("id"));
                hai.setName(resultSet.getString("name"));
                hai.setPrice(resultSet.getInt("price"));
            }
//            else{
//                System.out.println("Read failed");
//            }
            return hai;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int insertNew(Hai hai){
        try {
            query="insert into hai(id,name,price) values(hai_seq.nextval,?,?)";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,hai.getName());
            preparedStatement.setInt(2,hai.getPrice());
            returned=preparedStatement.executeUpdate();
            if(returned>0)
                return returned;
            else
                return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Collection<Hai> all(){
        try {
            query="select * from hai";
            preparedStatement= connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            Collection<Hai> all=new ArrayList<>();
            Hai hai=null;
            while(resultSet.next()){
                hai=new Hai();
                hai.setId(resultSet.getInt("id"));
                hai.setName(resultSet.getString("name"));
                hai.setPrice(resultSet.getInt("price"));
                //System.out.println(resultSet.getInt("id")+" "+resultSet.getString("name")+" "+resultSet.getInt("price"));
                all.add(hai);
            }
            return all;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
