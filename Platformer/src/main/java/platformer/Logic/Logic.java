/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.logic;

import platformer.entities.Collidable;
import platformer.entities.PatrollingEnemy;
import platformer.entities.Firespinner;
import platformer.entities.Boss;
import platformer.entities.Player;
import java.util.ArrayList;
import org.newdawn.slick.Input;

/**
 * Class that contains logic for the game
 *
 * @author Joonas
 */
public class Logic {

    private ArrayList<Collidable> platforms;
    private ArrayList<Collidable> damagingCollidables;
    private ArrayList<PatrollingEnemy> PatrollingEnemies;
    private ArrayList<Firespinner> firespinners;
    private Input input;
    private Player player;
    private Boss boss;

    /**
     * create a new logic class which has access to the slick2d keylistener
     *
     * @param input keylistener Input class from GameContainer in GUI
     */
    public Logic(Input input) {
        boss = new Boss(9, 64, 64);
        this.player = new Player(50, 100, 1, 12, 26);
        this.input = input;
        this.platforms = new ArrayList<>();
        this.damagingCollidables = new ArrayList<>();
        this.PatrollingEnemies = new ArrayList<>();
        this.firespinners = new ArrayList<>();
        addDamagingCollidable(boss);
    }

    /**
     * update the game to next frame
     *
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
        checkForBossFight();
        boss.update(delta);
    }

    /**
     * Check for collisions between the player and a collidable object
     *
     * @param collidables an arraylist of collidable objects
     */
    public void checkForCollisions(ArrayList<Collidable> collidables) {
        for (Collidable collidable : collidables) {
            checkHorizontalCollisions(collidable);
            checkVerticalCollisions(collidable);
        }
    }

    private void checkHorizontalCollisions(Collidable collidable) {
        if (player.getHitbox().intersects(collidable.getHitbox())) {

            //if coming from left
            if (player.getMaxX_old() < collidable.getX_old()) {
                player.setLocation(collidable.getX() - player.getWidth() - 0.1f, player.getY());
                player.setX_vel(collidable.getX_vel());
            } //if coming from right
            else if (player.getX_old() > collidable.getMaxX_old()) {
                player.setLocation(collidable.getMaxX() + 0.1f, player.getY());
                player.setX_vel(collidable.getX_vel());
            }
            if (collidable.getCollisionDamage() > 0) {
                if (player.takeDamage(collidable.getCollisionDamage())) {
                    boss.reset();
                }
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

    /**
     * check for firespinnercollisions
     */
    public void checkFirespinners() {
        ArrayList<Collidable> collidables;
        for (Firespinner fs : firespinners) {
            collidables = new ArrayList<>();
            collidables.addAll(fs.getFireballs());
            checkOnlyCollisionDamage(collidables);
        }
    }

    /**
     * apply collision damage without fixing collisions
     *
     * @param collidables
     */
    public void checkOnlyCollisionDamage(ArrayList<Collidable> collidables) {
        for (Collidable c : collidables) {
            if (player.getHitbox().intersects(c.getHitbox())) {
                if (player.takeDamage(c.getCollisionDamage())) {
                    boss.reset();
                }
            }
        }
    }

    /**
     * check if player is at the boss fight height
     */
    public void checkForBossFight() {
        if (player.getY() > boss.getStartposY()) {
            boss.start();
        }
    }

    /**
     * checks keys and apply movement to player
     */
    public void checkInput() {
        if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
            player.goLeft();
        }
        if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
            player.goRight();
        }
        if (input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            player.jump();
        }
        if (input.isKeyDown(Input.KEY_F3)) {
            player.setLocation(56 * 32, 10 * 32);
        }

    }

    /**
     * update all patrolling enemies
     *
     * @param delta milliseconds since last tick
     */
    public void updatePatrollingEnemies(int delta) {
        for (PatrollingEnemy p : PatrollingEnemies) {
            p.update(delta);
        }
    }

    /**
     * update all firespinners
     *
     * @param delta milliseconds since last tick
     */
    public void updateFirespinners(int delta) {
        for (Firespinner fp : firespinners) {
            fp.update(delta);
        }
    }

    /**
     * apply gravity to player and other objects
     *
     * @param delta milliseconds since last tick
     */
    public void applyGravity(int delta) {
        player.applyGravityAndVelocity(delta);
    }

    ///////////////////
    //get & set & add//
    ///////////////////
    public ArrayList<Collidable> getPlatforms() {
        return platforms;
    }

    public ArrayList<PatrollingEnemy> getPatrollingEnemies() {
        return PatrollingEnemies;
    }

    public ArrayList<Firespinner> getFirespinners() {
        return firespinners;
    }

    /**
     * add patrolling enemies to the PatrollingEnemies arraylist aswell as the
     * damagingCollidables arraylist
     *
     * @param list list of patrolling enemies
     */
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

    public void addDamagingCollidable(Collidable collidable) {
        damagingCollidables.add(collidable);
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

    public Boss getBoss() {
        return boss;
    }

    public ArrayList<Collidable> getDamagingCollidables() {
        return damagingCollidables;
    }

}
