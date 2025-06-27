/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Joel Grullon
 */
import data.repositories.DepartamentoRepository;
import data.repositories.GenericRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.Idioma;

public class UnitOfWork implements AutoCloseable {
    private final Connection connection;

    private DepartamentoRepository departamentoRepository;

     public UnitOfWork() throws SQLException {
        this.connection = MySQL.getConnection();
        this.connection.setAutoCommit(false);
    }
     
    public DepartamentoRepository departamentos() {
        if(departamentoRepository==null){
            departamentoRepository=new DepartamentoRepository(connection);
        }
        return departamentoRepository;
    }
     
    public void save() throws SQLException {
        connection.commit();
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}