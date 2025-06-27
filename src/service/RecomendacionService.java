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
import model.Recomendacion;

/**
 *
 * @author Joel Grullon
 */
public class RecomendacionService {
    
     private UnitOfWork _uow;
     private GenericRepository<Recomendacion> _repo;

     
     public RecomendacionService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(Recomendacion.class);
     }
     
     public List<Recomendacion> getAll(){
         return _repo.getAll();
     }
     
     public Recomendacion findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(Recomendacion recomendacion){
         try{
            boolean result= _repo.insert(recomendacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(Recomendacion recomendacion){
         try{
            boolean result= _repo.update(recomendacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(Recomendacion recomendacion){
         try{
            boolean result= _repo.delete(recomendacion);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
