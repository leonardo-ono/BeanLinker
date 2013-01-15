package br.beanlinker.core;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Bean linking unit tests.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (06/01/2013 12:35)
 */
public class CollectionLinkingTest {

    // ---------------------------------------------------------------------------
    // Test classes to simulate:
    //
    // Model.as <---> View.aViews
    // A.bs <---> AView.bViews
    // ---------------------------------------------------------------------------
    
    public static class Model {
        
        private String name = "Model.name_" + ((int) Math.random() * 999999);
        private List<A> as = new ArrayList<A>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<A> getAs() {
            return as;
        }

        @Override
        public String toString() {
            return "Model{" + "name=" + name + ", as=" + as + '}';
        }

    }
    
    public static class A {
        
        private String a = "A.a_" + ((int) (Math.random() * 999999));
        private List<B> bs = new ArrayList<B>();
        
        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public List<B> getBs() {
            return bs;
        }

        @Override
        public String toString() {
            return "A{" + "a=" + a + ", bs=" + bs + '}';
        }

    }
    
    public static class B {

        private String b = "B.b_" + ((int) (Math.random() * 999999));

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "B{" + "b=" + b + '}';
        }
        
    }
    
    public static class View {

        private TextView textName = new TextView();
        private List<AView> aViews = new ArrayList<AView>();

        public TextView getTextName() {
            return textName;
        }

        public void setTextName(TextView textName) {
            this.textName = textName;
        }

        public List<AView> getaViews() {
            return aViews;
        }

        @Override
        public String toString() {
            return "View{" + "textName=" + textName + ", aViews=" + aViews + '}';
        }
        
    }
    
    public static class TextView {

        private String text = "Text.text_" + ((int) (Math.random() * 999999));

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Text{" + "text=" + text + '}';
        }
        
    }
    
    public static class AView {
        
        private TextView textA = new TextView();
        private List<BView> bViews = new ArrayList<BView>();

        public TextView getTextA() {
            return textA;
        }

        public void setTextA(TextView textA) {
            this.textA = textA;
        }

        public List<BView> getbViews() {
            return bViews;
        }

        @Override
        public String toString() {
            return "AView{" + "textA=" + textA + ", bViews=" + bViews + '}';
        }
        
    }
    
    public static class BView {

        private TextView TextB = new TextView();

        public TextView getTextB() {
            return TextB;
        }

        public void setTextB(TextView TextB) {
            this.TextB = TextB;
        }

        @Override
        public String toString() {
            return "BView{" + "TextB=" + TextB + '}';
        }
        
    }
    
    // ---------------------------------------------------------------------------
    
    private BeanLinker beanLinker;
    
    @Before
    public void init() {
        beanLinker = new BeanLinkerImpl();
    }

    // ---------------------------------------------------------------------------
    // Test classes to simulate:
    //
    // Model.as <---> View.aViews
    // A.bs <---> AView.bViews
    // ---------------------------------------------------------------------------
    
    @Test
    public void testLinkClassCollectionPropertyToClassCollectionProperty() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.CollectionLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.CollectionLinkingTest$View");
        beanLinker.registerClass("A", "br.beanlinker.core.CollectionLinkingTest$A");
        beanLinker.registerClass("B", "br.beanlinker.core.CollectionLinkingTest$B");
        beanLinker.registerClass("AView", "br.beanlinker.core.CollectionLinkingTest$AView");
        beanLinker.registerClass("BView", "br.beanlinker.core.CollectionLinkingTest$BView");
        beanLinker.createAllNewInstances();
        
        beanLinker.linkProperty("Model.name", "View.textName.text", "", "", "", "");
        beanLinker.linkCollection("Model.as", "View.aViews", "AView"); // <---

        beanLinker.linkProperty("A.a", "AView.textA.text", "", "", "", "");
        beanLinker.linkCollection("A.bs", "AView.bViews", "BView"); // <---

        beanLinker.linkProperty("B.b", "BView.textB.text", "", "", "", "");
        
        List<A> as = (List<A>) beanLinker.eval("model.as");
        for (int i=0; i<1; i++) {
            A a = new A();
            a.bs.add(new B());
            a.bs.add(new B());
            a.bs.add(new B());
            as.add(a);
        }
        
        beanLinker.update("model", "view");
        
        System.out.println(beanLinker.eval("model"));
        System.out.println(beanLinker.eval("view"));

    }


    @Test
    public void testLinkInstanceMethodToClassPropertyWhenItemCollection() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.CollectionLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.CollectionLinkingTest$View");
        beanLinker.registerClass("A", "br.beanlinker.core.CollectionLinkingTest$A");
        beanLinker.registerClass("B", "br.beanlinker.core.CollectionLinkingTest$B");
        beanLinker.registerClass("AView", "br.beanlinker.core.CollectionLinkingTest$AView");
        beanLinker.registerClass("BView", "br.beanlinker.core.CollectionLinkingTest$BView");
        beanLinker.createAllNewInstances();
        
        beanLinker.linkProperty("Model.name", "View.textName.text", "", "", "", "");
        beanLinker.linkCollection("Model.as", "View.aViews", "AView"); 

        beanLinker.linkProperty("A.a", "AView.textA.text", "", "", "", "");
        beanLinker.linkCollection("A.bs", "AView.bViews", "BView"); 

        // Cuidado para nao substituir "model" pelo "collSourceItem"
        beanLinker.linkProperty("model.getName()", "BView.textB.text", "", "", "", ""); // <---
        
        List<A> as = (List<A>) beanLinker.eval("model.as");
        for (int i=0; i<100; i++) {
            A a = new A();
            a.bs.add(new B());
            a.bs.add(new B());
            a.bs.add(new B());
            as.add(a);
        }
        
        beanLinker.update("model", "view");
        
        System.out.println(beanLinker.eval("model"));
        System.out.println(beanLinker.eval("view"));
    }

    // TODO updateId generator
    
}
