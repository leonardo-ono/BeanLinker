package compra.pedido.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import ono.leo.jvcontroller.core.JvController;

/**
 *
 * @author leo
 */
public class Controller {

    private static JvController jc = JvController.getInstance();

    public static void main(String[] args) throws Exception {
        for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            System.out.println(laf);
        }
        // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

        jc.registerViewClass("ComprasPedidoView", "compra.pedido.view.ComprasPedidoView");
        jc.registerViewClass("PedcabView", "compra.pedido.view.entidades.PedcabView");
        jc.registerViewClass("PeddetView", "compra.pedido.view.entidades.PeddetView");
        jc.registerViewClass("PedgradcorView", "compra.pedido.view.entidades.PedgradcorView");
        jc.registerViewClass("PedgradqtdeView", "compra.pedido.view.entidades.PedgradqtdeView");
        jc.registerViewClass("PedgradtamView", "compra.pedido.view.entidades.PedgradtamView");
        jc.registerViewClass("ProdutoView", "compra.pedido.view.entidades.ProdutoView");

        jc.registerModelClass("ComprasPedido", "compra.pedido.modelo.ComprasPedidoImpl");
        jc.registerModelClass("Pedcab", "compra.pedido.entidades.Pedcab");
        jc.registerModelClass("Peddet", "compra.pedido.entidades.Peddet");
        jc.registerModelClass("PeddetPK", "compra.pedido.entidades.PeddetPK");
        jc.registerModelClass("Pedgradcor", "compra.pedido.entidades.Pedgradcor");
        jc.registerModelClass("PedgradcorPK", "compra.pedido.entidades.PedgradcorPK");
        jc.registerModelClass("Pedgradqtde", "compra.pedido.entidades.Pedgradqtde");
        jc.registerModelClass("PedgradqtdePK", "compra.pedido.entidades.PedgradqtdePK");
        jc.registerModelClass("Pedgradtam", "compra.pedido.entidades.Pedgradtam");
        jc.registerModelClass("PedgradtamPK", "compra.pedido.entidades.PedgradtamPK");

        jc.registerControllerClass("Controller", "compra.pedido.controller.Controller");

        jc.registerConversorClass("IntegerConversor", "compra.pedido.conversor.IntegerConversor");

        // Vinculo entre classes 


        // View Model
        jc.bindClassBean("ComprasPedidoView.pedcabView", "ComprasPedido.pedidoSelecionado");

        jc.bindClassProperty("PedcabView.textNumero.text", "", "", "", "", "", "controller.pednum", "", "", "''", "", "integerConversor", "", "", "", "");
        jc.bindClassProperty("PedcabView.textReferencia.text", "", "", "", "", "", "controller.referencia", "", "", "''", "", "integerConversor", "", "", "", "");
        // jc.bindClassProperty("PedcabView.textNumero.text", "controller.pednum", "integerConversor", "");
        // jc.bindClassProperty("PedcabView.textReferencia.text", "controller.referencia", "integerConversor", "");
        jc.bindClassCollection("", "Pedcab.peddetList", "PedcabView.addPeddetView", "PedcabView.removePeddetView", "PeddetView");
        jc.bindClassAction("PedcabView.buttonAbrir.action", "comprasPedido.abrirPedido(controller.pednum)", "", "", "", "Abrir pedido");
        jc.bindClassAction("PedcabView.buttonSalvar.action", "comprasPedido.fecharPedido()", "", "", "", "Fechar pedido");
        jc.bindClassAction("PedcabView.textReferencia.action", "comprasPedido.adicionarItem(controller.referencia, controller.referencia.toString())", "", "", "", "Adicionar item");

        jc.bindClassProperty("PeddetView.produtoView.textCodigo.text", "Peddet.proCod", "integerConversor", "");
        jc.bindClassCollection("", "Peddet.pedgradtamList", "PeddetView.addGradeViewItem", "PeddetView.removeGradeViewItem", "PedgradtamView");
        jc.bindClassCollection("", "Peddet.pedgradcorList", "PeddetView.addGradeViewItem", "PeddetView.removeGradeViewItem", "PedgradcorView");
        jc.bindClassCollection("", "Peddet.pedgradqtdeList", "PeddetView.addGradeViewItem", "PeddetView.removeGradeViewItem", "PedgradqtdeView");
        jc.bindClassAction("PeddetView.buttonRemover.action", "comprasPedido.removerItem(Peddet.proCod)", "", "", "", "Remover este item");

        jc.bindClassProperty("PedgradcorView.corCod", "Pedgradcor.pedgradcorPK.corCod", "", "");
        jc.bindClassProperty("PedgradcorView.linPos", "Pedgradcor.linPos", "", "");
        jc.bindClassProperty("PedgradcorView.labelCor.text", "Pedgradcor.pedgradcorPK.corCod", "integerConversor", "");
        jc.bindClassAction("PedgradcorView.buttonExcluir.action", "controller.removerCor(Pedgradcor.peddet.proCod, Pedgradcor.pedgradcorPK.corCod)", "", "", "", "x");
        
        jc.bindClassProperty("PedgradtamView.tamCod", "Pedgradtam.pedgradtamPK.tamCod", "", "");
        jc.bindClassProperty("PedgradtamView.colPos", "Pedgradtam.colPos", "", "");
        jc.bindClassProperty("PedgradtamView.labelTamanho.text", "Pedgradtam.pedgradtamPK.tamCod", "integerConversor", "");
        jc.bindClassAction("PedgradtamView.buttonExcluir.action", "controller.removerTamanho(Pedgradtam.peddet.proCod, Pedgradtam.pedgradtamPK.tamCod)", "", "", "", "x");

        jc.bindClassProperty("PedgradqtdeView.textQuantidade.text", "Pedgradqtde.proQtde", "integerConversor", "");
        jc.bindClassProperty("PedgradqtdeView.tamCod", "Pedgradqtde.pedgradtam.pedgradtamPK.tamCod", "", "");
        jc.bindClassProperty("PedgradqtdeView.corCod", "Pedgradqtde.pedgradcor.pedgradcorPK.corCod", "", "");

        jc.newInstance("comprasPedidoView", "ComprasPedidoView");
        jc.newInstance("comprasPedido", "ComprasPedido");
        jc.newInstance("controller", "Controller");
        jc.newInstance("integerConversor", "IntegerConversor");

        jc.bindInstanceBean("comprasPedidoView", "comprasPedido");

        jc.getELContext().set("comprasPedidoView.visible", true);
    }
    
    public void removerTamanho(int proCod, int tamCod) {
        try {
            jc.getELContext().invoke("comprasPedido.selecionarItem", proCod);
            jc.getELContext().invoke("comprasPedido.removerTamanho", tamCod);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removerCor(int proCod, int corCod) {
        try {
            jc.getELContext().invoke("comprasPedido.selecionarItem", proCod);
            jc.getELContext().invoke("comprasPedido.removerCor", corCod);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
