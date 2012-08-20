package ono.leo.com.jvcontroller.bean.access;

import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BeanInstanceELAccessorTest {
    
    private String teste = "TESTE";
    private BeanInstanceELAccessorTest app = new BeanInstanceELAccessorTest();
    private List<BeanInstanceELAccessorTest> apps = null;

    public BeanInstanceELAccessorTest() {
        this.app.app.setApp(null);
    }
    
    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public BeanInstanceELAccessorTest getApp() {
        return app;
    }

    public void setApp(BeanInstanceELAccessorTest app) {
        this.app = app;
    }

    public List<BeanInstanceELAccessorTest> getApps() {
        return apps;
    }

    public void setApps(List<BeanInstanceELAccessorTest> apps) {
        this.apps = apps;
    }

    public static void main( String[] args ) throws Exception {
        /*
        BeanSingleClassAccessor bsca = new BeanSingleClassAccessor();
        System.out.println("tem setter property teste ? " + bsca.hasSetter(App.class, "teste"));
        System.out.println("tem getter property teste ? " + bsca.hasGetter(App.class, "teste"));
        System.out.println("tem setter property teste2 ? " + bsca.hasSetter(App.class, "teste2"));
        System.out.println("tem getter property teste2 ? " + bsca.hasGetter(App.class, "teste2"));
        System.out.println("tem method property setTeste ? " + bsca.hasMethod(App.class, "setTeste"));
        System.out.println("tem method property getTeste ? " + bsca.hasMethod(App.class, "getTeste"));
        System.out.println("tem method property getTeste1 ? " + bsca.hasMethod(App.class, "getTeste1"));
        System.out.println("tem method property setTeste1 ? " + bsca.hasMethod(App.class, "setTeste1"));
         * 
         */
        
        /*
        BeanClassNestedAccessor bnca = BeanClassNestedAccessor.getInstance();
        BeanInstanceNestedAccessor bina = new BeanInstanceNestedAccessor();
        System.out.println("tem setter property app.teste ? " + bnca.hasSetter(App.class, "app.app.teste"));
        System.out.println("tem getter property app.teste ? " + bnca.hasGetter(App.class, "app.app.teste"));
        System.out.println("tem method app.setTeste ? " + bnca.hasMethod(App.class, "app.app.app.app.app.setTeste"));
        System.out.println("tem method app.getTeste ? " + bnca.hasMethod(App.class, "app.app.getTeste"));
        System.out.println("tem method app.getType ? " + bnca.getType(App.class, "app.app.teste"));
        System.out.println("tem method app.getCollectionType ? " + bnca.getGenericType(App.class, "app.app.app.app.app.app.apps"));
         * 
         */
        //System.out.println("tem method app.getCollectionType ? " + bina.get(new App(), "app.app.app.app.app.app.apps"));
        
        /*
        A a = new A();
        bina.set(a, "b.c", new C());
        bina.set(a, "b.c.endereco", "alterou");
        
        System.out.println(bina.get(a, "b.c.endereco"));
     
        try {
            bina.invoke(a, "b.c.acao");
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
         * 
         */
        
        BeanInstanceELAccessor ba = BeanInstanceELAccessor.getInstance();
/*
        ba.addBean("a", new A());
        ba.set("a.b.c", new C());
        ba.set("a.b.c.endereco", "RUA XXX");
        System.out.println(ba.get("a.b.c.endereco"));
        System.out.println(ba.getType("a.b.c.endereco"));
        System.out.println(ba.getGenericType("a.b.c.enderecos"));
        // System.out.println(ba.invoke("a.b.c.acao"));
        
        System.out.println("hasGetter: " + ba.hasGetter("a.b.c.endereco"));
        
        String r = (String) ba.evaluate("a.b.c.acao(a.b.c.endereco, a.b.c.acao( a.b.c.acao('','').getEndereco().toLowerCase() ,'').getEndereco().toString() ).getEndereco().toLowerCase().toString()");
        System.out.println("r=" + r);
 * 
 */
        ba.addBean("a", new A());
        ba.addBean("c", new C());
        
        // dynamic properties
        
        //ba.set("a.teste", "LEONARDO");
        ba.set("a.b.c.mensagem", "TESTE MENSAGEM EM LETRA MAIUSCULA !");
        ba.set("a.b.data", new Date());
        List<C> listC = new ArrayList<C>();
        ba.set("a.b.c.list", listC);
        
        
        
        System.out.println("data=" + ba.evaluate("a.b.data.getTime()"));
        System.out.println("date type=" + ba.getType("a.b"));
        System.out.println("date type=" + ba.getType("a.b.data"));
        System.out.println("long type=" + ba.getType("a.b.data.time"));
        System.out.println("has getter=" + ba.hasGetter("a.b.data.time"));
        System.out.println("has setter=" + ba.hasSetter("a.b.data.time"));
        System.out.println("has method=" + ba.hasMethod("a.b.data.getTime"));
        System.out.println("has method=" + ba.hasMethod("a.b.data.setTime"));
        System.out.println("invoke setTime=" + ba.invoke("a.b.data.setTime", 1234));
        System.out.println("invoke getTime=" + ba.invoke("a.b.data.getTime"));
        System.out.println("");
        System.out.println("list=" + ba.getType("a.b.c.list"));
        
        // TODO
        // Get generic type from dynamic property
        // System.out.println("generic type=" + ba.getGenericType("a.b.list"));
        
        System.out.println("");
        
        // TODO
        // Get the correct method when it's overloaded
        System.out.println("evaluate=" + ba.evaluate("a.b.c.list.add(c)"));
        
        System.out.println("list item =" + listC.get(0));
        System.out.println("mensagem =" + ba.evaluate("a.b.c.mensagem.toString().toLowerCase()"));
        System.out.println("");

        System.out.println("");
        ba.set("a.c", ba.get("c"));
        System.out.println("a.c=" + ba.evaluate("a.c"));
        System.out.println("");
        
        ba.set("a.va", "3");
        ba.set("a.vb", "5");
        ba.set("a.vr", ba.evaluate("a.b.c.somar( a.b.c.converter( a.va ), a.b.c.converter( a.vb ) )"));
        System.out.println("resultado=" + ba.evaluate("a.vr"));

    }
    
}
