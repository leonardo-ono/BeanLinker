package ono.leo.com.jvcontroller.core;

import ono.leo.jvcontroller.core.JvController;
import ono.leo.jvcontroller.core.JvControllerInterface;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test implementation of JvControllerImplTest.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 12:50)
 */
public class JvController_classContextTest {
    
    public JvController_classContextTest() {
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

    @Test
    public void testRegisterClass() throws Exception {
        JvControllerInterface jc = JvController.getInstance();
        jc.registerModelClass("ClassC", "ono.leo.com.jvcontroller.test.model.C");
    }

    @Test
    public void testRegisterClass_classAliasMustNotBeNull() throws Exception {
        JvControllerInterface jc = JvController.getInstance();
        try {
            jc.registerModelClass(null
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage().equals("Class alias must not be null !"));
        }
    }

    @Test
    public void testRegisterClass_classAliasMustNotBeEmpty() throws Exception {
        JvControllerInterface jc = JvController.getInstance();
        
        try {
            jc.registerModelClass(""
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(
                    e.getMessage().equals("Class alias must not be empty !"));
        }

        try {
            jc.registerModelClass("     "
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(
                    e.getMessage().equals("Class alias must not be empty !"));
        }
    }
    
    @Test
    public void testRegisterClass_classAliasMustStartWithLetter() 
            throws Exception {
        
        JvControllerInterface jc = JvController.getInstance();
        try {
            jc.registerModelClass("1JvControllerImplTest"
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage()
                    .equals("Class alias must start with a letter !"));
        }
    }

    @Test
    public void testRegisterClass_classAliasMustStartWithUpperLetter() 
            throws Exception {
        
        JvControllerInterface jc = JvController.getInstance();

        try {
            jc.registerModelClass("b", "ono.leo.com.jvcontroller.test.model.B");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage().equals(
                    "Class alias must start with upper letter !"));
        }

        try {
            jc.registerModelClass("CVCIT123"
                    , "ono.leo.com.jvcontroller.test.model.C");
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testRegisterClass_classAliasContainsInvalidCharacter() 
            throws Exception {
        
        JvControllerInterface jc = JvController.getInstance();
        
        try {
            jc.registerModelClass("JvControllerImplTest.Class"
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage().equals(
                    "Class alias contains invalid character '.' !"));
        }
        
        try {
            jc.registerModelClass("JvControllerImplTest2."
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage().equals(
                    "Class alias contains invalid character '.' !"));
        }
        
        try {
            jc.registerModelClass("J.vControllerImplTest3"
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage().equals(
                    "Class alias contains invalid character '.' !"));
        }
        
        try {
            jc.registerModelClass("JvControllerImplTest4_"
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage().equals(
                    "Class alias contains invalid character '_' !"));
        }
        
        try {
            jc.registerModelClass("JvController$ImplTest5"
                    , "ono.leo.com.jvcontroller.core.JvControllerImplTest");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage().equals(
                    "Class alias contains invalid character '$' !"));
        }
    }
    
    @Test
    public void testRegisterClass_classAliasAlreadyRegistered() 
            throws Exception {
        
        JvControllerInterface jc = JvController.getInstance();
        try {
            jc.registerModelClass("ClassAliasAlreadyRegistered"
                    , "ono.leo.com.jvcontroller.test.model.B");
            jc.registerModelClass("ClassAliasAlreadyRegistered"
                    , "ono.leo.com.jvcontroller.test.model.C");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e.getMessage());
            assertTrue(e.getMessage().indexOf(
                    "already registered !") >= 0);
        }
    }
    
    @Test
    public void testRegisterClass_classNameNull() throws Exception {
        JvControllerInterface jc = JvController.getInstance();
        try {
            jc.registerModelClass("ClassNameNull", null);
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e);
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testRegisterClass_classNameEmpty() throws Exception {
        JvControllerInterface jc = JvController.getInstance();
        
        try {
            jc.registerModelClass("ClassNameEmpty", "");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e);
            assertTrue(e instanceof ClassNotFoundException);
        }

        try {
            jc.registerModelClass("ClassNameEmpty", "   ");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e);
            assertTrue(e instanceof ClassNotFoundException);
        }
    }

    @Test
    public void testRegisterClass_classNotFound() throws Exception {
        JvControllerInterface jc = JvController.getInstance();
        try {
            jc.registerModelClass("ClassDoesNotExist", "ClassDoesNotExist");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e);
            assertTrue(e instanceof ClassNotFoundException);
        }
    }

    //@Test
    public void testRegisterClass_classMustHaveNonArgumentConstructor() 
            throws Exception {
        
        JvControllerInterface jc = JvController.getInstance();
        
        try {
            jc.registerModelClass("A", "ono.leo.com.jvcontroller.test.model.A");
            Assert.fail();
        }
        catch (Exception e) {
            // System.out.println(e);
            assertTrue(e.getMessage().equals(
                    "Class ono.leo.com.jvcontroller.test.model.A "
                    + "does not have a non-argument constructor !"));
        }

        try {
            jc.registerModelClass("B", "ono.leo.com.jvcontroller.test.model.B");
        }
        catch (Exception e) {
            // System.out.println(e);
            fail();
        }

        try {
            jc.registerModelClass("C", "ono.leo.com.jvcontroller.test.model.C");
        }
        catch (Exception e) {
            // System.out.println(e);
            fail();
        }
    }

}
