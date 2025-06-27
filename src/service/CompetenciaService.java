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
import model.Competencia;
import model.Idioma;

/**
 *
 * @author Joel Grullon
 */
public class CompetenciaService {
    
     private UnitOfWork _uow;
     private GenericRepository<Competencia> _repo;

     
     public CompetenciaService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(Competencia.class);
     }
     
     public List<Competencia> getAll(){
         return _repo.getAll();
     }
     
     public Competencia findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(Competencia Competencia){
         try{
            boolean result= _repo.insert(Competencia);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(Competencia Competencia){
         try{
            boolean result= _repo.update(Competencia);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(Competencia Competencia){
         try{
            boolean result= _repo.delete(Competencia);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
