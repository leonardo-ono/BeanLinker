/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.pedido.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author leonardo
 */
@Entity
@Table(name = "SITUACAOPRODUTO")
@NamedQueries({
    @NamedQuery(name = "Situacaoproduto.findAll", query = "SELECT s FROM Situacaoproduto s")})
public class Situacaoproduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SIPCOD")
    private Integer sipcod;
    @Column(name = "SIPDESC")
    private String sipdesc;
    @Basic(optional = false)
    @Column(name = "SITVALIDO")
    private int sitvalido;

    public Situacaoproduto() {
    }

    public Situacaoproduto(Integer sipcod) {
        this.sipcod = sipcod;
    }

    public Situacaoproduto(Integer sipcod, int sitvalido) {
        this.sipcod = sipcod;
        this.sitvalido = sitvalido;
    }

    public Integer getSipcod() {
        return sipcod;
    }

    public void setSipcod(Integer sipcod) {
        this.sipcod = sipcod;
    }

    public String getSipdesc() {
        return sipdesc;
    }

    public void setSipdesc(String sipdesc) {
        this.sipdesc = sipdesc;
    }

    public int getSitvalido() {
        return sitvalido;
    }

    public void setSitvalido(int sitvalido) {
        this.sitvalido = sitvalido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipcod != null ? sipcod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Situacaoproduto)) {
            return false;
        }
        Situacaoproduto other = (Situacaoproduto) object;
        if ((this.sipcod == null && other.sipcod != null) || (this.sipcod != null && !this.sipcod.equals(other.sipcod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.Situacaoproduto[ sipcod=" + sipcod + " ]";
    }
    
}
