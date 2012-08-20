package compras.pedido.modelo;

import compra.pedido.modelo.ComprasPedidoImpl;
import compra.pedido.modelo.ComprasPedido;
import compra.pedido.entidades.Pedcab;
import compra.pedido.entidades.Pedgradqtde;

/**
 * Classe Main.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class Main {

    public static void main(String[] args) throws Exception {
        teste4();
    }

    private static void teste1() throws Exception {
        ComprasPedido pedido = new ComprasPedidoImpl();
        pedido.abrirPedido(3961757);
        for (Pedcab pedcab : pedido.getPedidosAbertos()) {
            System.out.println("Pedido aberto: " + pedcab);
        }
        System.out.println("---");
        System.out.println("Pedido selecionado: " + pedido.getPedidoSelecionado());

        System.out.println("---");
        pedido.selecionarLoja(0);
        System.out.println("Codigo da loja selecionado: " + pedido.getPedidoSelecionado().getLojaCod());
        System.out.println("Pedido selecionado: " + pedido.getPedidoSelecionado());
        //pedido.selecionarLoja(1);
        //System.out.println("Codigo da loja selecionado: " + pedido.getCodigoDaLojaSelecionada());
        //System.out.println("Pedido selecionado: " + pedido.getPedidoSelecionado());

        System.out.println("---");
        System.out.println("Item selecionado: " + pedido.getItemSelecionado());

        System.out.println("---");
        pedido.selecionarItem(396073);
        System.out.println("Item selecionado: " + pedido.getItemSelecionado());
        //pedido.selecionarItem(453);
        //System.out.println("Item selecionado: " + pedido.getItemSelecionado());

        System.out.println("---");
        pedido.removerItem(999);

        System.out.println("---");
        pedido.adicionarItem(999, "999");

        System.out.println("---");
        pedido.adicionarCor(111);
        pedido.adicionarCor(222);
        pedido.adicionarCor(333);
        pedido.adicionarCor(444);
        pedido.adicionarCor(555);

        pedido.removerCor(222);
        pedido.removerCor(444);

        System.out.println("---");
        pedido.adicionarTamanho(111);
        pedido.adicionarTamanho(222);
        pedido.adicionarTamanho(333);
        pedido.adicionarTamanho(444);
        pedido.adicionarTamanho(555);

        pedido.removerTamanho(222);
        pedido.removerTamanho(444);

        Pedgradqtde[][][] qtdes = pedido.getQuantidades();

        for (int c = 0; c < qtdes.length; c++) {
            for (int t = 0; t < qtdes[0].length; t++) {
                Pedgradqtde qtde = qtdes[0][t][c];
                System.out.print("[" + qtde.getPedgradqtdePK().getTamCod() + ","
                        + qtde.getPedgradqtdePK().getCorCod() + ","
                        + qtde.getProQtde() + "]");
            }
            System.out.println("");
        }

        pedido.fecharPedido();
    }
    
    private static void teste2() throws Exception {
        ComprasPedido pedido = new ComprasPedidoImpl();
        
        pedido.abrirPedido(7851);
        pedido.selecionarLoja(0);
        System.out.println("Codigo da loja selecionado: " + pedido.getPedidoSelecionado().getLojaCod());
        System.out.println("Pedido selecionado: " + pedido.getPedidoSelecionado());
        System.out.println("---");
        pedido.selecionarItem(226156);
        System.out.println("Item selecionado: " + pedido.getItemSelecionado());

        Pedgradqtde[][][] qtdes = pedido.getQuantidades();

        Pedgradqtde qtdeAlterar = null;

        for (int c = 0; c < qtdes[0].length; c++) {
            for (int t = 0; t < qtdes.length; t++) {
                //System.out.println("c="+c+"/t="+t);
                Pedgradqtde qtde = qtdes[0][t][c];
                if (c==2 && t==0) qtdeAlterar = qtde;
                System.out.print("[" + qtde.getPedgradqtdePK().getTamCod() + ","
                        + qtde.getPedgradqtdePK().getCorCod() + ","
                        + qtde.getProQtde() + "]");
            }
            System.out.println("");
        }
        
        qtdeAlterar.setProQtde(1234);
        pedido.atualizarQuantidades();

        pedido.fecharPedido();
    }

    private static void teste3() throws Exception {
        ComprasPedido pedido = new ComprasPedidoImpl();
        
        pedido.abrirPedido(7851);
        pedido.selecionarLoja(0);
        pedido.selecionarLoja(2);
        
        pedido.selecionarItem(226110);
        
        pedido.adicionarCor(111);
        //pedido.adicionarCor(111);

        pedido.adicionarTamanho(111);
        //pedido.adicionarTamanho(111);
        
        pedido.getQuantidades()[0][0][0].setProQtde(111);
        pedido.atualizarQuantidades();
        
        //Pedcab p = pedido.getPedidoSelecionado();
        //System.out.println(p.clone());
    }

    private static void teste4() throws Exception {
        ComprasPedido pedido = new ComprasPedidoImpl();
        
        pedido.abrirPedido(7851);
        pedido.selecionarLoja(0);
        pedido.selecionarLoja(2);
        
        pedido.fecharPedido();
        
        pedido.iniciarPedidoNovo(122, 2000, 'N');
        System.out.println("Pedido novo iniciado pednum=" + pedido.getPedidoSelecionado().getPedNum());
        
        pedido.selecionarLoja(1);
        pedido.selecionarLoja(2);

        pedido.adicionarItem(122001, "teste");
        //pedido.adicionarItem(122001, "teste");
        
        pedido.adicionarItem(122002, "teste2");
        pedido.adicionarItem(122003, "teste3");
        pedido.adicionarItem(122004, "teste4");
        pedido.adicionarItem(122005, "teste5");

        //pedido.removerItem(122003);
        //pedido.removerItem(122006);
        //pedido.removerItem(122005);
        
        pedido.selecionarItem(122001);
        
        pedido.adicionarCor(1);
        pedido.adicionarCor(2);
        pedido.adicionarCor(3);
        
        pedido.adicionarTamanho(6);
        pedido.adicionarTamanho(7);
        pedido.adicionarTamanho(8);
        pedido.adicionarTamanho(9);
        pedido.adicionarTamanho(10);

        //pedido.removerCor(2);
        //pedido.removerCor(4);
        
        pedido.removerTamanho(6);
        pedido.removerTamanho(8);
        pedido.removerTamanho(10);
        
        Pedgradqtde[][][] qtdes = pedido.getQuantidades();
        System.out.println(qtdes);
        
        qtdes[0][0][0].setProQtde(1);
        pedido.getQuantidades()[0][0][1].setProQtde(2);
        pedido.getQuantidades()[0][0][2].setProQtde(3);
        pedido.getQuantidades()[0][1][0].setProQtde(4);
        pedido.getQuantidades()[0][1][1].setProQtde(5);
        pedido.getQuantidades()[0][1][2].setProQtde(6);
        
        pedido.atualizarQuantidades();
        
        pedido.fecharPedido();
        
        
    }
    
}
