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
import model.IdiomaCandidato;

/**
 *
 * @author Joel Grullon
 */
public class IdiomaCandidatoService {
    
     private UnitOfWork _uow;
     private GenericRepository<IdiomaCandidato> _repo;

     
     public IdiomaCandidatoService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(IdiomaCandidato.class);
     }
     
     public List<IdiomaCandidato> getAll(){
         return _repo.getAll();
     }
     
     public IdiomaCandidato findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(IdiomaCandidato idiomaCandidato){
         try{
            boolean result= _repo.insert(idiomaCandidato);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(IdiomaCandidato idiomaCandidato){
         try{
            boolean result= _repo.update(idiomaCandidato);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(IdiomaCandidato idiomaCandidato){
         try{
            boolean result= _repo.delete(idiomaCandidato);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
