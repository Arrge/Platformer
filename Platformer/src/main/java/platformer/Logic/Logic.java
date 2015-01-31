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
 *
 * @author Joonas
 */
public class Logic {

    private ArrayList<Collidable> platforms;
    private ArrayList<Collidable> damagingCollidables;
    private ArrayList<PatrollingEnemy> PatrollingEnemies;
    private final Input input;
    private Player player;

    //get input from GameContainer in GUI
    public Logic(Input input) {

        this.player = new Player(100, 250, 1, 12, 26);
        this.input = input;
        this.platforms = new ArrayList<>();
        this.damagingCollidables = new ArrayList<>();
        this.PatrollingEnemies = new ArrayList<>();
    }

    public void update(int delta) {
        checkInput();
        applyGravity(delta);

        player.move(delta);
        updatePatrollingEnemies(delta);
        checkForCollisions(damagingCollidables);
        checkForCollisions(platforms);
        
        
    }

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
    
    public void updatePatrollingEnemies(int delta) {
        for (PatrollingEnemy p : PatrollingEnemies ) {
            p.update(delta);
        }
    }
 
    public void applyGravity(int delta) {
        player.applyGravityAndVelocity(delta);
    }

    public void checkForCollisions(ArrayList<Collidable> collidables) {

        for (Collidable platform : collidables) {
            if (player.getHitbox().intersects(platform.getHitbox())) {
                if (platform.getCollisionDamage() > 0) {
                    player.takeDamage(platform.getCollisionDamage());
                }
                //if coming from left
                if (player.getMaxX_old() < platform.getX_old()) {
                    player.setLocation(platform.getX() - player.getWidth() - 0.1f, player.getY());
                    player.setX_vel(platform.getX_vel());
                } 
                //if coming from right
                else if (player.getX_old() > platform.getMaxX_old()) {
                    player.setLocation(platform.getMaxX() + 0.1f, player.getY());
                    player.setX_vel(platform.getX_vel());
                }
            }
            
            if (player.getHitbox().intersects(platform.getHitbox())) {
                //if coming from above
                if (player.getMaxY_old() < platform.getY_old()) {
                    player.setLocation(player.getX(), platform.getY() - player.getHeight() - 0.1f);
                    player.setY_vel(platform.getY_vel());
                    player.setX_vel(platform.getX_vel());
                    player.setOnPlatform(true);
                } 
                //if coming from under
                else if (player.getY_old() > platform.getMaxY_old()) {
                    player.setLocation(player.getX(), platform.getMaxY() + 0.1f);
                    player.setY_vel(platform.getY_vel());
                    player.setX_vel(platform.getX_vel());
                }
            }

            
        }
    }
    
    public ArrayList<Collidable> getPlatforms() {
        return platforms;
    }

    public ArrayList<PatrollingEnemy> getPatrollingEnemies() {
        return PatrollingEnemies;
    }
    
    
    
    public void addPatrollingEnemiesArrayList(ArrayList<PatrollingEnemy> list) {
        PatrollingEnemies.addAll(list);
        damagingCollidables.addAll(list);
        
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
