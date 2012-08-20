package ono.leo.com.jvcontroller.bean.access;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanInstanceELAccessor.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class BeanInstanceELAccessor extends BeanInstanceContextAccessor {

    private static BeanInstanceELAccessor instance;

    public synchronized static BeanInstanceELAccessor getInstance() {
        if (instance == null) {
            instance = new BeanInstanceELAccessor();
        }
        return instance;
    }

    public Object evaluate(String el) throws Exception {
        el = el.trim();
        
        if (el == null) {
            return null;
        }
        if (el.length() == 0) {
            return "";
        }
        if (!Character.isLetter(el.charAt(0)) 
                && el.charAt(0) != '\'' && el.charAt(0) != '#') {
            
            throw new Exception("Invalid expression \"" + el + "\" !");
        }

        int methodPosIni = el.indexOf("(");
        // is method ?
        if (methodPosIni > 0) {
            checkParenthesis(el);
            int methodPosFin = getMethodClose(el, methodPosIni);
            if (methodPosFin < 0) {
                throw new Exception("Invalid expression \"" + el + "\" !");
            }
            String methodName = el.substring(0, methodPosIni).trim();
            String methodParams = el.substring(methodPosIni + 1, methodPosFin);
            String nestedMethods = el.substring(methodPosFin + 1, el.length());
            nestedMethods = nestedMethods.trim();
            if ( (nestedMethods.length() == 1) 
                    || ( nestedMethods.length() > 1 
                    && nestedMethods.charAt(0) != '.') 
                    || ( nestedMethods.length() > 1 
                    && !Character.isLetter(nestedMethods.charAt(1)) ) ) {
                
                throw new Exception("Invalid expression \"" + el + "\" !");
            }

            //System.out.println("method: " + methodName 
            // + " / params=" + methodParams);

            String params[] = splitMethodParameters(methodParams.trim());
            List<Object> objParams = new ArrayList<Object>();
            for (String param : params) {
                //System.out.println("param=" + param);
                objParams.add(evaluate(param.trim()));
            }
            
            if (nestedMethods.length() == 0) {
                return invoke(methodName, objParams.toArray());
            }
            else {
                Object ret = invoke(methodName, objParams.toArray());
                if (getBeans().containsKey("#prov")) {
                    getBeans().remove("#prov");
                }
                getBeans().put("#prov", ret);
                ret = evaluate("#prov" + nestedMethods);
                if (getBeans().containsKey("#prov")) {
                    getBeans().remove("#prov");
                }
                return ret;
            }
        } 
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
            if (el.length() > 0 && Character.isDigit(el.charAt(0)) 
                    && el.contains(".")) {
                
                return Double.valueOf(el);
            }

            // Literal numerico inteiro ?
            if (el.length() > 0 && Character.isDigit(el.charAt(0))) {
                return Integer.valueOf(el);
            }

            // Literal string
            if (el.length() > 0 && el.charAt(0) == '\'' 
                    && el.charAt(el.length() - 1) == '\'') {
                
                return el.substring(1, el.length() - 1);
            }

            // Property
            return get(el);
        }
    }

    private void checkParenthesis(String el) throws Exception {
        int cIni = el.length() - el.replace("(", "").length();
        int cFin = el.length() - el.replace(")", "").length();
        if (cIni != cFin) {
            throw new Exception("Invalid expression \"" + el 
                    + "\", missing parenthesis !");
        }
    }
    
    private int getMethodClose(String el, int methodPosIni) {
        int c = 0;
        for (int i=methodPosIni; i<el.length(); i++) {
            if (el.charAt(i) == '(') {
                c++;
            }
            if (el.charAt(i) == ')') {
                c--;
                if (c == 0) {
                    c = i;
                    break;
                }
            }
        }
        return c;
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
