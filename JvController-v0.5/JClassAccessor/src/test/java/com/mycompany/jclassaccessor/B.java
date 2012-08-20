/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jclassaccessor;

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
