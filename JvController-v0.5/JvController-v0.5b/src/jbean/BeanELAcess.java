package jbean;

import classFinder.ClassFinder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BeanELAcess.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class BeanELAcess extends BeanContextAcess {
    
    private static BeanELAcess instance;
    
    public void startApp() {
        try {
            ClassFinder.main(null);
            ClassFinder.invokeStartMethod();
        } catch (IOException ex) {
            Logger.getLogger(BeanELAcess.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }
    public synchronized static BeanELAcess getInstance() {
        if (instance == null) {
            instance = new BeanELAcess();
        }
        return instance;
    }
    
    // falta poder acessar atraves de "aaa.bbb.funcao(xxx, yyy).outraFuncao()" ???
    
    public void getAsModel(String retVar, String el, String conversor, String validator) throws Exception {
        Object ret = BeanELAcess.getInstance().evaluate(conversor + ".getAsModel(" + el + ")");
        BeanELAcess.getInstance().addBean(retVar, ret);
    }

    public void getAsView(String retVar, String el, String conversor, String validator) throws Exception {
        Object ret = BeanELAcess.getInstance().evaluate(conversor + ".getAsView(" + el + ")");
        BeanELAcess.getInstance().addBean(retVar, ret);
    }
    
    public Object evaluate(String el) throws Exception {
        el = el.trim();

        
        int methodPosIni = el.indexOf("(");
        // existe metodo ?
        if (methodPosIni > 0) {
            int methodPosFin = el.lastIndexOf(")");
            if (methodPosFin < 0) {
                throw new Exception("Invalid expression !");
            }
            String methodName = el.substring(0, methodPosIni).trim();
            String methodParams = el.substring(methodPosIni + 1, methodPosFin);
            
            //System.out.println("method: " + methodName + " / params=" + methodParams);
            
            String params[] = splitMethodParameters(methodParams);
            List<Object> objParams = new ArrayList<Object>();
            for (String param : params) {
                //System.out.println("param=" + param);
                objParams.add(evaluate(param.trim()));
            }
            return invoke(methodName, objParams.toArray());
        }
        // senao so pode ser propriedade
        else {
            // Literal boolean true
            if (el.toLowerCase().equals("true")) {
                return true;
            }
            
            // Literal boolean false
            if (el.toLowerCase().equals("false")) {
                return false;
            }
            
            // Literal numerico fracionario ?
            if (Character.isDigit(el.charAt(0)) && el.contains(".")) {
                return Double.valueOf(el);
            }

            // Literal numerico inteiro ?
            if (Character.isDigit(el.charAt(0))) {
                return Integer.valueOf(el);
            }
            
            // Literal string
            if (el.charAt(0)=='\'' && el.charAt(el.length()-1)=='\'') {
                return el.substring(1, el.length()-1);
            }
            
            // Property
            return get(el);
        }
    }
    
    private String handleMultiply (String value) {
        List<String> strParams = new ArrayList<String>();
        String param = "";
        int index = -1;
        for (int i=0; i<value.length()-1; i++) {
            if (value.charAt(i) == '+' || value.charAt(i) == '-' || value.charAt(i) == '*' || value.charAt(i) == '/') {
                strParams.add(value.charAt(i) + "");
                if (value.charAt(i) == '*' && index < 0) {
                    index = strParams.size() - 1;
                }
                if (param.length() > 0) {
                    strParams.add(param);
                    param = "";
                }
            }
            param += value.charAt(i);
        }
        for (String strParam : strParams) {
            if (strParam.equals("*")) {
                
            }
        }
        return null;
    }
    
    private String[] splitMethodParameters(String value) {
        String paramComplete = null;
        String[] params = value.split(",");
        List<String> strParams = new ArrayList<String>();
        for (String param : params) {
            if (paramComplete == null) {
                paramComplete = param;
            }
            else {
                paramComplete = paramComplete + "," + param;
            }
            //System.out.println("paramComplete=" + paramComplete);
            if (paramComplete.replace("(", "").length() 
                    == paramComplete.replace(")", "").length()) {
                
                strParams.add(paramComplete);
                paramComplete = null;
            }
        }
        return strParams.toArray(new String[0]);
    }
    
}
