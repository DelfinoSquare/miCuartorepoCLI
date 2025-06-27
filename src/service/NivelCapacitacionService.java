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
import model.NivelCapacitacion;

/**
 *
 * @author Joel Grullon
 */
public class NivelCapacitacionService {
    
     private UnitOfWork _uow;
     private GenericRepository<NivelCapacitacion> _repo;

     
     public NivelCapacitacionService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(NivelCapacitacion.class);
     }
     
     public List<NivelCapacitacion> getAll(){
         return _repo.getAll();
     }
     
     public NivelCapacitacion findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(NivelCapacitacion nivelCapacitacion){
         try{
            boolean result= _repo.insert(nivelCapacitacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(NivelCapacitacion nivelCapacitacion){
         try{
            boolean result= _repo.update(nivelCapacitacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(NivelCapacitacion nivelCapacitacion){
         try{
            boolean result= _repo.delete(nivelCapacitacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
