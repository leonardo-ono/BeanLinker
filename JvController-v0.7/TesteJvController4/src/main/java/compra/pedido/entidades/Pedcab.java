package compra.pedido.entidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe Pedcab.
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
@Entity
@Table(name = "PEDCAB")
public class Pedcab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "PedNum")
    private int pedNum;
    @Basic(optional = false)
    @Column(name = "PedDataCad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDataCad;
    @Column(name = "PedDataEmis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDataEmis;
    @Basic(optional = false)
    @Column(name = "ForCod")
    private int forCod;
    @Basic(optional = false)
    @Column(name = "OpeCod")
    private int opeCod;
    @Basic(optional = false)
    @Column(name = "PedSitCod")
    private int pedSitCod;
    @Lob
    @Column(name = "PedObs")
    private String pedObs;
    @Column(name = "PedDataPrg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDataPrg;
    @Basic(optional = false)
    @Column(name = "PedTipo")
    private char pedTipo;
    @Basic(optional = false)
    @Column(name = "PedRegTipo")
    private String pedRegTipo;
    @Id
    @Basic(optional = false)
    @Column(name = "PedCod")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer pedCod;
    @Column(name = "LojaCod")
    private Integer lojaCod;
    @Lob
    @Column(name = "PedForObs")
    private String pedForObs;
    @Column(name = "PedDataEntr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDataEntr;
    @Basic(optional = false)
    @Column(name = "ConfFil")
    private boolean confFil;
    @Column(name = "AbertoPor")
    private Integer abertoPor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedcab", fetch= FetchType.EAGER)
    private List<Peddet> peddetList = new ArrayList<Peddet>();

    public Pedcab() {
    }

    public Pedcab(Integer pedCod) {
        this.pedCod = pedCod;
    }

    public Pedcab(Integer pedCod, int pedNum, Date pedDataCad, int forCod, int opeCod, int pedSitCod, char pedTipo, String pedRegTipo, boolean confFil) {
        this.pedCod = pedCod;
        this.pedNum = pedNum;
        this.pedDataCad = pedDataCad;
        this.forCod = forCod;
        this.opeCod = opeCod;
        this.pedSitCod = pedSitCod;
        this.pedTipo = pedTipo;
        this.pedRegTipo = pedRegTipo;
        this.confFil = confFil;
    }

    public int getPedNum() {
        return pedNum;
    }

    public void setPedNum(int pedNum) {
        this.pedNum = pedNum;
    }

    public Date getPedDataCad() {
        return pedDataCad;
    }

    public void setPedDataCad(Date pedDataCad) {
        this.pedDataCad = pedDataCad;
    }

    public Date getPedDataEmis() {
        return pedDataEmis;
    }

    public void setPedDataEmis(Date pedDataEmis) {
        this.pedDataEmis = pedDataEmis;
    }

    public int getForCod() {
        return forCod;
    }

    public void setForCod(int forCod) {
        this.forCod = forCod;
    }

    public int getOpeCod() {
        return opeCod;
    }

    public void setOpeCod(int opeCod) {
        this.opeCod = opeCod;
    }

    public int getPedSitCod() {
        return pedSitCod;
    }

    public void setPedSitCod(int pedSitCod) {
        this.pedSitCod = pedSitCod;
    }

    public String getPedObs() {
        return pedObs;
    }

    public void setPedObs(String pedObs) {
        this.pedObs = pedObs;
    }

    public Date getPedDataPrg() {
        return pedDataPrg;
    }

    public void setPedDataPrg(Date pedDataPrg) {
        this.pedDataPrg = pedDataPrg;
    }

    public char getPedTipo() {
        return pedTipo;
    }

    public void setPedTipo(char pedTipo) {
        this.pedTipo = pedTipo;
    }

    public String getPedRegTipo() {
        return pedRegTipo;
    }

    public void setPedRegTipo(String pedRegTipo) {
        this.pedRegTipo = pedRegTipo;
    }

    public Integer getPedCod() {
        return pedCod;
    }

    public void setPedCod(Integer pedCod) {
        this.pedCod = pedCod;
    }

    public Integer getLojaCod() {
        return lojaCod;
    }

    public void setLojaCod(Integer lojaCod) {
        this.lojaCod = lojaCod;
    }

    public String getPedForObs() {
        return pedForObs;
    }

    public void setPedForObs(String pedForObs) {
        this.pedForObs = pedForObs;
    }

    public Date getPedDataEntr() {
        return pedDataEntr;
    }

    public void setPedDataEntr(Date pedDataEntr) {
        this.pedDataEntr = pedDataEntr;
    }

    public boolean getConfFil() {
        return confFil;
    }

    public void setConfFil(boolean confFil) {
        this.confFil = confFil;
    }

    public Integer getAbertoPor() {
        return abertoPor;
    }

    public void setAbertoPor(Integer abertoPor) {
        this.abertoPor = abertoPor;
    }

    public List<Peddet> getPeddetList() {
        return peddetList;
    }

    public void setPeddetList(List<Peddet> peddetList) {
        this.peddetList = peddetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedCod != null ? pedCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedcab)) {
            return false;
        }
        Pedcab other = (Pedcab) object;
        if ((this.pedCod == null && other.pedCod != null) || (this.pedCod != null && !this.pedCod.equals(other.pedCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.Pedcab[ pedCod=" + pedCod + " ]";
    }

    //@Override
    //protected void finalize() throws Throwable {
    //    System.out.println("Objeto " + this + " vai ser destruido ...");
    //    super.finalize();
    //}
    
}
