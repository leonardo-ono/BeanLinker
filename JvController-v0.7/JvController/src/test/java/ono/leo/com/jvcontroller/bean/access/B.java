/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ono.leo.com.jvcontroller.bean.access;

/**
 *
 * @author leonardo
 */
public class B {
    
    private String nome = "teste";
    private C c = new C();
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
    
}
