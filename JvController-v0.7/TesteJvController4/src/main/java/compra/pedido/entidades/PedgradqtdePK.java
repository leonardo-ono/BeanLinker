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
public class PedgradqtdePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PedCod")
    private int pedCod;
    @Basic(optional = false)
    @Column(name = "ProRef")
    private String proRef;
    @Basic(optional = false)
    @Column(name = "TamCod")
    private int tamCod;
    @Basic(optional = false)
    @Column(name = "CorCod")
    private int corCod;

    public PedgradqtdePK() {
    }

    public PedgradqtdePK(int pedCod, String proRef, int tamCod, int corCod) {
        this.pedCod = pedCod;
        this.proRef = proRef;
        this.tamCod = tamCod;
        this.corCod = corCod;
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

    public int getTamCod() {
        return tamCod;
    }

    public void setTamCod(int tamCod) {
        this.tamCod = tamCod;
    }

    public int getCorCod() {
        return corCod;
    }

    public void setCorCod(int corCod) {
        this.corCod = corCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pedCod;
        hash += (proRef != null ? proRef.hashCode() : 0);
        hash += (int) tamCod;
        hash += (int) corCod;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedgradqtdePK)) {
            return false;
        }
        PedgradqtdePK other = (PedgradqtdePK) object;
        if (this.pedCod != other.pedCod) {
            return false;
        }
        if ((this.proRef == null && other.proRef != null) || (this.proRef != null && !this.proRef.equals(other.proRef))) {
            return false;
        }
        if (this.tamCod != other.tamCod) {
            return false;
        }
        if (this.corCod != other.corCod) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.PedgradqtdePK[ pedCod=" + pedCod + ", proRef=" + proRef + ", tamCod=" + tamCod + ", corCod=" + corCod + " ]";
    }
    
}
