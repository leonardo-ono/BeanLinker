/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.pedido.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author leo
 */
@Embeddable
public class PeddetPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PedCod")
    private int pedCod;
    @Basic(optional = false)
    @Column(name = "ProRef")
    private String proRef;

    public PeddetPK() {
    }

    public PeddetPK(int pedCod, String proRef) {
        this.pedCod = pedCod;
        this.proRef = proRef;
    }

    public int getPedCod() {
        return pedCod;
    }

    public void setPedCod(int pedCod) {
        this.pedCod = pedCod;
    }

    public String getProRef() {
        return proRef;
    }

    public void setProRef(String proRef) {
        this.proRef = proRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pedCod;
        hash += (proRef != null ? proRef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeddetPK)) {
            return false;
        }
        PeddetPK other = (PeddetPK) object;
        if (this.pedCod != other.pedCod) {
            return false;
        }
        if ((this.proRef == null && other.proRef != null) || (this.proRef != null && !this.proRef.equals(other.proRef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.PeddetPK[ pedCod=" + pedCod + ", proRef=" + proRef + " ]";
    }
    
}
