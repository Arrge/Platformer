package platformer.Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PlayerJUnitTest {
    Player p;
    
    public PlayerJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new Player(0, 0, 20, 10);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void gravityIncreasesFallSpeed() {
        p.applyGravityAndVelocity();
        
        assertTrue(p.getY_vel() > 0);
    }
    @Test
    public void xVelocitySlowsDownWhenOnPlatform() {
        p.setX_vel(10f);
        p.setOnPlatform(true);
        p.applyGravityAndVelocity();
        assertTrue(p.getX_vel() < 10f);
    }
    @Test
    public void JumpWorksWhenOnPlatform() {
        p.setOnPlatform(true);
        p.jump();
        p.applyGravityAndVelocity();
        p.move();
        assertTrue(p.getY_old() > p.getY());
    }
    @Test
    public void PlayerGoesLeft() {
        p.goLeft();
        p.applyGravityAndVelocity();
        p.move();
        assertTrue(p.getX_old() > p.getX());
    }
    @Test
    public void playerGoesRight() {
        p.goRight();
        p.applyGravityAndVelocity();
        p.move();
        assertTrue(p.getX_old() < p.getX());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
