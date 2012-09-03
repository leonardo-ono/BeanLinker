package teste;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class Model {

    private List<Telefone> telefones = new ArrayList<Telefone>();

    public Model() {
        telefones.add(new Telefone());
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void addTelefone(String nome, String ddd, String numero) {
        telefones.add(new Telefone(nome, ddd, numero));
    }
    
    public void removeTelefone(Telefone telefone) {
        telefones.remove(telefone);
    }
    
    @Override
    public String toString() {
        return "Model{" + "telefones=" + telefones + '}';
    }
    
}
