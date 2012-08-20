package compra.pedido.entidades;

import compra.pedido.entidades.Pedcab;
import compra.pedido.entidades.PeddetPK;
import compra.pedido.entidades.Pedgradcor;
import compra.pedido.entidades.Pedgradqtde;
import compra.pedido.entidades.Pedgradtam;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-08-19T17:27:04")
@StaticMetamodel(Peddet.class)
public class Peddet_ { 

    public static volatile SingularAttribute<Peddet, Integer> proCod;
    public static volatile ListAttribute<Peddet, Pedgradcor> pedgradcorList;
    public static volatile ListAttribute<Peddet, Pedgradtam> pedgradtamList;
    public static volatile SingularAttribute<Peddet, PeddetPK> peddetPK;
    public static volatile SingularAttribute<Peddet, Short> proChkBar;
    public static volatile SingularAttribute<Peddet, Integer> proInd;
    public static volatile SingularAttribute<Peddet, String> proObs;
    public static volatile SingularAttribute<Peddet, Integer> proQtde;
    public static volatile SingularAttribute<Peddet, Integer> proFator;
    public static volatile SingularAttribute<Peddet, Integer> pedDetSitCod;
    public static volatile SingularAttribute<Peddet, Pedcab> pedcab;
    public static volatile ListAttribute<Peddet, Pedgradqtde> pedgradqtdeList;
    public static volatile SingularAttribute<Peddet, Integer> proSldo;

}