package jbean;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leonardo
 */
public class BeanELAcessTest {
    
    public BeanELAcessTest() {
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

    /**
     * Test of get method, of class BeanAcess.
     */
    @Test
    public void testGet() throws Exception {
        ObjB b = new ObjB();
        BeanELAcess ba = new BeanELAcess();
        ba.addBean("objB", b);
        //Object ret = ba.evaluate("objB.acao(objB.acao(objB.varStringObj, objB.acao(objB.objA.varIntObj, objB.acao(objB.objA.varIntObj, objB.objA.varIntObj))), objB.acao( objB.objA.varIntObj, objB.varStringObj))");
        //Object ret2 = ba.evaluate("objB.varStringObj");
        Object ret3 = ba.evaluate("objB.acao( objB.varStringObj.equals('TESTE'), objB.objA.varIntObj)");
        //System.out.println("Ret=" + ret);
        //System.out.println("Ret2=" + ret2);
        System.out.println("Ret3=" + ret3);
    }

}
