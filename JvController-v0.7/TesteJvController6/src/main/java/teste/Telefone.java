package teste;

/**
 *
 * @author leonardo
 */
public class Telefone {

    private long id = System.currentTimeMillis();
    private String nome = "LEO";
    private String ddd = "011";
    private String numero = "55622004";

    public Telefone() {
    }

    public Telefone(String nome, String ddd, String numero) {
        this.nome = nome;
        this.ddd = ddd;
        this.numero = numero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefone{" + "nome=" + nome + ", ddd=" + ddd + ", numero=" + numero + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Objeto " + this + " vai ser destruido ...");
        super.finalize();
    }
    
}
