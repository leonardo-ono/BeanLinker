package jbean;

/**
 *
 * @author leo
 */
public class ObjB {

    private String varStringObj = "TESTE";
    private ObjA objA = new ObjA();

    public ObjA getObjA() {
        return objA;
    }

    public void setObjA(ObjA objA) {
        this.objA = objA;
    }

    public String getVarStringObj() {
        return varStringObj;
    }

    public void setVarStringObj(String varStringObj) {
        this.varStringObj = varStringObj;
    }

    public Object acao(Object arg1, Object arg2) {
        System.out.println("chamou acao() ... " + arg1 + " / " + arg2);
        return arg1;
    }
    
    @Override
    public String toString() {
        return "ObjB{" + "objA=" + objA + '}';
    }

}
