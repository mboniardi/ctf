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
@Table(name = "FLOWER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flower.findAll", query = "SELECT f FROM Flower f")
    , @NamedQuery(name = "Flower.findById", query = "SELECT f FROM Flower f WHERE f.id = :id")
    , @NamedQuery(name = "Flower.findByTitle", query = "SELECT f FROM Flower f WHERE f.title = :title")
    , @NamedQuery(name = "Flower.findByImgUrl", query = "SELECT f FROM Flower f WHERE f.imgUrl = :imgUrl")})
public class Flower implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ID")
    private String id;
    @Size(max = 256)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 256)
    @Column(name = "IMG_URL")
    private String imgUrl;

    public Flower() {
    }

    public Flower(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        if (!(object instanceof Flower)) {
            return false;
        }
        Flower other = (Flower) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lastminute.flower.entity.Flower[ id=" + id + " ]";
    }
    
}
