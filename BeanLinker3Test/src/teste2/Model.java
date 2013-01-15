package teste2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leo
 */
public class Model {

    private Telefone telefone = new Telefone();
    private List<Telefone> telefones = new ArrayList<Telefone>();
    private Color whiteColor = Color.WHITE;
    private Color yellowColor = Color.YELLOW;
    
    public Model() {
        criarTelefones();
    }

    public Color getWhiteColor() {
        return whiteColor;
    }

    public Color getYellowColor() {
        return yellowColor;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void limparTelefones() {
        telefones = new ArrayList<Telefone>();
    }

    public void excluirTelefone(Telefone telefone) {
        telefones.remove(telefone);
    }
    
    public void adicionarTelefone(Telefone telefone) {
        // limparTelefones();
        
        telefones.add(telefone);
        criarTelefones();
    }

    public final void criarTelefones() {
        for (int i = 0; i < 10; i++) {

            telefone = new Telefone();
            telefone.setDdd("11_" + i);
            telefone.setNumero("" + i);
            telefone.setTipo("Residencial");
            telefones.add(telefone);

        }
    }
    
    @Override
    public String toString() {
        return "Model{" + "telefone=" + telefone + ", telefones=" + telefones + '}';
    }
    
    public String getNumeroRandomico() {
        return "" + (Math.random() * 99999);
    }
    
    public Produto gerarProdutoAleatorio() {
        Produto produto = new Produto();
        produto.setCodigo((int) (Math.random() * 999999));
        produto.setDescricao("descricao_" + Math.random() * 9999);
        return produto;
    }
    
}
