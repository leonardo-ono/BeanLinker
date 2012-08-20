package jbean2.model;

import jbean2.annotation.Model;

/**
 *
 * @author leo
 */
@Model("Endereco")
public class Endereco {
    
    private String logradouro = "RUA XXX";
    private int numero = 123;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Endereco{" + "logradouro=" + logradouro + ", numero=" + numero + '}';
    }
    
}
