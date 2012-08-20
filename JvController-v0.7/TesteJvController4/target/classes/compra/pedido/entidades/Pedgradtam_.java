package compra.pedido.entidades;

import compra.pedido.entidades.Peddet;
import compra.pedido.entidades.Pedgradqtde;
import compra.pedido.entidades.PedgradtamPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-08-19T20:12:43")
@StaticMetamodel(Pedgradtam.class)
public class Pedgradtam_ { 

    public static volatile SingularAttribute<Pedgradtam, Peddet> peddet;
    public static volatile SingularAttribute<Pedgradtam, PedgradtamPK> pedgradtamPK;
    public static volatile SingularAttribute<Pedgradtam, Integer> colPos;
    public static volatile ListAttribute<Pedgradtam, Pedgradqtde> pedgradqtdeList;

}