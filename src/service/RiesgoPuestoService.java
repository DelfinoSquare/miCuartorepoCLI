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
import model.RiesgoPuesto;

/**
 *
 * @author Joel Grullon
 */
public class RiesgoPuestoService {
    
     private UnitOfWork _uow;
     private GenericRepository<RiesgoPuesto> _repo;

     
     public RiesgoPuestoService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(RiesgoPuesto.class);
     }
     
     public List<RiesgoPuesto> getAll(){
         return _repo.getAll();
     }
     
     public RiesgoPuesto findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(RiesgoPuesto riesgoPuesto){
         try{
            boolean result= _repo.insert(riesgoPuesto);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(RiesgoPuesto riesgoPuesto){
         try{
            boolean result= _repo.update(riesgoPuesto);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(RiesgoPuesto riesgoPuesto){
         try{
            boolean result= _repo.delete(riesgoPuesto);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
