package ono.leo.com.jvcontroller.test.view;

/**
 * Class CView..
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 15:21)
 */
public class CView {

    private String a;
    private String b;

    public CView() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
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
        return "CView{" + "a=" + a + ", b=" + b + '}';
    }
    
}
