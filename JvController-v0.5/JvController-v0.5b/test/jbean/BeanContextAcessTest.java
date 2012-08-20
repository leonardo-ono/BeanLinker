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
public class BeanContextAcessTest {
    
    public BeanContextAcessTest() {
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
        ObjA a = new ObjA();
        ObjB b = new ObjB();
        BeanContextAcess bca = new BeanContextAcess();
        bca.addBean("objA", a);
        bca.addBean("objB", b);
        bca.removeBean("objA");
        bca.set("objB.objA.varLongPrim", 123);
        System.out.println(bca.get("objB.objA.varLongPrim"));
    }

}
