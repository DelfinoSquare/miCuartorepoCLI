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
import model.Candidato;
import model.Idioma;

/**
 *
 * @author Joel Grullon
 */
public class CandidatoService {
    
     private UnitOfWork _uow;
     private GenericRepository<Candidato> _repo;

     
     public CandidatoService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(Candidato.class);
     }
     
     public List<Candidato> getAll(){
         return _repo.getAll();
     }
     
     public Candidato findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(Candidato candidato){
         try{
            boolean result= _repo.insert(candidato);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(Candidato candidato){
         try{
            boolean result= _repo.update(candidato);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(Candidato candidato){
         try{
            boolean result= _repo.delete(candidato);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
