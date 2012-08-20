package ono.leo.com.jvcontroller.test.model;

/**
 * Class C with non argument constructor and others.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 13:31)
 */
public class C {

    private int a;
    private String b;

    public C() {
    }

    public C(int a, String b) {
        this.a = a;
        this.b = b;
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

    @Override
    public String toString() {
        return "C{" + "a=" + a + ", b=" + b + '}';
    }
    
}
