package compra.pedido.entidades;

import compra.pedido.entidades.Peddet;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-08-19T17:27:04")
@StaticMetamodel(Pedcab.class)
public class Pedcab_ { 

    public static volatile SingularAttribute<Pedcab, Integer> forCod;
    public static volatile SingularAttribute<Pedcab, Integer> lojaCod;
    public static volatile SingularAttribute<Pedcab, String> pedForObs;
    public static volatile SingularAttribute<Pedcab, Integer> opeCod;
    public static volatile SingularAttribute<Pedcab, Date> pedDataEmis;
    public static volatile SingularAttribute<Pedcab, Date> pedDataEntr;
    public static volatile SingularAttribute<Pedcab, String> pedRegTipo;
    public static volatile SingularAttribute<Pedcab, Boolean> confFil;
    public static volatile SingularAttribute<Pedcab, String> pedObs;
    public static volatile SingularAttribute<Pedcab, Integer> pedNum;
    public static volatile SingularAttribute<Pedcab, Integer> pedSitCod;
    public static volatile SingularAttribute<Pedcab, Character> pedTipo;
    public static volatile SingularAttribute<Pedcab, Integer> abertoPor;
    public static volatile SingularAttribute<Pedcab, Integer> pedCod;
    public static volatile SingularAttribute<Pedcab, Date> pedDataPrg;
    public static volatile ListAttribute<Pedcab, Peddet> peddetList;
    public static volatile SingularAttribute<Pedcab, Date> pedDataCad;

}