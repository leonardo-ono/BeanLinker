/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leonardo
 */
public class Teste {

    public static void main(String[] args) throws Exception {
        Map<String, Telefone> mapa = new HashMap<String, Telefone>();
        
        Telefone t = new Telefone();
        
        mapa.put("1", new Telefone());
        mapa.put("2", new Telefone());
        mapa.put("3", t);
        mapa.put("4", new Telefone());

        mapa.values().remove(t);
        t = null;
        for (String key : mapa.keySet()) {
            System.out.println("key=" + key + " value=" + mapa.get(key));
        }
        
        System.gc();
        
        Thread.sleep(10000);
    }
}
