package teste.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leo
 */
public class Cliente {

    private String nome = "LEONARDO";
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", enderecos=" + enderecos + '}';
    }
    
}
