/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.pedido.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leo
 */
@Entity
@Table(name = "PEDGRADTAM")
@NamedQueries({
    @NamedQuery(name = "Pedgradtam.findAll", query = "SELECT p FROM Pedgradtam p")})
public class Pedgradtam implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedgradtamPK pedgradtamPK;
    @Basic(optional = false)
    @Column(name = "ColPos")
    private int colPos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedgradtam")
    private List<Pedgradqtde> pedgradqtdeList;
    @JoinColumns({
        @JoinColumn(name = "PedCod", referencedColumnName = "PedCod", insertable = false, updatable = false),
        @JoinColumn(name = "ProRef", referencedColumnName = "ProRef", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Peddet peddet;

    public Pedgradtam() {
        
    }

    public Pedgradtam(PedgradtamPK pedgradtamPK) {
        this.pedgradtamPK = pedgradtamPK;
    }

    public Pedgradtam(PedgradtamPK pedgradtamPK, int colPos) {
        this.pedgradtamPK = pedgradtamPK;
        this.colPos = colPos;
    }

    public Pedgradtam(int pedCod, String proRef, int tamCod) {
        this.pedgradtamPK = new PedgradtamPK(pedCod, proRef, tamCod);
    }

    public PedgradtamPK getPedgradtamPK() {
        return pedgradtamPK;
    }

    public void setPedgradtamPK(PedgradtamPK pedgradtamPK) {
        this.pedgradtamPK = pedgradtamPK;
    }

    public int getColPos() {
        return colPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    public List<Pedgradqtde> getPedgradqtdeList() {
        return pedgradqtdeList;
    }

    public void setPedgradqtdeList(List<Pedgradqtde> pedgradqtdeList) {
        this.pedgradqtdeList = pedgradqtdeList;
    }

    public Peddet getPeddet() {
        return peddet;
    }

    public void setPeddet(Peddet peddet) {
        this.peddet = peddet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedgradtamPK != null ? pedgradtamPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedgradtam)) {
            return false;
        }
        Pedgradtam other = (Pedgradtam) object;
        if ((this.pedgradtamPK == null && other.pedgradtamPK != null) || (this.pedgradtamPK != null && !this.pedgradtamPK.equals(other.pedgradtamPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.Pedgradtam[ pedgradtamPK=" + pedgradtamPK + " ]";
    }
    
}
