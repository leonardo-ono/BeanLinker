package compra.pedido.modelo;

import compra.pedido.entidades.Pedcab;
import compra.pedido.entidades.Peddet;
import compra.pedido.entidades.Pedgradqtde;
import java.util.List;

/**
 * Classe ComprasPedido.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public interface ComprasPedido {
    
    public List<Pedcab> getPedidosAbertos();
    public Pedcab getPedidoSelecionado();
    public Peddet getItemSelecionado();
    public Pedgradqtde[][][] getQuantidades() throws Exception;

    public void iniciarPedidoNovo(int forCod, int opeCod, char tipo) throws Exception;
    public void abrirPedido(int pedNum) throws Exception;
    public void fecharPedido() throws Exception;

    public void selecionarLoja(int lojaCod) throws Exception;
    
    public void selecionarItem(int proCod) throws Exception;
    public void adicionarItem(int proCod, String proRef) throws Exception;
    public void removerItem(int proCod) throws Exception;
    
    public void adicionarCor(int corCod) throws Exception;
    public void removerCor(int corCod) throws Exception;
    
    public void adicionarTamanho(int tamCod) throws Exception;
    public void removerTamanho(int tamCod) throws Exception;
    
    public void atualizarQuantidades() throws Exception;
    public void restaurarQuantidades() throws Exception;
    
}
