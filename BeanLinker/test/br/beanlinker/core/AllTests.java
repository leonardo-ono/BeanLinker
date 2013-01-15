/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beanlinker.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author leo
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({br.beanlinker.core.PropertyLinkingTest.class, br.beanlinker.core.BeanLinkingTest.class, br.beanlinker.core.CollectionLinkingTest.class})
public class AllTests {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
