/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.entities;

import platformer.entities.Firespinner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joonas
 */
public class FirespinnerJUnitTest {

    Firespinner fs, fsOld;

    public FirespinnerJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        fs = new Firespinner(32, 32, 25);
        fsOld = new Firespinner(32, 32, 25);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void fireballsMove() {
        fs.update(1000);

        assertTrue(fs.getFireballs().get(fs.getFireballs().size() - 1).getX() != fsOld.getFireballs().get(fsOld.getFireballs().size() - 1).getX());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
