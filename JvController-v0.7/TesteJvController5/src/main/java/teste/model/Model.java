package teste.model;

/**
 *
 * @author leonardo
 */
public class Model {

    private Cliente cliente = new Cliente();

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void adicionarTelefone() {
        cliente.getTelefones().add(new Telefone());
    }

    public void removerTelefone(Telefone telefone) {
        cliente.getTelefones().remove(telefone);
    }
    
}
