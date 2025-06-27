/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import data.repositories.GenericRepository;
import java.sql.SQLException;
import java.util.List;
import model.BaseEntity;
import model.Capacitacion;
import model.Idioma;

/**
 *
 * @author Joel Grullon
 */
public class CapacitacionService {
    
     private UnitOfWork _uow;
     private GenericRepository<Capacitacion> _repo;

     
     public CapacitacionService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(Capacitacion.class);
     }
     
     public List<Capacitacion> getAll(){
         return _repo.getAll();
     }
     
     public Capacitacion findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(Capacitacion capacitacion){
         try{
            boolean result= _repo.insert(capacitacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(Capacitacion capacitacion){
         try{
            boolean result= _repo.update(capacitacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(Capacitacion capacitacion){
         try{
            boolean result= _repo.delete(capacitacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
