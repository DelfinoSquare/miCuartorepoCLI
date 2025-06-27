/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Joel Grullon
 */
@Entity
@Table(name = "idiomas")
@NamedQueries({
    @NamedQuery(name = "Idiomas.findAll", query = "SELECT i FROM NivelIdioma i"),
    @NamedQuery(name = "Idiomas.findByIdIdioma", query = "SELECT i FROM NivelIdioma i WHERE i.idNivel = :idNivel"),
    @NamedQuery(name = "Idiomas.findByNombreIdioma", query = "SELECT i FROM NivelIdioma i WHERE i.nombreNivel= :nombreNivel")})
public class NivelIdioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel")
    private Integer idNivel;
    
    @Basic(optional = false)
    @Column(name = "nombre_nivel")
    private String nombreNivel;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelId")
    private Collection<IdiomaCandidato> idiomasCandidatosCollection;

    public NivelIdioma() {
    }

    public NivelIdioma(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public NivelIdioma(Integer idIdioma, String nombreIdioma) {
        this.idNivel = idIdioma;
        this.nombreNivel = nombreIdioma;
    }

    public Integer getIdIdioma() {
        return idNivel;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idNivel = idIdioma;
    }

    public String getNombreIdioma() {
        return nombreNivel;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreNivel = nombreIdioma;
    }

    public Collection<IdiomaCandidato> getIdiomasCandidatosCollection() {
        return idiomasCandidatosCollection;
    }

    public void setIdiomasCandidatosCollection(Collection<IdiomaCandidato> idiomasCandidatosCollection) {
        this.idiomasCandidatosCollection = idiomasCandidatosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivel != null ? idNivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelIdioma)) {
            return false;
        }
        NivelIdioma other = (NivelIdioma) object;
        if ((this.idNivel == null && other.idNivel != null) || (this.idNivel != null && !this.idNivel.equals(other.idNivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Idiomas[ idIdioma=" + idNivel + " ]";
    }
    
}
