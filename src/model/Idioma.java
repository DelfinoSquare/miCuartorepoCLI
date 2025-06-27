/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

public class Idioma {
    private int idIdioma;
    private String nombreIdioma;
    private Boolean eliminado;

    private List<IdiomaCandidato> idiomaCandidatos;

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List<IdiomaCandidato> getIdiomaCandidatos() {
        return idiomaCandidatos;
    }

    public void setIdiomaCandidatos(List<IdiomaCandidato> idiomaCandidatos) {
        this.idiomaCandidatos = idiomaCandidatos;
    }
}
