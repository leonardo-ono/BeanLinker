package compra.pedido.entidades;

import compra.pedido.entidades.Peddet;
import compra.pedido.entidades.PedgradcorPK;
import compra.pedido.entidades.Pedgradqtde;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-08-19T17:27:04")
@StaticMetamodel(Pedgradcor.class)
public class Pedgradcor_ { 

    public static volatile SingularAttribute<Pedgradcor, Integer> linPos;
    public static volatile SingularAttribute<Pedgradcor, Peddet> peddet;
    public static volatile SingularAttribute<Pedgradcor, PedgradcorPK> pedgradcorPK;
    public static volatile ListAttribute<Pedgradcor, Pedgradqtde> pedgradqtdeList;

}