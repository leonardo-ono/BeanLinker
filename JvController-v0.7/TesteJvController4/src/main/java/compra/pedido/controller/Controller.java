package compra.pedido.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
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
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

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
        jc.registerConversorClass("BigDecimalConversor", "compra.pedido.conversor.BigDecimalConversor");
        jc.registerConversorClass("BooleanConversor", "compra.pedido.conversor.BooleanConversor");
        jc.registerConversorClass("ShortConversor", "compra.pedido.conversor.ShortConversor");

        // Vinculo entre classes 

        // Produto
        jc.bindClassProperty("", "ProdutoView.textProcod.text", "Produtos.procod", "integerConversor", "");
        jc.bindClassProperty("", "ProdutoView.textProref.text", "Produtos.proref", "", "");
        jc.bindClassProperty("", "ProdutoView.textProdesc.text", "Produtos.prodesc", "", "");
        jc.bindClassProperty("", "ProdutoView.textProsint.text", "Produtos.prosint", "", "");
        jc.bindClassProperty("", "ProdutoView.textProtam.text", "Produtos.protam", "", "");
        jc.bindClassProperty("", "ProdutoView.textGrpcod.text", "Produtos.grpcod", "", "");
        jc.bindClassProperty("", "ProdutoView.textUnidescv.text", "Produtos.unicodv", "integerConversor", ""); // TODO conversor
        jc.bindClassProperty("", "ProdutoView.textPropeso.text", "Produtos.propeso", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProcus.text", "Produtos.procus", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProdes1.text", "Produtos.prodes1", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProdes2.text", "Produtos.prodes2", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProdes3.text", "Produtos.prodes3", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProcusliq.text", "Produtos.procusliq", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProipi.text", "Produtos.proipi", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textPromrg.text", "Produtos.promrg", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProvnd.text", "Produtos.provnd", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProicm25.text", "Produtos.proicm25", "", ""); 
        jc.bindClassProperty("", "ProdutoView.textProicmred.text", "Produtos.proicmred", "booleanConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProfator.text", "Produtos.profator", "shortConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textPromshe.text", "Produtos.promshe", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textPromshk.text", "Produtos.promshk", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textPromsho.text", "Produtos.promsho", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textPromshevnd.text", "Produtos.promshevnd", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textPromshkvnd.text", "Produtos.promshkvnd", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textPromshovnd.text", "Produtos.promshovnd", "bigDecimalConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textProiva.text", "Produtos.proiva", "", ""); 
        jc.bindClassProperty("", "ProdutoView.textProchkdev.text", "Produtos.prochkdev", "booleanConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textClasscod1.text", "Produtos.classcod1", "", ""); 
        jc.bindClassProperty("", "ProdutoView.textClasscod2.text", "Produtos.classcod2", "", ""); 
        jc.bindClassProperty("", "ProdutoView.textClasscod3.text", "Produtos.classcod3", "", ""); 
        jc.bindClassProperty("", "ProdutoView.textProchkbar.text", "Produtos.prochkbar", "integerConversor", ""); 
        jc.bindClassProperty("", "ProdutoView.textSipdesc.text", "Produtos.situacaoproduto.sipdesc", "", ""); 
        

        // View Model
        jc.bindClassBean("pedido", "ComprasPedidoView.pedcabView", "ComprasPedido.pedidoSelecionado");

        jc.bindClassProperty("", "PedcabView.textNumero.text", "", "", "", "", "", "controller.pednum", "", "", "''", "", "integerConversor", "", "", "", "");
        jc.bindClassProperty("", "PedcabView.textReferencia.text", "", "", "", "", "", "controller.referencia", "", "", "''", "", "integerConversor", "", "", "", "");
        // jc.bindClassProperty("PedcabView.textNumero.text", "controller.pednum", "integerConversor", "");
        // jc.bindClassProperty("PedcabView.textReferencia.text", "controller.referencia", "integerConversor", "");
        
        jc.bindClassCollection("itens", "controller.concat('item', modelItem.proCod)", "", "Pedcab.peddetList", "PedcabView.addPeddetView", "PedcabView.removePeddetView", "PeddetView");
        jc.bindClassAction("PedcabView.buttonAbrir.action", "comprasPedido.abrirPedido(controller.pednum)", "", "", "", "Abrir pedido");
        jc.bindClassAction("PedcabView.buttonSalvar.action", "controller.fecharPedido()", "", "", "", "Fechar pedido");
        jc.bindClassAction("PedcabView.textReferencia.action", "comprasPedido.adicionarItem(controller.referencia, controller.referencia.toString())", "", "", "", "Adicionar item");
        
        jc.bindClassProperty("", "PedcabView.textCodigoDaLoja.text", "", "", "", "", "", "controller.codigoDaLoja", "", "", "''", "", "integerConversor", "", "", "", "");
        jc.bindClassAction("PedcabView.textCodigoDaLoja.action", "comprasPedido.selecionarLoja(controller.codigoDaLoja)", "", "", "", "Selecionar loja");

        
        //jc.bindClassProperty("", "PeddetView.produtoView.textCodigo.text", "Peddet.proCod", "integerConversor", "");
        jc.bindClassBean("", "PeddetView.produtoView", "Peddet.produto");
        
        jc.bindClassCollection("", "", "", "Peddet.pedgradtamList", "PeddetView.addGradeViewItem", "PeddetView.removeGradeViewItem", "PedgradtamView");
        jc.bindClassCollection("", "", "", "Peddet.pedgradcorList", "PeddetView.addGradeViewItem", "PeddetView.removeGradeViewItem", "PedgradcorView");
        jc.bindClassCollection("", "", "", "Peddet.pedgradqtdeList", "PeddetView.addGradeViewItem", "PeddetView.removeGradeViewItem", "PedgradqtdeView");
        jc.bindClassAction("PeddetView.buttonRemover.action", "comprasPedido.removerItem(Peddet.proCod)", "", "controller.concat('main.pedido.itens.item',Peddet.proCod)", "controller.concat('main.pedido.itens.item',Peddet.proCod)", "Remover este item");

        jc.bindClassProperty("", "PeddetView.gradeView.addCor.text", "controller.corCod", "integerConversor", "");
        jc.bindClassProperty("", "PeddetView.gradeView.addTamanho.text", "controller.tamCod", "integerConversor", "");
        jc.bindClassAction("PeddetView.gradeView.addCor.action", "controller.adicionarCor(Peddet.proCod, controller.corCod)", "", "controller.concat('main.pedido.itens.item',Peddet.proCod)", "controller.concat('main.pedido.itens.item',Peddet.proCod)", "Adicionar cor");
        jc.bindClassAction("PeddetView.gradeView.addTamanho.action", "controller.adicionarTamanho(Peddet.proCod, controller.tamCod)", "", "controller.concat('main.pedido.itens.item',Peddet.proCod)", "controller.concat('main.pedido.itens.item',Peddet.proCod)", "Adicionar tamanho");

        jc.bindClassProperty("", "PedgradcorView.corCod", "Pedgradcor.pedgradcorPK.corCod", "", "");
        jc.bindClassProperty("", "PedgradcorView.linPos", "Pedgradcor.linPos", "", "");
        jc.bindClassProperty("", "PedgradcorView.labelCor.text", "Pedgradcor.pedgradcorPK.corCod", "integerConversor", "");
        jc.bindClassAction("PedgradcorView.buttonExcluir.action", "controller.removerCor(Pedgradcor.peddet.proCod, Pedgradcor.pedgradcorPK.corCod)", "", "controller.concat('main.pedido.itens.item',Pedgradcor.peddet.proCod)", "controller.concat('main.pedido.itens.item',Pedgradcor.peddet.proCod)", "x");
        // jc.bindClassAction("PedgradcorView.buttonExcluir.action", "controller.removerCor(Pedgradcor.peddet.proCod, Pedgradcor.pedgradcorPK.corCod)", "", "", "", "x");
        
        jc.bindClassProperty("", "PedgradtamView.tamCod", "Pedgradtam.pedgradtamPK.tamCod", "", "");
        jc.bindClassProperty("", "PedgradtamView.colPos", "Pedgradtam.colPos", "", "");
        jc.bindClassProperty("", "PedgradtamView.labelTamanho.text", "Pedgradtam.pedgradtamPK.tamCod", "integerConversor", "");
        jc.bindClassAction("PedgradtamView.buttonExcluir.action", "controller.removerTamanho(Pedgradtam.peddet.proCod, Pedgradtam.pedgradtamPK.tamCod)", "", "controller.concat('main.pedido.itens.item',Pedgradtam.peddet.proCod)", "controller.concat('main.pedido.itens.item',Pedgradtam.peddet.proCod)", "x");
        // jc.bindClassAction("PedgradtamView.buttonExcluir.action", "controller.removerTamanho(Pedgradtam.peddet.proCod, Pedgradtam.pedgradtamPK.tamCod)", "", "", "", "x");

        jc.bindClassProperty("", "PedgradqtdeView.textQuantidade.text", "Pedgradqtde.proQtde", "integerConversor", "");
        jc.bindClassProperty("", "PedgradqtdeView.tamCod", "Pedgradqtde.pedgradtam.pedgradtamPK.tamCod", "", "");
        jc.bindClassProperty("", "PedgradqtdeView.corCod", "Pedgradqtde.pedgradcor.pedgradcorPK.corCod", "", "");

        jc.newInstance("comprasPedidoView", "ComprasPedidoView");
        jc.newInstance("comprasPedido", "ComprasPedido");
        jc.newInstance("controller", "Controller");
        jc.newInstance("integerConversor", "IntegerConversor");
        jc.newInstance("bigDecimalConversor", "BigDecimalConversor");
        jc.newInstance("booleanConversor", "BooleanConversor");
        jc.newInstance("shortConversor", "ShortConversor");

        jc.bindInstanceBean("main", "comprasPedidoView", "comprasPedido");

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

    public void adicionarCor(int proCod, int corCod) {
        try {
            jc.getELContext().invoke("comprasPedido.selecionarItem", proCod);
            jc.getELContext().invoke("comprasPedido.adicionarCor", corCod);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adicionarTamanho(int proCod, int tamCod) {
        try {
            jc.getELContext().invoke("comprasPedido.selecionarItem", proCod);
            jc.getELContext().invoke("comprasPedido.adicionarTamanho", tamCod);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fecharPedido() {
        try {
            jc.getELContext().evaluate("comprasPedido.fecharPedido()");
            System.gc();
            
            /*
                    Iterator i = jc.getELContext().getBeans().entrySet().iterator();
                    while (i.hasNext()) {
                        Entry e = (Entry) i.next();
                        if (e.getKey().toString().startsWith("col")) {
                            i.remove();
                        }
                    }
            
            System.out.println("============= fecharPedido() ============");
            Iterator i = jc.getInstanceBinding().getBindings().entrySet().iterator();
            while (i.hasNext()) {
                Entry e = (Entry) i.next();
                System.out.println("instance bindings=" + e);
            }
            System.out.println("============= fecharPedido() ============");
            
            Map<String, Object> mapa = jc.getELContext().getBeans();
            System.out.println("============= jc.getELContext().getBeans() ============");
            for (String key : mapa.keySet()) {
                System.out.println("key=" + key + " value=" + mapa.get(key));
            }
            System.out.println("============= jc.getELContext().getBeans() ============");

             */
            
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String concat(Object str1, Object str2) {
        return "" + str1 + str2;
    }
    
}
