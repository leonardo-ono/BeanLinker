/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.pedido.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leonardo
 */
@Entity
@Table(name = "PRODUTOS")
@XmlRootElement
public class Produtos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PROCOD")
    private Integer procod;
    @Basic(optional = false)
    @Column(name = "PROREF")
    private String proref;
    @Basic(optional = false)
    @Column(name = "FORCOD")
    private int forcod;
    @Basic(optional = false)
    @Column(name = "PRODESC")
    private String prodesc;
    @Basic(optional = false)
    @Column(name = "PROSINT")
    private String prosint;
    @Column(name = "UNICODC")
    private Integer unicodc;
    @Column(name = "UNICODV")
    private Integer unicodv;
    @Basic(optional = false)
    @Column(name = "GRPCOD")
    private String grpcod;
    @Column(name = "PROTAM")
    private String protam;
    @Basic(optional = false)
    @Column(name = "PROIVA")
    private String proiva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PROPESO")
    private BigDecimal propeso;
    @Column(name = "PROCUS")
    private BigDecimal procus;
    @Column(name = "PRODES1")
    private BigDecimal prodes1;
    @Column(name = "PRODES2")
    private BigDecimal prodes2;
    @Column(name = "PRODES3")
    private BigDecimal prodes3;
    @Basic(optional = false)
    @Column(name = "PROVND")
    private BigDecimal provnd;
    @Column(name = "PROIPI")
    private BigDecimal proipi;
    @Column(name = "PROICM25")
    private String proicm25;
    @Column(name = "PROMSHE")
    private BigDecimal promshe;
    @Column(name = "PROMSHK")
    private BigDecimal promshk;
    @Column(name = "PROMSHO")
    private BigDecimal promsho;
    @Column(name = "PROFATOR")
    private Short profator;
    
    //@Basic(optional = false)
    //@Column(name = "SIPCOD")
    //private int sipcod;
    @ManyToOne
    @JoinColumn(name="SIPCOD", referencedColumnName="SIPCOD")
    private Situacaoproduto situacaoproduto;
    
    @Column(name = "PROANT")
    private BigDecimal proant;
    @Column(name = "PRONPR")
    private BigDecimal pronpr;
    @Column(name = "PRODPN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodpn;
    @Column(name = "PRODUA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date produa;
    @Column(name = "PRODCA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodca;
    @Column(name = "PROICMRED")
    private Boolean proicmred;
    @Basic(optional = false)
    @Column(name = "PROCHKBAR")
    private int prochkbar;
    @Basic(optional = false)
    @Column(name = "PROCHKDEV")
    private boolean prochkdev;
    @Basic(optional = false)
    @Column(name = "PROFATORPED")
    private BigDecimal profatorped;
    @Basic(optional = false)
    @Column(name = "CLASSCOD1")
    private String classcod1;
    @Basic(optional = false)
    @Column(name = "CLASSCOD2")
    private String classcod2;
    @Basic(optional = false)
    @Column(name = "CLASSCOD3")
    private String classcod3;
    @Basic(optional = false)
    @Column(name = "PROLAN")
    private short prolan;
    @Basic(optional = false)
    @Column(name = "PROMRG")
    private BigDecimal promrg;
    @Basic(optional = false)
    @Column(name = "PROREP")
    private int prorep;
    @Basic(optional = false)
    @Column(name = "CF")
    private int cf;
    @Column(name = "CSTENTRADA")
    private String cstentrada;
    @Column(name = "CSTSAIDA")
    private String cstsaida;
    @Column(name = "GRPCOD2")
    private String grpcod2;

    public Produtos() {
    }

    public Produtos(Integer procod) {
        this.procod = procod;
    }

    public Produtos(Integer procod, String proref, int forcod, String prodesc, String prosint, String grpcod, String proiva, BigDecimal propeso, BigDecimal provnd, Situacaoproduto situacaoproduto, int prochkbar, boolean prochkdev, BigDecimal profatorped, String classcod1, String classcod2, String classcod3, short prolan, BigDecimal promrg, int prorep, int cf) {
        this.procod = procod;
        this.proref = proref;
        this.forcod = forcod;
        this.prodesc = prodesc;
        this.prosint = prosint;
        this.grpcod = grpcod;
        this.proiva = proiva;
        this.propeso = propeso;
        this.provnd = provnd;
        this.situacaoproduto = situacaoproduto;
        this.prochkbar = prochkbar;
        this.prochkdev = prochkdev;
        this.profatorped = profatorped;
        this.classcod1 = classcod1;
        this.classcod2 = classcod2;
        this.classcod3 = classcod3;
        this.prolan = prolan;
        this.promrg = promrg;
        this.prorep = prorep;
        this.cf = cf;
    }

    public Integer getProcod() {
        return procod;
    }

    public void setProcod(Integer procod) {
        this.procod = procod;
    }

    public String getProref() {
        return proref;
    }

    public void setProref(String proref) {
        this.proref = proref;
    }

    public int getForcod() {
        return forcod;
    }

    public void setForcod(int forcod) {
        this.forcod = forcod;
    }

    public String getProdesc() {
        return prodesc;
    }

    public void setProdesc(String prodesc) {
        this.prodesc = prodesc;
    }

    public String getProsint() {
        return prosint;
    }

    public void setProsint(String prosint) {
        this.prosint = prosint;
    }

    public Integer getUnicodc() {
        return unicodc;
    }

    public void setUnicodc(Integer unicodc) {
        this.unicodc = unicodc;
    }

    public Integer getUnicodv() {
        return unicodv;
    }

    public void setUnicodv(Integer unicodv) {
        this.unicodv = unicodv;
    }

    public String getGrpcod() {
        return grpcod;
    }

    public void setGrpcod(String grpcod) {
        this.grpcod = grpcod;
    }

    public String getProtam() {
        return protam;
    }

    public void setProtam(String protam) {
        this.protam = protam;
    }

    public String getProiva() {
        return proiva;
    }

    public void setProiva(String proiva) {
        this.proiva = proiva;
    }

    public BigDecimal getPropeso() {
        return propeso;
    }

    public void setPropeso(BigDecimal propeso) {
        this.propeso = propeso;
    }

    public BigDecimal getProcus() {
        return procus;
    }

    public void setProcus(BigDecimal procus) {
        this.procus = procus;
    }

    public BigDecimal getProdes1() {
        return prodes1;
    }

    public void setProdes1(BigDecimal prodes1) {
        this.prodes1 = prodes1;
    }

    public BigDecimal getProdes2() {
        return prodes2;
    }

    public void setProdes2(BigDecimal prodes2) {
        this.prodes2 = prodes2;
    }

    public BigDecimal getProdes3() {
        return prodes3;
    }

    public void setProdes3(BigDecimal prodes3) {
        this.prodes3 = prodes3;
    }

    public BigDecimal getProvnd() {
        return provnd;
    }

    public void setProvnd(BigDecimal provnd) {
        this.provnd = provnd;
    }

    public BigDecimal getProipi() {
        return proipi;
    }

    public void setProipi(BigDecimal proipi) {
        this.proipi = proipi;
    }

    public String getProicm25() {
        return proicm25;
    }

    public void setProicm25(String proicm25) {
        this.proicm25 = proicm25;
    }

    public BigDecimal getPromshe() {
        return promshe;
    }

    public void setPromshe(BigDecimal promshe) {
        this.promshe = promshe;
    }

    public BigDecimal getPromshk() {
        return promshk;
    }

    public void setPromshk(BigDecimal promshk) {
        this.promshk = promshk;
    }

    public BigDecimal getPromsho() {
        return promsho;
    }

    public void setPromsho(BigDecimal promsho) {
        this.promsho = promsho;
    }

    public Short getProfator() {
        return profator;
    }

    public void setProfator(Short profator) {
        this.profator = profator;
    }

    public Situacaoproduto getSituacaoproduto() {
        return situacaoproduto;
    }

    public void setSituacaoproduto(Situacaoproduto situacaoproduto) {
        this.situacaoproduto = situacaoproduto;
    }

    public BigDecimal getProant() {
        return proant;
    }

    public void setProant(BigDecimal proant) {
        this.proant = proant;
    }

    public BigDecimal getPronpr() {
        return pronpr;
    }

    public void setPronpr(BigDecimal pronpr) {
        this.pronpr = pronpr;
    }

    public Date getProdpn() {
        return prodpn;
    }

    public void setProdpn(Date prodpn) {
        this.prodpn = prodpn;
    }

    public Date getProdua() {
        return produa;
    }

    public void setProdua(Date produa) {
        this.produa = produa;
    }

    public Date getProdca() {
        return prodca;
    }

    public void setProdca(Date prodca) {
        this.prodca = prodca;
    }

    public Boolean getProicmred() {
        return proicmred;
    }

    public void setProicmred(Boolean proicmred) {
        this.proicmred = proicmred;
    }

    public int getProchkbar() {
        return prochkbar;
    }

    public void setProchkbar(int prochkbar) {
        this.prochkbar = prochkbar;
    }

    public boolean getProchkdev() {
        return prochkdev;
    }

    public void setProchkdev(boolean prochkdev) {
        this.prochkdev = prochkdev;
    }

    public BigDecimal getProfatorped() {
        return profatorped;
    }

    public void setProfatorped(BigDecimal profatorped) {
        this.profatorped = profatorped;
    }

    public String getClasscod1() {
        return classcod1;
    }

    public void setClasscod1(String classcod1) {
        this.classcod1 = classcod1;
    }

    public String getClasscod2() {
        return classcod2;
    }

    public void setClasscod2(String classcod2) {
        this.classcod2 = classcod2;
    }

    public String getClasscod3() {
        return classcod3;
    }

    public void setClasscod3(String classcod3) {
        this.classcod3 = classcod3;
    }

    public short getProlan() {
        return prolan;
    }

    public void setProlan(short prolan) {
        this.prolan = prolan;
    }

    public BigDecimal getPromrg() {
        return promrg;
    }

    public void setPromrg(BigDecimal promrg) {
        this.promrg = promrg;
    }

    public int getProrep() {
        return prorep;
    }

    public void setProrep(int prorep) {
        this.prorep = prorep;
    }

    public int getCf() {
        return cf;
    }

    public void setCf(int cf) {
        this.cf = cf;
    }

    public String getCstentrada() {
        return cstentrada;
    }

    public void setCstentrada(String cstentrada) {
        this.cstentrada = cstentrada;
    }

    public String getCstsaida() {
        return cstsaida;
    }

    public void setCstsaida(String cstsaida) {
        this.cstsaida = cstsaida;
    }

    public String getGrpcod2() {
        return grpcod2;
    }

    public void setGrpcod2(String grpcod2) {
        this.grpcod2 = grpcod2;
    }

    // Adicionado depois
    public BigDecimal getProcusliq() {
        BigDecimal procusliq = procus;
        procusliq = procusliq.multiply(BigDecimal.valueOf(100).subtract(prodes1).divide(BigDecimal.valueOf(100)));
        procusliq = procusliq.multiply(BigDecimal.valueOf(100).subtract(prodes2).divide(BigDecimal.valueOf(100)));
        procusliq = procusliq.multiply(BigDecimal.valueOf(100).subtract(prodes3).divide(BigDecimal.valueOf(100)));
        return procusliq;
    }
    public void setProcusliq(BigDecimal procusliq) {
        // Nao faz nada
    }

    public BigDecimal getPromshevnd() {
        return BigDecimal.TEN;
    }
    
    public void setPromshevnd(BigDecimal vnd) {
        // Nao faz nada
    }
    
    public BigDecimal getPromshkvnd() {
        return BigDecimal.TEN;
    }
    
    public void setPromshkvnd(BigDecimal vnd) {
        // Nao faz nada
    }

    public BigDecimal getPromshovnd() {
        return BigDecimal.TEN;
    }
    
    public void setPromshovnd(BigDecimal vnd) {
        // Nao faz nada
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procod != null ? procod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.procod == null && other.procod != null) || (this.procod != null && !this.procod.equals(other.procod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compra.pedido.entidades.Produtos[ procod=" + procod + " ]";
    }
    
}
