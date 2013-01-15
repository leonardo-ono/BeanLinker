package teste2;

/**
 *
 * @author leo
 */
public class Telefone {

    private String tipo = "tipo";
    private String ddd = "ddd";
    private String numero = "numero";
    
    private Produto produto = new Produto();
    
    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Telefone{" + "tipo=" + tipo + ", ddd=" + ddd + ", numero=" + numero + ", produto=" + produto + '}';
    }

}
