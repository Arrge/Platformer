package platformer.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import platformer.entities.Player;
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
        p = new Player(0, 0, 1, 20, 10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void goesImmuneWhenAttacked() {
        int health = p.getHealth();
        p.takeDamage(1);
        p.takeDamage(1);
        assertTrue(p.getHealth() == health - 1);
    }

    @Test
    public void healthDoesntGoBelowZero() {
        p.takeDamage(p.getHealth() + 1);
        assertTrue(p.getHealth() >= 0);
    }

    @Test
    public void gravityIncreasesFallSpeed() {
        p.applyGravityAndVelocity(17);

        assertTrue(p.getY_vel() > 0);
    }

    @Test
    public void xVelocitySlowsDownWhenOnPlatform() {
        p.setX_vel(10f);
        p.setOnPlatform(true);
        p.applyGravityAndVelocity(17);
        assertTrue(p.getX_vel() < 10f);
    }

    @Test
    public void JumpWorksWhenOnPlatform() {
        p.setOnPlatform(true);
        p.jump();
        p.applyGravityAndVelocity(17);
        p.move(17);
        assertTrue(p.getY_old() > p.getY() && !p.isOnPlatform());
    }

    @Test
    public void PlayerGoesLeft() {
        p.goLeft();
        p.applyGravityAndVelocity(17);
        p.move(17);
        assertTrue(p.getX_old() > p.getX());
    }

    @Test
    public void playerGoesRight() {
        p.goRight();
        p.applyGravityAndVelocity(17);
        p.move(17);
        assertTrue(p.getX_old() < p.getX());
    }

    @Test
    public void dontTakeDamageWhenImmune() {
        int health = p.getHealth();
        p.setImmunityTimer(p.getImmunityTime());
        p.takeDamage(10);
        assertTrue(health == p.getHealth());
    }

    @Test
    public void HealthResetsToFullWhenPlayerDies() {
        p.takeDamage(p.getHealth());
        assertTrue(p.getX() == 50 && p.getY() == 100 && p.getHealth() == 50);
    }

    @Test
    public void opacityChangesWhenImmune() {
        p.takeDamage(1);
        assertTrue(p.getOpacity() != 255);
    }

    @Test
    public void opacityDoesntChangeIfNotImmune() {
        assertTrue(p.getOpacity() == 255);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
