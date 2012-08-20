package ono.leo.com.jvcontroller.test.view;

/**
 * Class BView.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 15:21)
 */
public class BView {

    private String a;
    private String b;
    private CView cview = new CView();

    public BView() {
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

    public CView getCview() {
        return cview;
    }

    public void setCview(CView cview) {
        this.cview = cview;
    }

    @Override
    public String toString() {
        return "BView{" + "a=" + a + ", b=" + b + ", cview=" + cview + '}';
    }

    
}
