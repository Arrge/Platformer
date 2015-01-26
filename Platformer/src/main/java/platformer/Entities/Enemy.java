/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

/**
 *
 * @author Joonas
 */
public class Enemy extends Entity {

    private int health, damage;

    public Enemy(int health, int damage, int x, int y, int spriteX, int spriteY, int width, int height) {
        super(x, y, spriteX, spriteY, width, height);
        this.health = health;
        this.damage = damage;
    }

    public boolean damage(int amount) {
        if (health - amount <= 0) {
            health = 0;
            return true;
        }
        health -= amount;
        return false;
    }
}
