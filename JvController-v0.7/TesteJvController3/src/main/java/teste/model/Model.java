package teste.model;

/**
 *
 * @author leo
 */
public class Model {

    private Cliente cliente = new Cliente();

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void adicionarEndereco() {
        cliente.getEnderecos().add(new Endereco());
    }

    public void removerEndereco() {
        cliente.getEnderecos().remove(0);
    }
    
    public void excluir(Endereco endereco) {
        cliente.getEnderecos().remove(endereco);
    }

    @Override
    public String toString() {
        return "Model{" + "cliente=" + cliente + '}';
    }
    
}
