package ono.leo.com.jvcontroller.test.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class A without non argument constructor.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 13:31)
 */
public class A {

    private int a;
    private String b;
    private List<B> bs = new ArrayList<B>();
    
    // please do not modify or test will fail.
    // remover o contrustor sem argumentos apos o teste do bindCollection
    public A() {
        bs.add(new B());
     }

    public A(int a) {
        this.a = a;
    }

    public A(String b) {
        this.b = b;
    }
    
    public A(int a, String b) {
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

    public List<B> getBs() {
        return bs;
    }

    public void setBs(List<B> bs) {
        this.bs = bs;
    }

    @Override
    public String toString() {
        return "A{" + "a=" + a + ", b=" + b + ", bs=" + bs + '}';
    }
    
}
