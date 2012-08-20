/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ono.leo.com.jvcontroller.bean.access;

import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class C {
    
    private String endereco = "C.ENDERECO";
    private List<String> enderecos;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        // throw new RuntimeException("setEndereco");
    }

    public C acao(String arg1, String arg2) throws FileNotFoundException {
        System.out.println("acao ... arg1=" + arg1 + " arg2=" + arg2);
        return this;
    }

    public List<String> getEnderecos() {
        return enderecos;
    }
    
    public Integer converter(String x) {
        return Integer.valueOf(x);
    }
    
    public Integer somar(int a, int b) {
        return a + b;
    }
    
}
