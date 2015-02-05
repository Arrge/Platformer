/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

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
public class PatrollingEnemyJUnitTest {
    PatrollingEnemy pe;
    public PatrollingEnemyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pe = new PatrollingEnemy(22, 10, 150, new Entity(10, 0, 1, 32, 32, 10));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void turnsAtMinX() {
        pe.update(1000);
        pe.update(1000);
        assertTrue(pe.getX_vel() > 0);
    }
    @Test
    public void turnsAtMaxX() {
        pe.update(1000);
        assertTrue(pe.getX_vel() < 0);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
