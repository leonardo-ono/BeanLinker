/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class BeanAcessTest {
    
    public BeanAcessTest() {
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
        BeanSingleAcess ba = new BeanSingleAcess();
        System.out.println(ba.get(a, "varBytePrim"));
        System.out.println(ba.get(a, "varByteObj"));
    }

    /**
     * Test of set method, of class BeanAcess.
     */
    @Test
    public void testSet() throws Exception {
        ObjA a = new ObjA();
        BeanSingleAcess ba = new BeanSingleAcess();
        ba.set(a, "varDoublePrim", 122);
        System.out.println(ba.get(a, "varDoublePrim"));
    }

    /**
     * Test of invoke method, of class BeanAcess.
     */
    @Test
    public void testInvoke() throws Exception {
        ObjA a = new ObjA();
        BeanSingleAcess ba = new BeanSingleAcess();
        ba.invoke(a, "setVarDoublePrim", 122);
        System.out.println(ba.get(a, "varDoublePrim"));
    }

    /**
     * Test of invoke hasGetter, of class BeanAcess.
     */
    @Test
    public void testHasGetter() throws Exception {
        ObjA a = new ObjA();
        BeanSingleAcess ba = new BeanSingleAcess();
        assertFalse(ba.hasGetter(a, "varDoublePrimx"));
        assertTrue(ba.hasGetter(a, "varDoublePrim"));
    }

    /**
     * Test of invoke hasSetter, of class BeanAcess.
     */
    @Test
    public void testHasSetter() throws Exception {
        ObjA a = new ObjA();
        BeanSingleAcess ba = new BeanSingleAcess();
        assertFalse(ba.hasSetter(a, "varDoublePrimx"));
        assertTrue(ba.hasSetter(a, "varDoublePrim"));
    }

    /**
     * Test of invoke hasMethod, of class BeanAcess.
     */
    @Test
    public void testHasMethod() throws Exception {
        ObjA a = new ObjA();
        BeanSingleAcess ba = new BeanSingleAcess();
        assertFalse(ba.hasMethod(a, "setVarDoublePrimx"));
        assertTrue(ba.hasMethod(a, "setVarDoublePrim"));
    }

    /**
     * Test of invoke getType, of class BeanAcess.
     */
    @Test
    public void testGetType() throws Exception {
        ObjA a = new ObjA();
        BeanSingleAcess ba = new BeanSingleAcess();
        assertEquals(double.class, ba.getType(a, "varDoublePrim"));
        assertEquals(Double.class, ba.getType(a, "varDoubleObj"));
    }
    
    
}
