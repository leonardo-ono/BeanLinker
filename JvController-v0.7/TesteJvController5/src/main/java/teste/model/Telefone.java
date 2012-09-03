/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.model;

/**
 *
 * @author leonardo
 */
public class Telefone {
    
    private String ddd = "011";
    private String numero = "556220040";

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

    @Override
    public String toString() {
        return "Telefone{" + "ddd=" + ddd + ", numero=" + numero + '}';
    }
    
    
}
