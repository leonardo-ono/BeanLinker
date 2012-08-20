/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.pedido.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author leo
 */
@Entity
@Table(name = "PEDGRADQTDE")
@NamedQueries({
    @NamedQuery(name = "Pedgradqtde.findAll", query = "SELECT p FROM Pedgradqtde p")})
public class Pedgradqtde implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedgradqtdePK pedgradqtdePK;
    @Basic(optional = false)
    @Column(name = "ProQtde")
    private int proQtde;
    @JoinColumns({
        @JoinColumn(name = "PedCod", referencedColumnName = "PedCod", insertable = false, updatable = false),
        @JoinColumn(name = "ProRef", referencedColumnName = "ProRef", insertable = false, updatable = false),
        @JoinColumn(name = "TamCod", referencedColumnName = "TamCod", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Pedgradtam pedgradtam;
    @JoinColumns({
        @JoinColumn(name = "PedCod", referencedColumnName = "PedCod", insertable = false, updatable = false),
        @JoinColumn(name = "ProRef", referencedColumnName = "ProRef", insertable = false, updatable = false),
        @JoinColumn(name = "CorCod", referencedColumnName = "CorCod", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Pedgradcor pedgradcor;
    @JoinColumns({
        @JoinColumn(name = "PedCod", referencedColumnName = "PedCod", insertable = false, updatable = false),
        @JoinColumn(name = "ProRef", referencedColumnName = "ProRef", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Peddet peddet;

    public Pedgradqtde() {
    }

    public Pedgradqtde(PedgradqtdePK pedgradqtdePK) {
        this.pedgradqtdePK = pedgradqtdePK;
    }

    public Pedgradqtde(PedgradqtdePK pedgradqtdePK, int proQtde) {
        this.pedgradqtdePK = pedgradqtdePK;
        this.proQtde = proQtde;
    }

    public Pedgradqtde(int pedCod, String proRef, int tamCod, int corCod) {
        this.pedgradqtdePK = new PedgradqtdePK(pedCod, proRef, tamCod, corCod);
    }

    public PedgradqtdePK getPedgradqtdePK() {
        return pedgradqtdePK;
    }

    public void setPedgradqtdePK(PedgradqtdePK pedgradqtdePK) {
        this.pedgradqtdePK = pedgradqtdePK;
    }

    public int getProQtde() {
        return proQtde;
    }

    public void setProQtde(int proQtde) {
        this.proQtde = proQtde;
    }

    public Pedgradtam getPedgradtam() {
        return pedgradtam;
    }

    public void setPedgradtam(Pedgradtam pedgradtam) {
        this.pedgradtam = pedgradtam;
    }

    public Pedgradcor getPedgradcor() {
        return pedgradcor;
    }

    public void setPedgradcor(Pedgradcor pedgradcor) {
        this.pedgradcor = pedgradcor;
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
        hash += (pedgradqtdePK != null ? pedgradqtdePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedgradqtde)) {
            return false;
        }
        Pedgradqtde other = (Pedgradqtde) object;
        if ((this.pedgradqtdePK == null && other.pedgradqtdePK != null) || (this.pedgradqtdePK != null && !this.pedgradqtdePK.equals(other.pedgradqtdePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.Pedgradqtde[ pedgradqtdePK=" + pedgradqtdePK + " ]";
    }
    
}
