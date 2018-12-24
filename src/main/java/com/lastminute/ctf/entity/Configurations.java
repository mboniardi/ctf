/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mboniardi
 */
@Entity
@Table(name = "CONFIGURATIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configurations.findAll", query = "SELECT c FROM Configurations c")
    , @NamedQuery(name = "Configurations.findById", query = "SELECT c FROM Configurations c WHERE c.id = :id")
    , @NamedQuery(name = "Configurations.findByContent", query = "SELECT c FROM Configurations c WHERE c.content = :content")})
public class Configurations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ID")
    private String id;
    @Size(max = 256)
    @Column(name = "CONTENT")
    private String content;

    public Configurations() {
    }

    public Configurations(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configurations)) {
            return false;
        }
        Configurations other = (Configurations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lastminute.flower.entity.Configurations[ id=" + id + " ]";
    }
    
}
