package model;

/**
 *
 * @author leo
 */
public class Endereco {

    private String endereco = "RUA XXX";
    private int numero = 83;
    private String bairro = "CIDADE ADEMAR";
    private String cidade = "SAO PAULO";

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Endereco{" + "endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + '}';
    }
    
    
}
