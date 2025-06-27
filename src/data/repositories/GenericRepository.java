/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repositories;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import javax.persistence.*;

public class GenericRepository<Entidad> {
    private final Class<Entidad> type;
    private final Connection connection;

    public GenericRepository(Class<Entidad> type, Connection connection) {
        this.type = type;
        this.connection = connection;
    }

    private String getTableName() {
        Table table = type.getAnnotation(Table.class);
        return (table != null) ? table.name() : type.getSimpleName().toLowerCase();
    }

    private Field getIdField() {
        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                return field;
            }
        }
        throw new RuntimeException("No @Id field found in " + type.getName());
    }

    private String getColumnName(Field field) {
        Column column = field.getAnnotation(Column.class);
        return (column != null) ? column.name() : field.getName();
    }

    private List<Field> getPersistableFields(boolean includeId) {
        List<Field> result = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            if (!includeId && field.isAnnotationPresent(Id.class)) continue;

            Column column = field.getAnnotation(Column.class);
            if (column != null || field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                result.add(field);
            }
        }
        return result;
    }

    public List<Entidad> getAll() {
        String sql = "SELECT * FROM " + getTableName();
        List<Entidad> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Entidad findById(Object id) {
        Field idField = getIdField();
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getColumnName(idField) + " = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insert(Entidad entity) {
        List<Field> fields = getPersistableFields(false); // Exclude ID (assuming auto-increment)
        String table = getTableName();

        String columns = String.join(", ", fields.stream().map(this::getColumnName).toArray(String[]::new));
        String placeholders = String.join(", ", Collections.nCopies(fields.size(), "?"));
        String sql = "INSERT INTO " + table + " (" + columns + ") VALUES (" + placeholders + ")";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < fields.size(); i++) {
                stmt.setObject(i + 1, fields.get(i).get(entity));
            }
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Entidad entity) {
        Field idField = getIdField();
        Object idValue = null;

        try {
            idValue = idField.get(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

        List<Field> fields = getPersistableFields(false);
        String setClause = String.join(", ", fields.stream()
                .map(f -> getColumnName(f) + " = ?")
                .toArray(String[]::new));

        String sql = "UPDATE " + getTableName() + " SET " + setClause +
                     " WHERE " + getColumnName(idField) + " = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < fields.size(); i++) {
                stmt.setObject(i + 1, fields.get(i).get(entity));
            }
            stmt.setObject(fields.size() + 1, idValue);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(Entidad entity) {
        Field idField = getIdField();
        Object idValue;

        try {
            idValue = idField.get(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "DELETE FROM " + getTableName() + " WHERE " + getColumnName(idField) + " = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, idValue);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Entidad mapResultSet(ResultSet rs) throws Exception {
        Entidad instance = type.getDeclaredConstructor().newInstance();

        for (Field field : type.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            Column column = field.getAnnotation(Column.class);
            if (column == null && !field.isAnnotationPresent(Id.class)) continue;

            String columnName = getColumnName(field);
            Object value = rs.getObject(columnName);

            field.setAccessible(true);
            field.set(instance, value);
        }

        return instance;
    }
}
