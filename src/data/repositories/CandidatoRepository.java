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
import model.Candidato;
import model.Departamento;
public class CandidatoRepository {

    private final Connection connection;

    public CandidatoRepository(Connection connection) {
        this.connection = connection;
    }

    public Candidato findById(int id) {
        String sql = "SELECT * FROM candidatos WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando candidato", e);
        }
    }

    public List<Candidato> findAll() {
        List<Candidato> list = new ArrayList<>();
        String sql = "SELECT * FROM candidatos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando candidatos", e);
        }
        return list;
    }

    public void save(Candidato c) {
        String sql = "INSERT INTO candidatos(cedula, nombre, apellido, puesto_id, salario, eliminado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, c.getCedula());
            stmt.setString(2, c.getNombre());
            stmt.setString(3, c.getApellido());
            stmt.setInt(4, c.getPuestoId());
            stmt.setBigDecimal(5, c.getSalario());
            stmt.setObject(6, c.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setIdCandidato(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando candidato", e);
        }
    }

    public void update(Candidato c) {
        String sql = "UPDATE candidatos SET cedula = ?, nombre = ?, apellido = ?, puesto_id = ?, salario = ?, eliminado = ? " +
                     "WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getCedula());
            stmt.setString(2, c.getNombre());
            stmt.setString(3, c.getApellido());
            stmt.setInt(4, c.getPuestoId());
            stmt.setBigDecimal(5, c.getSalario());
            stmt.setObject(6, c.getEliminado());
            stmt.setInt(7, c.getIdCandidato());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando candidato", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM candidatos WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando candidato", e);
        }
    }

    private Candidato mapRow(ResultSet rs) throws SQLException {
        Candidato c = new Candidato();
        c.setIdCandidato(rs.getInt("id_candidato"));
        c.setCedula(rs.getString("cedula"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setPuestoId(rs.getInt("puesto_id"));
        c.setSalario(rs.getBigDecimal("salario"));
        c.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return c;
    }
}
