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
public class World {
    ArrayList<Entity> platforms;
    ArrayList<Enemy> enemies;
    Input input;
    Player player;
    //gravity as pixels per tick
    final float gravity;

    //get input class from 
    public World(Input input) {
        this.input = input;
        this.player = new Player(40, 40);
        this.gravity = 1;
        this.platforms = new ArrayList<Entity>();
        this.enemies = new ArrayList<Enemy>();
    }
    
    //update all entities
    public void update() {
        
    }
}
