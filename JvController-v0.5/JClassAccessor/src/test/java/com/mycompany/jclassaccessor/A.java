/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jclassaccessor;

/**
 *
 * @author leonardo
 */
public class A {
    
    private String teste = "teste";
    private B b = new B();
    
    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
    
    
}
