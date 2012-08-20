package model;

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

    @Override
    public String toString() {
        return "Model{" + "cliente=" + cliente + '}';
    }
    
}
