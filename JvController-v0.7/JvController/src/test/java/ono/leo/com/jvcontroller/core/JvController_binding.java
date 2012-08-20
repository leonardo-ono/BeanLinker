package ono.leo.com.jvcontroller.core;

import java.util.List;
import ono.leo.com.jvcontroller.test.view.BView;
import ono.leo.jvcontroller.core.JvController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test implementation of JvControllerImplTest.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 15:19)
 */public class JvController_binding {
    
    public JvController_binding() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //@Test
    public void testBindProperty() throws Exception {
        JvController jc = JvController.getInstance();
        
        // Register model classes
        jc.registerModelClass("B", "ono.leo.com.jvcontroller.test.model.B");
        jc.registerModelClass("C", "ono.leo.com.jvcontroller.test.model.C");

        // Register view classes
        jc.registerViewClass("BView", "ono.leo.com.jvcontroller.test.view.BView");
        jc.registerViewClass("CView", "ono.leo.com.jvcontroller.test.view.CView");

        // Register conversor classes
        jc.registerViewClass("IntegerConversor", "ono.leo.com.jvcontroller.test.conversor.IntegerConversor");
        
        // Bind between classes
        jc.bindClassProperty("BView.a", "B.a", "conversor", "validator");
        jc.bindClassProperty("BView.b", "B.b", "conversor", "validator");
        
        // Create new instances
        jc.newInstance("bView", "BView");
        jc.newInstance("bModel", "B");
        jc.newInstance("conversor", "IntegerConversor");
        
        // Set values to model
        jc.getELContext().set("bModel.a", 99);
        jc.getELContext().set("bModel.b", "ALTEROU");
        
        // Bind between instances
        // jc.bindInstanceProperty("bView.a", "bModel.a", "conversor", "");
        // jc.bindInstanceProperty("bView.b", "bModel.b", "", "");
        jc.bindInstanceProperty("bView.a", "bView.setA(am)", "bModel.setA(av)", "bModel.a", "conversor", "", "av", "am");

        // Update view
        jc.updateView();
        System.out.println(jc.getELContext().evaluate("bView"));

        // Set values to view
        jc.getELContext().set("bView.a", "111");
        jc.getELContext().set("bView.b", "XXX");

        // Update model
        jc.updateModel();
        System.out.println(jc.getELContext().evaluate("bModel"));
        
    }

    //@Test
    public void testBindBean() throws Exception {
        JvController jc = JvController.getInstance();
        
        jc.registerModelClass("B", "ono.leo.com.jvcontroller.test.model.B");
        jc.registerViewClass("BView", "ono.leo.com.jvcontroller.test.view.BView");
        jc.registerViewClass("CView", "ono.leo.com.jvcontroller.test.view.CView");
        jc.registerConversorClass("IntegerConversor", "ono.leo.com.jvcontroller.test.conversor.IntegerConversor");
        
        // Bind between classes
        jc.bindClassProperty("BView.a", "B.a", "conversor", "");
        jc.bindClassProperty("BView.b", "B.b", "", "");
        jc.bindClassBean("BView.cview", "B.c");

        jc.bindClassProperty("CView.a", "C.a", "conversor", "");
        jc.bindClassProperty("CView.b", "C.b", "", "");
        
        
        // Create new instances
        jc.newInstance("bView", "BView");
        jc.newInstance("bModel", "B");
        jc.newInstance("conversor", "IntegerConversor");
        
        // Set values to model
        jc.getELContext().set("bModel.a", 99);
        jc.getELContext().set("bModel.b", "ALTEROU");

        jc.getELContext().set("bModel.c.a", 33);
        jc.getELContext().set("bModel.c.b", "KKK");
        
        // Bind between instances
        jc.bindInstanceBean("bView", "bModel");

        // Update view
        jc.updateView();
        //System.out.println(jc.getELContext().evaluate("bView"));
        //System.out.println(jc.getELContext().evaluate("bView.cview"));

        // Set values to view
        jc.getELContext().set("bView.a", "111");
        jc.getELContext().set("bView.b", "XXX");
        jc.getELContext().set("bView.cview.a", "4586");
        jc.getELContext().set("bView.cview.b", "kljkl");

        // Update model
        jc.updateModel();
        System.out.println(jc.getELContext().evaluate("bModel"));
        System.out.println(jc.getELContext().evaluate("bModel.c"));
        
    }
    
    @Test
    public void testBindCollection() throws Exception {
        JvController jc = JvController.getInstance();
        
        jc.registerModelClass("A", "ono.leo.com.jvcontroller.test.model.A");
        jc.registerModelClass("B", "ono.leo.com.jvcontroller.test.model.B");
        
        jc.registerViewClass("AView", "ono.leo.com.jvcontroller.test.view.AView");
        jc.registerViewClass("BView", "ono.leo.com.jvcontroller.test.view.BView");
        
        jc.registerConversorClass("IntegerConversor", "ono.leo.com.jvcontroller.test.conversor.IntegerConversor");
        
        // Bind between classes
        jc.bindClassProperty("AView.a", "A.a", "conversor", "");
        jc.bindClassProperty("AView.b", "A.b", "", "");
        jc.bindClassCollection("AView.bviews", "A.bs", "", "", "");

        jc.bindClassProperty("BView.a", "B.a", "conversor", "");
        jc.bindClassProperty("BView.b", "B.b", "", "");
        
        
        // Create new instances
        jc.newInstance("aView", "AView");
        jc.newInstance("aModel", "A");
        jc.newInstance("conversor", "IntegerConversor");
        
        // Bind between instances
        jc.bindInstanceBean("aView", "aModel");

        // Set values to model
        jc.getELContext().set("aModel.a", 99);
        jc.getELContext().set("aModel.b", "ALTEROU");

        // Update view
        jc.updateView();
        System.out.println(jc.getELContext().evaluate("aModel"));
        System.out.println(jc.getELContext().evaluate("aView"));

        // Set values to view
        List<BView> bviews = (List<BView>) jc.getELContext().get("aView.bviews");
        bviews.get(0).setA("12345");
        bviews.get(0).setB("ALTEREI ALTEREI ALTEREI ...");
        
        // Update model
        jc.updateModel();
        System.out.println("aModel=" + jc.getELContext().evaluate("aModel"));
        System.out.println("aView=" + jc.getELContext().evaluate("aView"));
    }
    
}
