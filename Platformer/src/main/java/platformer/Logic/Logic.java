/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Logic;

import java.util.ArrayList;
import org.newdawn.slick.Input;
import platformer.Entities.*;

/**
 * Class that contains logic for the game
 * @author Joonas
 */
public class Logic {

    private ArrayList<Collidable> platforms;
    private ArrayList<Collidable> damagingCollidables;
    private ArrayList<PatrollingEnemy> PatrollingEnemies;
    private ArrayList<Firespinner> firespinners;
    private final Input input;
    private Player player;

    /**
     * 
     * @param input get input from GameContainer in GUI
     */
    public Logic(Input input) {

        this.player = new Player(100, 250, 1, 12, 26);
        this.input = input;
        this.platforms = new ArrayList<>();
        this.damagingCollidables = new ArrayList<>();
        this.PatrollingEnemies = new ArrayList<>();
        this.firespinners = new ArrayList<>();
    }

    /**
     * update the game to next frame
     * @param delta milliseconds since last tick
     */
    public void update(int delta) {
        checkInput();
        applyGravity(delta);

        player.move(delta);
        updateFirespinners(delta);
        updatePatrollingEnemies(delta);
        checkForCollisions(damagingCollidables);
        checkForCollisions(platforms);
        checkFirespinners();

    }

    /**
     * checks keys and apply movement to player
     */
    public void checkInput() {
        if (input.isKeyDown(Input.KEY_A)) {
            player.goLeft();
        }
        if (input.isKeyDown(Input.KEY_D)) {
            player.goRight();
        }
        if (input.isKeyDown(Input.KEY_SPACE)) {
            player.jump();
        }
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            player.setLocation(200, 200);
        }

    }
    /**
     * 
     * @param delta milliseconds since last tick
     */
    public void updatePatrollingEnemies(int delta) {
        for (PatrollingEnemy p : PatrollingEnemies) {
            p.update(delta);
        }
    }
    
    public void updateFirespinners(int delta) {
        for (Firespinner fp : firespinners) {
            fp.update(delta);
        }
    }
    /**
     * apply gravity to objects
     * @param delta milliseconds since last tick
     */
    public void applyGravity(int delta) {
        player.applyGravityAndVelocity(delta);
    }

    /**
     * Check for collisions between the player and a collidable object
     *
     * @param collidables
     */
    public void checkForCollisions(ArrayList<Collidable> collidables) {
        for (Collidable collidable : collidables) {
            checkHorizontalCollisions(collidable);
            checkVerticalCollisions(collidable);
        }
    }

    private void checkHorizontalCollisions(Collidable collidable) {
        if (player.getHitbox().intersects(collidable.getHitbox())) {
            if (collidable.getCollisionDamage() > 0) {
                player.takeDamage(collidable.getCollisionDamage());
            }
            //if coming from left
            if (player.getMaxX_old() < collidable.getX_old()) {
                player.setLocation(collidable.getX() - player.getWidth() - 0.1f, player.getY());
                player.setX_vel(collidable.getX_vel());
            } //if coming from right
            else if (player.getX_old() > collidable.getMaxX_old()) {
                player.setLocation(collidable.getMaxX() + 0.1f, player.getY());
                player.setX_vel(collidable.getX_vel());
            }
        }
    }

    private void checkVerticalCollisions(Collidable collidable) {
        if (player.getHitbox().intersects(collidable.getHitbox())) {
            //if coming from above
            if (player.getMaxY_old() < collidable.getY_old()) {
                player.setLocation(player.getX(), collidable.getY() - player.getHeight() - 0.1f);
                player.setY_vel(collidable.getY_vel());
                player.setX_vel(collidable.getX_vel());
                player.setOnPlatform(true);
            } //if coming from under
            else if (player.getY_old() > collidable.getMaxY_old()) {
                player.setLocation(player.getX(), collidable.getMaxY() + 0.1f);
                player.setY_vel(collidable.getY_vel());
                player.setX_vel(collidable.getX_vel());
            }
        }
    }
    
    public void checkFirespinners() {
        ArrayList<Collidable> collidables;
        for (Firespinner fs : firespinners) {
            collidables = new ArrayList<>();
            collidables.addAll(fs.getFireballs());
            checkOnlyCollisionDamage(collidables );
        }
    }
    
    public void checkOnlyCollisionDamage(ArrayList<Collidable> collidables) {
        for (Collidable c : collidables) {
            if (player.getHitbox().intersects(c.getHitbox())) {
                player.takeDamage(c.getCollisionDamage());
            }
        }
    }

    public ArrayList<Collidable> getPlatforms() {
        return platforms;
    }

    public ArrayList<PatrollingEnemy> getPatrollingEnemies() {
        return PatrollingEnemies;
    }

    public ArrayList<Firespinner> getFirespinners() {
        return firespinners;
    }

    
    
    public void addPatrollingEnemiesArrayList(ArrayList<PatrollingEnemy> list) {
        PatrollingEnemies.addAll(list);
        damagingCollidables.addAll(list);

    }
    
    public void addFirespinnerArrayList(ArrayList<Firespinner> list) {
        firespinners.addAll(list);
    }

    public void addDamagingCollidablesArrayList(ArrayList<Collidable> list) {
        damagingCollidables.addAll(list);
    }

    public void addPlatformArrayList(ArrayList<Collidable> list) {
        platforms.addAll(list);
    }

    public void addPlatform(Collidable e) {
        platforms.add(e);
    }

    public Player getPlayer() {
        return player;
    }

}
