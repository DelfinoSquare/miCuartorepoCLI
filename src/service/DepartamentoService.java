/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import data.repositories.DepartamentoRepository;
import java.sql.SQLException;
import java.util.List;
import model.Departamento;

/**
 *
 * @author Joel Grullon
 */
public class DepartamentoService {
    
    private UnitOfWork _uow;
    private DepartamentoRepository _repo;

    public DepartamentoService() throws SQLException {
        _uow = new UnitOfWork();
        _repo = _uow.departamentos();
    }

    public List<Departamento> getAll() {
        return _repo.findAll();
    }

    public Departamento findById(int id) {
        return _repo.findById(id);
    }

    public boolean insert(Departamento departamento) {
        try {
            _repo.save(departamento);
            _uow.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Departamento departamento) {
        try {
            _repo.update(departamento);
            _uow.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Departamento departamento) {
        try {
            _repo.delete(departamento.getIdDepartamento());
            _uow.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ MÉTODO NUEVO para inicializar los 5 departamentos
    public void inicializarDepartamentos() {
        String[] nombres = {"Finanzas", "Marketing", "Recursos Humanos", "Ventas", "Tecnología"};

        for (String nombre : nombres) {
            if (!existeDepartamento(nombre)) {
                Departamento d = new Departamento();
                d.setNombre(nombre);
                insert(d);
            }
        }
    }

    private boolean existeDepartamento(String nombre) {
        List<Departamento> lista = getAll();
        return lista.stream().anyMatch(dep -> dep.getNombre().equalsIgnoreCase(nombre));
    }
}

