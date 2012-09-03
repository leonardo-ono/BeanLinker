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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author leo
 */
@Entity
@Table(name = "PEDDET")
@NamedQueries({
    @NamedQuery(name = "Peddet.findAll", query = "SELECT p FROM Peddet p")})
public class Peddet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeddetPK peddetPK;
    @Basic(optional = false)
    @Column(name = "ProCod")
    private int proCod;
    @Basic(optional = false)
    @Column(name = "ProChkBar")
    private short proChkBar;
    @Basic(optional = false)
    @Column(name = "ProQtde")
    private int proQtde;
    @Basic(optional = false)
    @Column(name = "ProSldo")
    private int proSldo;
    @Column(name = "ProFator")
    private Integer proFator;
    @Basic(optional = false)
    @Column(name = "PedDetSitCod")
    private int pedDetSitCod;
    @Column(name = "ProObs")
    private String proObs;
    @Column(name = "ProInd")
    private Integer proInd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peddet", fetch= FetchType.EAGER)
    private List<Pedgradqtde> pedgradqtdeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peddet", fetch= FetchType.EAGER)
    private List<Pedgradcor> pedgradcorList;
    @JoinColumn(name = "PedCod", referencedColumnName = "PedCod", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedcab pedcab;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peddet", fetch= FetchType.EAGER)
    private List<Pedgradtam> pedgradtamList;

    @Transient
    private Produtos produto;

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }
    
    public Peddet() {
    }

    public Peddet(PeddetPK peddetPK) {
        this.peddetPK = peddetPK;
    }

    public Peddet(PeddetPK peddetPK, int proCod, short proChkBar, int proQtde, int proSldo, int pedDetSitCod) {
        this.peddetPK = peddetPK;
        this.proCod = proCod;
        this.proChkBar = proChkBar;
        this.proQtde = proQtde;
        this.proSldo = proSldo;
        this.pedDetSitCod = pedDetSitCod;
    }

    public Peddet(int pedCod, String proRef) {
        this.peddetPK = new PeddetPK(pedCod, proRef);
    }

    public PeddetPK getPeddetPK() {
        return peddetPK;
    }

    public void setPeddetPK(PeddetPK peddetPK) {
        this.peddetPK = peddetPK;
    }

    public int getProCod() {
        return proCod;
    }

    public void setProCod(int proCod) {
        this.proCod = proCod;
    }

    public short getProChkBar() {
        return proChkBar;
    }

    public void setProChkBar(short proChkBar) {
        this.proChkBar = proChkBar;
    }

    public int getProQtde() {
        return proQtde;
    }

    public void setProQtde(int proQtde) {
        this.proQtde = proQtde;
    }

    public int getProSldo() {
        return proSldo;
    }

    public void setProSldo(int proSldo) {
        this.proSldo = proSldo;
    }

    public Integer getProFator() {
        return proFator;
    }

    public void setProFator(Integer proFator) {
        this.proFator = proFator;
    }

    public int getPedDetSitCod() {
        return pedDetSitCod;
    }

    public void setPedDetSitCod(int pedDetSitCod) {
        this.pedDetSitCod = pedDetSitCod;
    }

    public String getProObs() {
        return proObs;
    }

    public void setProObs(String proObs) {
        this.proObs = proObs;
    }

    public Integer getProInd() {
        return proInd;
    }

    public void setProInd(Integer proInd) {
        this.proInd = proInd;
    }

    public List<Pedgradqtde> getPedgradqtdeList() {
        return pedgradqtdeList;
    }

    public void setPedgradqtdeList(List<Pedgradqtde> pedgradqtdeList) {
        this.pedgradqtdeList = pedgradqtdeList;
    }

    public List<Pedgradcor> getPedgradcorList() {
        return pedgradcorList;
    }

    public void setPedgradcorList(List<Pedgradcor> pedgradcorList) {
        this.pedgradcorList = pedgradcorList;
    }

    public Pedcab getPedcab() {
        return pedcab;
    }

    public void setPedcab(Pedcab pedcab) {
        this.pedcab = pedcab;
    }

    public List<Pedgradtam> getPedgradtamList() {
        return pedgradtamList;
    }

    public void setPedgradtamList(List<Pedgradtam> pedgradtamList) {
        this.pedgradtamList = pedgradtamList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peddetPK != null ? peddetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peddet)) {
            return false;
        }
        Peddet other = (Peddet) object;
        if ((this.peddetPK == null && other.peddetPK != null) || (this.peddetPK != null && !this.peddetPK.equals(other.peddetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.Peddet[ peddetPK=" + peddetPK + " ]";
    }

    //@Override
    //protected void finalize() throws Throwable {
    //    System.out.println("Objeto " + this + " vai ser destruido ...");
    //    super.finalize();
    //}
    
}
