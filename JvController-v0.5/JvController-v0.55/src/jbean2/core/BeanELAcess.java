package jbean2.core;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanELAcess.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class BeanELAcess extends BeanContextAcess {

    private static BeanELAcess instance;

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

            String params[] = splitMethodParameters(methodParams.trim());
            List<Object> objParams = new ArrayList<Object>();
            for (String param : params) {
                //System.out.println("param=" + param);
                objParams.add(evaluate(param.trim()));
            }
            return invoke(methodName, objParams.toArray());
        } // senao so pode ser propriedade
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
            if (el.length() > 0 && Character.isDigit(el.charAt(0)) && el.contains(".")) {
                return Double.valueOf(el);
            }

            // Literal numerico inteiro ?
            if (el.length() > 0 && Character.isDigit(el.charAt(0))) {
                return Integer.valueOf(el);
            }

            // Literal string
            if (el.length() > 0 && el.charAt(0) == '\'' && el.charAt(el.length() - 1) == '\'') {
                return el.substring(1, el.length() - 1);
            }

            // Property
            return get(el);
        }
    }

    private String[] splitMethodParameters(String value) {
        String paramComplete = null;
        String[] params = value.split(",");
        List<String> strParams = new ArrayList<String>();
        for (String param : params) {
            if (paramComplete == null) {
                paramComplete = param;
            } else {
                paramComplete = paramComplete + "," + param;
            }
            //System.out.println("paramComplete=" + paramComplete);
            if (paramComplete.replace("(", "").length()
                    == paramComplete.replace(")", "").length()) {

                if (paramComplete.trim().length() > 0) {
                    strParams.add(paramComplete);
                }
                paramComplete = null;
            }
        }
        return strParams.toArray(new String[0]);
    }
}
