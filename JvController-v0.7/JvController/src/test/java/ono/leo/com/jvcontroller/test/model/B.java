package ono.leo.com.jvcontroller.test.model;

/**
 * Class B with non argument constructor.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 13:31)
 */
public class B {

    private int a = (int) System.currentTimeMillis();
    private String b = "" + System.currentTimeMillis();
    private C c = new C();
    
    public B() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "B{" + "a=" + a + ", b=" + b + ", c=" + c + '}';
    }
    
}
