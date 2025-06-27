/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class IdiomaCandidato {
    private int idIdiomaCandidato;
    private int candidatoId;
    private int idiomaId;
    private int nivelId;

    private Candidato candidato;
    private Idioma idioma;
    private NivelIdioma nivelIdioma;

    public int getIdIdiomaCandidato() {
        return idIdiomaCandidato;
    }

    public void setIdIdiomaCandidato(int idIdiomaCandidato) {
        this.idIdiomaCandidato = idIdiomaCandidato;
    }

    public int getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(int candidatoId) {
        this.candidatoId = candidatoId;
    }

    public int getIdiomaId() {
        return idiomaId;
    }

    public void setIdiomaId(int idiomaId) {
        this.idiomaId = idiomaId;
    }

    public int getNivelId() {
        return nivelId;
    }

    public void setNivelId(int nivelId) {
        this.nivelId = nivelId;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public NivelIdioma getNivelIdioma() {
        return nivelIdioma;
    }

    public void setNivelIdioma(NivelIdioma nivelIdioma) {
        this.nivelIdioma = nivelIdioma;
    }
}
