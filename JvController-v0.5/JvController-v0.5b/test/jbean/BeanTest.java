package jbean;

import rejected.Bean;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leo
 */
public class BeanTest {

    private static BeanAcessInterface bean;
    private ObjA objA;
    private ObjB objB;
    
    public BeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        bean = new BeanNestedAcess(); // Bean.getInstance();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        objA = new ObjA();
        objB = new ObjB();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class Bean.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        BeanAcessInterface result = Bean.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of set method, of class Bean.
     */
    @Test
    public void testSet() throws Exception {
        System.out.println("testSet");
        
        Object obj = null;
        Date date = null;
        BigDecimal bigDecimal = null;
        
        // primeiro nivel - setter
        bean.set(objA, "varBytePrim", (byte) 123);
        bean.set(objA, "varByteObj", (byte) 124);
        
        bean.set(objA, "varShortPrim", (byte) 125);
        bean.set(objA, "varShortPrim", (short) 125);
        bean.set(objA, "varShortObj", (short) 126);
        
        bean.set(objA, "varIntPrim", (byte) 127);
        bean.set(objA, "varIntPrim", (short) 127);
        bean.set(objA, "varIntPrim", (int) 127);
        bean.set(objA, "varIntObj", 128);

        bean.set(objA, "varLongPrim", (byte) 129);
        bean.set(objA, "varLongPrim", (short) 129);
        bean.set(objA, "varLongPrim", (int) 129);
        bean.set(objA, "varLongPrim", (long) 129);
        bean.set(objA, "varLongObj", 130L);
        
        bean.set(objA, "varFloatPrim", (byte) 31.5f);
        bean.set(objA, "varFloatPrim", (short) 31.5f);
        bean.set(objA, "varFloatPrim", (int) 31.5f);
        bean.set(objA, "varFloatPrim", (long) 31.5f);
        bean.set(objA, "varFloatPrim", (float) 31.5f);
        bean.set(objA, "varFloatObj", 32.5f);
        
        bean.set(objA, "varDoublePrim", (byte) 33.5);
        bean.set(objA, "varDoublePrim", (short) 33.5);
        bean.set(objA, "varDoublePrim", (int) 33.5);
        bean.set(objA, "varDoublePrim", (long) 33.5);
        bean.set(objA, "varDoublePrim", (float) 33.5);
        bean.set(objA, "varDoublePrim", 33.5);
        bean.set(objA, "varDoubleObj", 34.5);
        
        bean.set(objA, "varBooleanPrim", false);
        bean.set(objA, "varBooleanObj", false);
        
        bean.set(objA, "varCharPrim", 'B');
        bean.set(objA, "varCharObj", 'C');
        obj = new Object();
        date = new Date(12345L);
        bigDecimal = BigDecimal.ONE;
        bean.set(objA, "obj", obj);
        bean.set(objA, "varDateObj", date);
        bean.set(objA, "varBigDecimalObj", bigDecimal);
        
        // Verificacao
        assertEquals(objA.getVarBytePrim(), (byte) 123);
        assertEquals(objA.getVarByteObj(), (Byte) (byte) 124);
        assertEquals(objA.getVarShortPrim(), (short) 125);
        assertEquals(objA.getVarShortObj(), (Short) (short) 126);
        assertEquals(objA.getVarIntPrim(), 127);
        assertEquals(objA.getVarIntObj(), (Integer) 128);
        assertEquals(objA.getVarLongPrim(), 129L);
        assertEquals(objA.getVarLongObj(), (Long) 130L);
        assertEquals(objA.getVarFloatPrim(), (float) 31.5, 0);
        assertEquals(objA.getVarFloatObj(), (Float) 32.5f, 0);
        assertEquals(objA.getVarDoublePrim(), 33.5d, 0);
        assertEquals(objA.getVarDoubleObj(), (Double) 34.5d);
        assertEquals(objA.isVarBooleanPrim(), false);
        assertEquals(objA.getVarBooleanObj(), false);
        assertEquals(objA.getVarCharPrim(), 'B');
        assertEquals(objA.getVarCharObj(), (Character) 'C');
        assertEquals(objA.getObj(), obj);
        assertEquals(objA.getVarDateObj(), date);
        assertEquals(objA.getVarBigDecimalObj(), bigDecimal);

        // segundo nivel - setters
        bean.set(objB, "objA.varBytePrim", (byte) 35);
        bean.set(objB, "objA.varByteObj", (byte) 36);
        bean.set(objB, "objA.varShortPrim", (short) 137);
        bean.set(objB, "objA.varShortObj", (short) 138);
        bean.set(objB, "objA.varIntPrim", 139);
        bean.set(objB, "objA.varIntObj", 140);
        bean.set(objB, "objA.varLongPrim", 141L);
        bean.set(objB, "objA.varLongObj", 142L);
        bean.set(objB, "objA.varFloatPrim", 143.5f);
        bean.set(objB, "objA.varFloatObj", 144.5f);
        bean.set(objB, "objA.varDoublePrim", 145.5d);
        bean.set(objB, "objA.varDoubleObj", 146.5d);
        bean.set(objB, "objA.varBooleanPrim", false);
        bean.set(objB, "objA.varBooleanObj", false);
        bean.set(objB, "objA.varCharPrim", 'D');
        bean.set(objB, "objA.varCharObj", 'E');
        obj = new Object();
        date = new Date(67890L);
        bigDecimal = BigDecimal.ZERO;
        bean.set(objB, "objA.obj", obj);
        bean.set(objB, "objA.varDateObj", date);
        bean.set(objB, "objA.varBigDecimalObj", bigDecimal);
        
        // Verificacao
        assertEquals(objB.getObjA().getVarBytePrim(), 35);
        assertEquals(objB.getObjA().getVarByteObj(), (Byte) (byte) 36);
        assertEquals(objB.getObjA().getVarShortPrim(), 137);
        assertEquals(objB.getObjA().getVarShortObj(), (Short) (short) 138);
        assertEquals(objB.getObjA().getVarIntPrim(), 139);
        assertEquals(objB.getObjA().getVarIntObj(), (Object) 140);
        assertEquals(objB.getObjA().getVarLongPrim(), 141L);
        assertEquals(objB.getObjA().getVarLongObj(), (Long) 142L);
        assertEquals(objB.getObjA().getVarFloatPrim(), (float) 143.5, 0);
        assertEquals(objB.getObjA().getVarFloatObj(), (Float) 144.5f, 0);
        assertEquals(objB.getObjA().getVarDoublePrim(), 145.5d, 0);
        assertEquals(objB.getObjA().getVarDoubleObj(), (Double) 146.5d);
        assertEquals(objB.getObjA().isVarBooleanPrim(), false);
        assertEquals(objB.getObjA().getVarBooleanObj(), false);
        assertEquals(objB.getObjA().getVarCharPrim(), 'D');
        assertEquals(objB.getObjA().getVarCharObj(), (Character) 'E');
        assertEquals(objB.getObjA().getObj(), obj);
        assertEquals(objB.getObjA().getVarDateObj(), date);
        assertEquals(objB.getObjA().getVarBigDecimalObj(), bigDecimal);
        
    }

    /**
     * Test of get method, of class Bean.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("testGet");
        
        // primeiro nivel
        assertEquals(objA.getVarBytePrim(), bean.get(objA, "varBytePrim"));
        assertEquals(objA.getVarByteObj(), bean.get(objA, "varByteObj"));
        assertEquals(objA.getVarShortPrim(), bean.get(objA, "varShortPrim"));
        assertEquals(objA.getVarShortObj(), bean.get(objA, "varShortObj"));
        assertEquals(objA.getVarIntPrim(), bean.get(objA, "varIntPrim"));
        assertEquals(objA.getVarIntObj(), bean.get(objA, "varIntObj"));
        assertEquals(objA.getVarLongPrim(), bean.get(objA, "varLongPrim"));
        assertEquals(objA.getVarLongObj(), bean.get(objA, "varLongObj"));
        assertEquals(objA.getVarFloatPrim(), bean.get(objA, "varFloatPrim"));
        assertEquals(objA.getVarFloatObj(), bean.get(objA, "varFloatObj"));
        assertEquals(objA.getVarDoublePrim(), bean.get(objA, "varDoublePrim"));
        assertEquals(objA.getVarDoubleObj(), bean.get(objA, "varDoubleObj"));
        assertEquals(objA.isVarBooleanPrim(), bean.get(objA, "varBooleanPrim"));
        assertEquals(objA.getVarBooleanObj(), bean.get(objA, "varBooleanObj"));
        assertEquals(objA.getVarCharPrim(), bean.get(objA, "varCharPrim"));
        assertEquals(objA.getVarCharObj(), bean.get(objA, "varCharObj"));
        assertEquals(objA.getObj(), bean.get(objA, "obj"));
        
        // segundo nivel
        assertEquals(objB.getObjA().getVarBytePrim(), bean.get(objB, "objA.varBytePrim"));
        assertEquals(objB.getObjA().getVarByteObj(), bean.get(objB, "objA.varByteObj"));
        assertEquals(objB.getObjA().getVarShortPrim(), bean.get(objB, "objA.varShortPrim"));
        assertEquals(objB.getObjA().getVarShortObj(), bean.get(objB, "objA.varShortObj"));
        assertEquals(objB.getObjA().getVarIntPrim(), bean.get(objB, "objA.varIntPrim"));
        assertEquals(objB.getObjA().getVarIntObj(), bean.get(objB, "objA.varIntObj"));
        assertEquals(objB.getObjA().getVarLongPrim(), bean.get(objB, "objA.varLongPrim"));
        assertEquals(objB.getObjA().getVarLongObj(), bean.get(objB, "objA.varLongObj"));
        assertEquals(objB.getObjA().getVarFloatPrim(), bean.get(objB, "objA.varFloatPrim"));
        assertEquals(objB.getObjA().getVarFloatObj(), bean.get(objB, "objA.varFloatObj"));
        assertEquals(objB.getObjA().getVarDoublePrim(), bean.get(objB, "objA.varDoublePrim"));
        assertEquals(objB.getObjA().getVarDoubleObj(), bean.get(objB, "objA.varDoubleObj"));
        assertEquals(objB.getObjA().isVarBooleanPrim(), bean.get(objB, "objA.varBooleanPrim"));
        assertEquals(objB.getObjA().getVarBooleanObj(), bean.get(objB, "objA.varBooleanObj"));
        assertEquals(objB.getObjA().getVarCharPrim(), bean.get(objB, "objA.varCharPrim"));
        assertEquals(objB.getObjA().getVarCharObj(), bean.get(objB, "objA.varCharObj"));
        assertEquals(objB.getObjA().getObj(), bean.get(objB, "objA.obj"));
    }

    /**
     * Test of invoke method, of class Bean.
     */
    //@Test
    public void testInvoke() throws Exception {
        System.out.println("invoke");
        Object bean = null;
        String methodName = "";
        Object[] values = null;
        Bean instance = null;
        Object expResult = null;
        Object result = instance.invoke(bean, methodName, values);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Bean.
     */
    //@Test
    public void testGetType() throws Exception {
        System.out.println("getType");
        Object bean = null;
        String propertyName = "";
        Bean instance = null;
        Class expResult = null;
        Class result = instance.getType(bean, propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasGetter method, of class Bean.
     */
    //@Test
    public void testHasGetter() throws Exception {
        System.out.println("hasGetter");
        Object bean = null;
        String propertyName = "";
        Bean instance = null;
        boolean expResult = false;
        boolean result = instance.hasGetter(bean, propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasSetter method, of class Bean.
     */
    //@Test
    public void testHasSetter() throws Exception {
        System.out.println("hasSetter");
        Object bean = null;
        String propertyName = "";
        Bean instance = null;
        boolean expResult = false;
        boolean result = instance.hasSetter(bean, propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasMethod method, of class Bean.
     */
    //@Test
    public void testHasMethod() throws Exception {
        System.out.println("hasMethod");
        Object bean = null;
        String methodName = "";
        Bean instance = null;
        boolean expResult = false;
        boolean result = instance.hasMethod(bean, methodName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
