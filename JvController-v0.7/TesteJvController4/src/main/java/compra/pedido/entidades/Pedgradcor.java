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
@Table(name = "PEDGRADCOR")
@NamedQueries({
    @NamedQuery(name = "Pedgradcor.findAll", query = "SELECT p FROM Pedgradcor p")})
public class Pedgradcor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedgradcorPK pedgradcorPK;
    @Basic(optional = false)
    @Column(name = "LinPos")
    private int linPos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedgradcor")
    private List<Pedgradqtde> pedgradqtdeList;
    @JoinColumns({
        @JoinColumn(name = "PedCod", referencedColumnName = "PedCod", insertable = false, updatable = false),
        @JoinColumn(name = "ProRef", referencedColumnName = "ProRef", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Peddet peddet;

    public Pedgradcor() {
    }

    public Pedgradcor(PedgradcorPK pedgradcorPK) {
        this.pedgradcorPK = pedgradcorPK;
    }

    public Pedgradcor(PedgradcorPK pedgradcorPK, int linPos) {
        this.pedgradcorPK = pedgradcorPK;
        this.linPos = linPos;
    }

    public Pedgradcor(int pedCod, String proRef, int corCod) {
        this.pedgradcorPK = new PedgradcorPK(pedCod, proRef, corCod);
    }

    public PedgradcorPK getPedgradcorPK() {
        return pedgradcorPK;
    }

    public void setPedgradcorPK(PedgradcorPK pedgradcorPK) {
        this.pedgradcorPK = pedgradcorPK;
    }

    public int getLinPos() {
        return linPos;
    }

    public void setLinPos(int linPos) {
        this.linPos = linPos;
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
        hash += (pedgradcorPK != null ? pedgradcorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedgradcor)) {
            return false;
        }
        Pedgradcor other = (Pedgradcor) object;
        if ((this.pedgradcorPK == null && other.pedgradcorPK != null) || (this.pedgradcorPK != null && !this.pedgradcorPK.equals(other.pedgradcorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.Pedgradcor[ pedgradcorPK=" + pedgradcorPK + " ]";
    }

    //@Override
    //protected void finalize() throws Throwable {
    //    System.out.println("Objeto " + this + " vai ser destruido ...");
    //    super.finalize();
    //}
    
}
