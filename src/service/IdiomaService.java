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
import model.Idioma;
import model.Idioma;

/**
 *
 * @author Joel Grullon
 */
public class IdiomaService {
    
     private UnitOfWork _uow;
     private GenericRepository<Idioma> _repo;

     
     public IdiomaService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(Idioma.class);
     }
     
     public List<Idioma> getAll(){
         return _repo.getAll();
     }
     
     public Idioma findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(Idioma idioma){
         try{
            boolean result= _repo.insert(idioma);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(Idioma idioma){
         try{
            boolean result= _repo.update(idioma);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(Idioma idioma){
         try{
            boolean result= _repo.delete(idioma);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
