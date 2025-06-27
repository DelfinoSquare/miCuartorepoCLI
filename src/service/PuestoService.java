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
import model.Puesto;

/**
 *
 * @author Joel Grullon
 */
public class PuestoService {
    
     private UnitOfWork _uow;
     private GenericRepository<Puesto> _repo;

     
     public PuestoService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(Puesto.class);
     }
     
     public List<Puesto> getAll(){
         return _repo.getAll();
     }
     
     public Puesto findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(Puesto puesto){
         try{
            boolean result= _repo.insert(puesto);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(Puesto puesto){
         try{
            boolean result= _repo.update(puesto);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(Puesto puesto){
         try{
            boolean result= _repo.delete(puesto);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
