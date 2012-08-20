package ono.leo.com.jvcontroller.test.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Class BView.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 15:21)
 */
public class AView {

    private String a;
    private String b;
    private List<BView> bviews = new ArrayList<BView>();

    public AView() {
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

    public List<BView> getBviews() {
        return bviews;
    }

    public void setBviews(List<BView> bviews) {
        this.bviews = bviews;
    }

    @Override
    public String toString() {
        return "AView{" + "a=" + a + ", b=" + b + ", bviews=" + bviews + '}';
    }

    
}
