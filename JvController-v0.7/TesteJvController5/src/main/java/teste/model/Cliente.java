package teste.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class Cliente {

    private String nome = "LEONARDO";
    private int idade = 20;
    private List<Telefone> telefones = new ArrayList<Telefone>();

    public Cliente() {
        telefones.add(new Telefone());
    }
    
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", idade=" + idade + ", telefones=" + telefones + '}';
    }

    
}
