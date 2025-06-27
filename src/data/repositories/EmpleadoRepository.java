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
import java.sql.*;
import java.util.*;
import model.Empleado;

public class EmpleadoRepository {

    private final Connection connection;

    public EmpleadoRepository(Connection connection) {
        this.connection = connection;
    }

    public Empleado findById(int id) {
        String sql = "SELECT * FROM empleados WHERE id_empleado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando empleado", e);
        }
    }

    public List<Empleado> findAll() {
        List<Empleado> list = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando empleados", e);
        }
        return list;
    }

    public void save(Empleado e) {
        String sql = "INSERT INTO empleados(cedula, nombre_empleado, apellido_empleado, fecha_ingreso, departamento_id, puesto, eliminado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, e.getCedula());
            stmt.setString(2, e.getNombreEmpleado());
            stmt.setString(3, e.getApellidoEmpleado());
            if (e.getFechaIngreso() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(e.getFechaIngreso()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setInt(5, e.getDepartamentoId());
            stmt.setString(6, e.getPuesto());
            stmt.setObject(7, e.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    e.setIdEmpleado(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error insertando empleado", ex);
        }
    }

    public void update(Empleado e) {
        String sql = "UPDATE empleados SET cedula = ?, nombre_empleado = ?, apellido_empleado = ?, fecha_ingreso = ?, departamento_id = ?, puesto = ?, eliminado = ? " +
                     "WHERE id_empleado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getCedula());
            stmt.setString(2, e.getNombreEmpleado());
            stmt.setString(3, e.getApellidoEmpleado());
            if (e.getFechaIngreso() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(e.getFechaIngreso()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setInt(5, e.getDepartamentoId());
            stmt.setString(6, e.getPuesto());
            stmt.setObject(7, e.getEliminado());
            stmt.setInt(8, e.getIdEmpleado());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error actualizando empleado", ex);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM empleados WHERE id_empleado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error eliminando empleado", ex);
        }
    }

    private Empleado mapRow(ResultSet rs) throws SQLException {
        Empleado e = new Empleado();
        e.setIdEmpleado(rs.getInt("id_empleado"));
        e.setCedula(rs.getString("cedula"));
        e.setNombreEmpleado(rs.getString("nombre_empleado"));
        e.setApellidoEmpleado(rs.getString("apellido_empleado"));
        java.sql.Date fecha = rs.getDate("fecha_ingreso");
        if (fecha != null) {
            e.setFechaIngreso(fecha.toLocalDate());
        }
        e.setDepartamentoId(rs.getInt("departamento_id"));
        e.setPuesto(rs.getString("puesto"));
        e.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return e;
    }
}
