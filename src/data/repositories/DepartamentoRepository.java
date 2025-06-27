/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repositories;

/**
 *
 * @author Joel Grullon
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Departamento;

public class DepartamentoRepository{

    private final Connection connection;

    public DepartamentoRepository(Connection connection) {
        this.connection = connection;
    }
    
    public Departamento findById(int id) {
        String sql = "SELECT * FROM departamentos WHERE id_departamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el departamento", e);
        }
        return null;
    }
    
    public List<Departamento> findAll() {
        List<Departamento> list = new ArrayList<>();
        String sql = "SELECT * FROM departamentos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar departamentos", e);
        }
        return list;
    }
    
    public void save(Departamento d) {
        String sql = "INSERT INTO departamentos(nombre_departamento, eliminado) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, d.getNombreDepartamento());
            stmt.setObject(2, d.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    d.setIdDepartamento(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar departamento", e);
        }
    }
    
    public void update(Departamento d) {
        String sql = "UPDATE departamentos SET nombre_departamento = ?, eliminado = ? WHERE id_departamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, d.getNombreDepartamento());
            stmt.setObject(2, d.getEliminado());
            stmt.setInt(3, d.getIdDepartamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar departamento", e);
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM departamentos WHERE id_departamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar departamento", e);
        }
    }

    private Departamento mapRow(ResultSet rs) throws SQLException {
        Departamento d = new Departamento();
        d.setIdDepartamento(rs.getInt("id_departamento"));
        d.setNombreDepartamento(rs.getString("nombre_departamento"));
        d.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return d;
    }
}
