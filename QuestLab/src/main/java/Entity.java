import items.Consumable;
import items.Weapon;
import items.Wearable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Entity {

    String name;
    Weapon heldItem;
    ArrayList<Consumable> consumables;
    Wearable wearable;
    Boolean alive;

    double hitPoints;
    double hitPointsLimit;

    double strength;
    double accuracy;


    public double getHitPointsLimit() {
        return hitPointsLimit;
    }

    public Entity(String name, double hitPoints, double strength, double accuracy){
        this.name = name;
        this.hitPoints = hitPoints;
        this.hitPointsLimit = hitPoints * 1.2;
        this.alive = true;
        this.heldItem = Weapon.FISTS;
        this.strength = strength;
        this.accuracy = accuracy;
        this.wearable = Wearable.NOTHING;
        this.consumables = new ArrayList<>();
    }

    public String getWearableName() {
        return this.wearable.getName();
    }

    public double getStrength() {
        return this.strength;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public String getName() {
        return this.name;
    }

    public double getHitPoints () {
        return this.hitPoints;
    }

    public boolean getAliveStatus () {
        return alive;
    }

    public void changeHeldItem(Weapon weapon){
        this.heldItem = weapon;
    }

    public String getHeldItem() {
        System.out.println(this.getName() + " is holding " + this.heldItem.getName());
        return this.heldItem.getName();
    }

    public void checkAlive() {
        if (this.hitPoints <= 0) {
            this.alive = false;
            System.out.println(this.getName() + " has DIED!");
        } else {
            System.out.println(this.getName() + " survives");
        }
    }

    public void receiveHit(double damage) {
        double modifiedDamage = damage * (1 - this.wearable.getDamageNegation());
        System.out.println(this.wearable.getName() + " reduced damage taken to " + modifiedDamage);
        this.hitPoints -= modifiedDamage;
        System.out.println(this.getName() + " was hit for " + damage + " HP");
        checkAlive();
    }


    public double getRandomNumber() {
        Random random = new Random();
        double randomValue = random.nextDouble();
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Random value: " + df.format(randomValue));
        return randomValue;
    }
    public double getRandomNumber(int seed) {
        Random random = new Random(seed);
        double randomValue = random.nextDouble();
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Random value: " + df.format(randomValue));
        return randomValue;
    }

    public void giveHit(Entity entity) {
        double modifiedDamage = this.strength * this.heldItem.getDamageModifier();
        double modifiedAccuracy = this.accuracy * this.heldItem.getAccuracyModifier();
        System.out.println("Modified accuracy is " + modifiedAccuracy);
        System.out.println(this.getName() + " is holding a " + this.heldItem.getName() + " with a damage modifier of " + this.heldItem.getDamageModifier());
        System.out.println("The item changed the damage dealt from " + this.strength + " to " + modifiedDamage);
        double randomValue = this.getRandomNumber();
        if (modifiedAccuracy > randomValue) {
            System.out.println("He strikes successfully");
            System.out.println(this.getName() + " hit " + entity.getName() + " for " + modifiedDamage + " HP");
            entity.receiveHit(modifiedDamage);
        } else {
            System.out.printf("He fails to connect");
        }
    }

    public void giveHit(Entity entity, int seed) {
        double modifiedDamage = this.strength * this.heldItem.getDamageModifier();
        double modifiedAccuracy = this.accuracy * this.heldItem.getAccuracyModifier();
        System.out.println("Modified accuracy is " + modifiedAccuracy);
        System.out.println(this.getName() + " is holding a " + this.heldItem.getName() + " with a damage modifier of " + this.heldItem.getDamageModifier());
        System.out.println("The item changed the damage dealt from " + this.strength + " to " + modifiedDamage);
        double randomValue = this.getRandomNumber(seed);
        if (modifiedAccuracy > randomValue) {
            System.out.println("He strikes successfully");
            System.out.println(this.getName() + " hit " + entity.getName() + " for " + modifiedDamage + " HP");
            entity.receiveHit(modifiedDamage);
        } else {
            System.out.printf("He fails to connect");
        }
    }


    public void wearNewItem(Wearable wearable) {
        this.wearable = wearable;
        System.out.println(this.name + " has slipped on their " + this.wearable.getName());
    }

    public void pickUpConsumable(Consumable consumable) {
        this.consumables.add(consumable);
    }

    public int getNumConsumables() {
        return this.consumables.size();
    }

    public void imbibe(Consumable consumable) {
        if (consumables.contains(consumable)) {
            System.out.println(this.getName() + " has consumed " + consumable.getPotionName());
            this.hitPoints += Math.min(consumable.getHpImpact(), this.hitPointsLimit - this.hitPoints);
            consumables.remove(consumable);
            System.out.println( "Their vitality has changed by up to " + consumable.getHpImpact() + " to " + this.hitPoints);
            checkAlive();
        } else {
            System.out.println(this.getName() + " does not have " + consumable.getPotionName());
        }
    }

    public void ply(Consumable consumable, Entity entity) {
        entity.pickUpConsumable(consumable);
        entity.imbibe(consumable);
    }
}
