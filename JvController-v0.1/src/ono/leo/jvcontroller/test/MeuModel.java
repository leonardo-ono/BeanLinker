/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ono.leo.jvcontroller.test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leo
 */
public class MeuModel {
    
    private List<String> list = new ArrayList<String>();
    private String valor = "";

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void adicionarNaLista() {
        System.out.println("adicionando na lista " + valor);
        list.add(valor);
        valor = "";
    }
    
}
