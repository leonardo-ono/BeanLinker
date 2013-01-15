package br.beanlinker.core;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Bean linking unit tests.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (06/01/2013 12:35)
 */
public class BeanLinkingTest {

    // ---------------------------------------------------------------------------
    // Test classes to simulate:
    //
    // Model.nome <---> View.textNome.text
    // Model.a <---> View.aView
    //
    // A.a = AView.textA.text
    // B.b = BView.textB.text
    // ---------------------------------------------------------------------------
    
    public static class Model {
        
        private String name = "Model.name";
        private A a = new A();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "Model{" + "name=" + name + ", a=" + a + '}';
        }

    }
    
    public static class A {
        
        private String a = "A.a";
        private B b = new B();
        
        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "A{" + "a=" + a + ", b=" + b + '}';
        }
        
    }
    
    public static class B {

        private String b = "B.b";

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
        private AView aView = new AView();

        public TextView getTextName() {
            return textName;
        }

        public void setTextName(TextView textName) {
            this.textName = textName;
        }

        public AView getaView() {
            return aView;
        }

        public void setaView(AView aView) {
            this.aView = aView;
        }

        @Override
        public String toString() {
            return "View{" + "textName=" + textName + ", aView=" + aView + '}';
        }
        
    }
    
    public static class TextView {

        private String text = "Text.text";

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
        private BView bView = new BView();

        public TextView getTextA() {
            return textA;
        }

        public void setTextA(TextView textA) {
            this.textA = textA;
        }

        public BView getbView() {
            return bView;
        }

        public void setbView(BView bView) {
            this.bView = bView;
        }

        @Override
        public String toString() {
            return "AView{" + "textA=" + textA + ", bView=" + bView + '}';
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

    // Model.nome <---> View.textNome.text
    // Model.a <---> View.aView
    //
    // A.a = AView.textA.text
    // B.b = BView.textB.text    
    @Test
    public void testLinkClassPropertyToClassProperty() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.BeanLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.BeanLinkingTest$View");
        beanLinker.registerClass("A", "br.beanlinker.core.BeanLinkingTest$A");
        beanLinker.registerClass("B", "br.beanlinker.core.BeanLinkingTest$B");
        beanLinker.registerClass("AView", "br.beanlinker.core.BeanLinkingTest$AView");
        beanLinker.registerClass("BView", "br.beanlinker.core.BeanLinkingTest$BView");
        beanLinker.createAllNewInstances();
        
        beanLinker.linkProperty("Model.name", "View.textName.text", "", "", "", "");
        beanLinker.linkBean("Model.a", "View.aView"); // <---

        beanLinker.linkProperty("A.a", "AView.textA.text", "", "", "", "");
        beanLinker.linkBean("A.b", "AView.bView"); // <---

        beanLinker.linkProperty("B.b", "BView.textB.text", "", "", "", "");
        
        beanLinker.eval("model.name = 'abcde12345'; ");
        beanLinker.update("view", "model");
        
        System.out.println(beanLinker.eval("model"));
        System.out.println(beanLinker.eval("view"));

        assertEquals("Model{name=Text.text, a=A{a=Text.text, b=B{b=Text.text}}}", beanLinker.eval("model").toString());
        assertEquals("View{textName=Text{text=Text.text}, aView=AView{textA=Text{text=Text.text}, bView=BView{TextB=Text{text=Text.text}}}}", beanLinker.eval("view").toString());
    }

    @Test
    public void testLinkClassPropertyToClassProperty2() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.BeanLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.BeanLinkingTest$View");
        beanLinker.registerClass("A", "br.beanlinker.core.BeanLinkingTest$A");
        beanLinker.registerClass("B", "br.beanlinker.core.BeanLinkingTest$B");
        beanLinker.registerClass("AView", "br.beanlinker.core.BeanLinkingTest$AView");
        beanLinker.registerClass("BView", "br.beanlinker.core.BeanLinkingTest$BView");
        beanLinker.createAllNewInstances();
        
        beanLinker.linkProperty("Model.name", "View.textName.text", "", "", "", "");
        beanLinker.linkBean("Model.a", "View.aView"); // <---

        beanLinker.linkProperty("A.a", "AView.textA.text", "", "", "", "");
        beanLinker.linkBean("A.b", "AView.bView"); // <---

        beanLinker.linkProperty("B.b", "BView.textB.text", "", "", "", "");
        
        beanLinker.eval("model.name = 'abcde12345'; ");
        beanLinker.update("model", "view");
        
        System.out.println(beanLinker.eval("model"));
        System.out.println(beanLinker.eval("view"));
        
        assertEquals("Model{name=abcde12345, a=A{a=A.a, b=B{b=B.b}}}", beanLinker.eval("model").toString());
        assertEquals("View{textName=Text{text=abcde12345}, aView=AView{textA=Text{text=A.a}, bView=BView{TextB=Text{text=B.b}}}}", beanLinker.eval("view").toString());
    }

    // Se fizer isso, avisar o programador lancando uma excecao ?
        //linker.linkBean("Model.telefone", "View.telefoneView");
        //linker.linkBean("View.telefoneView", "Model.telefone");    

    // TODO updateId generator
    
}
