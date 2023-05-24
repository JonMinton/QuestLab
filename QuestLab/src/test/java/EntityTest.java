import items.Consumable;
import items.Weapon;
import items.Wearable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityTest {

    Entity entityStrong;
    Entity entityAccurate;

    @Before
    public void setUp() {
        entityStrong = new Entity("Bob the barbarian", 20, 8, 0.45);
        entityAccurate = new Entity("Sneaky Steve", 11, 4, 0.9);
    }

    @Test
    public void hasName() {
        assertEquals("Bob the barbarian", entityStrong.getName());
    }

    @Test
    public void initiallyWearsNothing() {
        assertEquals("Nothing", entityStrong.getWearableName());
    }

    @Test
    public void canWearSomethingElse() {

       assertEquals("Nothing", entityAccurate.getWearableName());
        Wearable robes = Wearable.ROBES;
       entityAccurate.wearNewItem(robes);
       assertEquals("Robes", entityAccurate.getWearableName());
    }

    @Test
    public void canGetHp() {
        assertEquals(20, entityStrong.getHitPoints(), 0.01);
    }

    @Test
    public void canGetGetHit(){
        assertEquals(20, entityStrong.getHitPoints(), 0.01);
        entityStrong.receiveHit(5);
        assertEquals(15, entityStrong.getHitPoints(), 0.01);
    }

    @Test
    public void canSurviveBeingHitMeakly() {
        assertTrue(entityStrong.getAliveStatus());
        assertEquals(20, entityStrong.getHitPoints(), 0.01);
        entityStrong.receiveHit(3);
        assertTrue(entityStrong.getAliveStatus());
        assertEquals(17, entityStrong.getHitPoints(), 0.01);
    }

    @Test
    public void canBeKilled() {
        assertTrue(entityStrong.getAliveStatus());
        assertEquals(20, entityStrong.getHitPoints(), 0.01);
        entityStrong.receiveHit(21);
        assertFalse(entityStrong.getAliveStatus());
        assertEquals(-1, entityStrong.getHitPoints(), 0.01);
    }

    @Test
    public void canMissOtherEntity() {
        assertEquals(11.0, entityAccurate.getHitPoints(), 0.01);
        entityStrong.giveHit(entityAccurate, 999);
//        He will fail to hit every time because of the seed value
        assertEquals(11.00, entityAccurate.getHitPoints(), 0.01 );
    }

    @Test
    public void canSuccessfullyHitOtherEntity() {
        assertEquals(11.0, entityAccurate.getHitPoints(), 0.01);
        entityStrong.giveHit(entityAccurate, 999999);
//        He will hit every time because of the seed value
        assertEquals(7.00, entityAccurate.getHitPoints(), 0.01 );
    }

    @Test
    public void canGetItem() {
        assertEquals("Fists", entityStrong.getHeldItem());
    }

    @Test
    public void canChangeItem() {
        entityStrong.changeHeldItem(Weapon.SWORD);
        assertEquals("Sword", entityStrong.getHeldItem());
    }



    @Test
    public void getGetSeededRandomNumbers() {
        assertEquals(0.6800, entityStrong.getRandomNumber(600), 0.0001);
        assertEquals(0.72301, entityStrong.getRandomNumber(60000), 0.0001);
        assertEquals(0.08187, entityStrong.getRandomNumber(99999999), 0.00001 );
    }

    @Test
    public void wearingClothesReducesDamage() {
        entityStrong.receiveHit(5);
        assertEquals(15, entityStrong.getHitPoints(), 0.0);
        entityStrong.wearNewItem(Wearable.GIMPSUIT);
        entityStrong.receiveHit(5);
        assertEquals(13.45, entityStrong.getHitPoints(), 0.0);
    }

    @Test
    public void canPickUpConsumables(){
        assertEquals(0, entityStrong.getNumConsumables());
        entityStrong.pickUpConsumable(Consumable.BULLPOISON);
        assertEquals(1, entityStrong.getNumConsumables());
    }

    @Test
    public void canImbibeIfHasItem() {
        assertEquals(20, entityStrong.getHitPoints(),
                0.0);
        entityStrong.pickUpConsumable(Consumable.BULLPOISON);
        entityStrong.imbibe(Consumable.BULLPOISON);
        assertEquals(5, entityStrong.getHitPoints(), 0.0);
    }

    @Test
    public void ifItemConsumedCannotUseItAgain() {
        assertEquals(20, entityStrong.getHitPoints(), 0.01);
        entityStrong.receiveHit(10);
        assertEquals(10, entityStrong.getHitPoints(), 0.01);
        entityStrong.pickUpConsumable(Consumable.WEAKPOTION);
        entityStrong.imbibe(Consumable.WEAKPOTION);
        assertEquals(15, entityStrong.getHitPoints(), 0.01);
        entityStrong.imbibe(Consumable.WEAKPOTION);
        assertEquals(15, entityStrong.getHitPoints(), 0.01);
    }

    @Test
    public void canOnlyHealUpToThreshold() {
        entityStrong.pickUpConsumable(Consumable.WEAKPOTION);
        entityStrong.imbibe(Consumable.WEAKPOTION);
        assertEquals(24, entityStrong.getHitPoints(), 0.01);
    }



    @Test
    public void canImbibeIfHasItemAndDie() {
        assertEquals(20, entityStrong.getHitPoints(),
                0.0);
        entityStrong.pickUpConsumable(Consumable.BULLPOISON);
        entityStrong.imbibe(Consumable.BULLPOISON);
        assertEquals(5, entityStrong.getHitPoints(), 0.0);
        entityStrong.pickUpConsumable(Consumable.BULLPOISON);
        entityStrong.imbibe(Consumable.BULLPOISON);
        assertEquals(-10, entityStrong.getHitPoints(), 0.0);
        assertFalse(entityStrong.getAliveStatus());

    }

    @Test
    public void canPlyConsumables() {
        entityAccurate.ply(Consumable.BULLPOISON, entityStrong);
        assertEquals(5, entityStrong.getHitPoints(), 0.0);

    }

    @Test
    public void canPlyToDeath() {
        entityAccurate.ply(Consumable.BULLPOISON, entityStrong);
        entityAccurate.ply(Consumable.BULLPOISON, entityStrong);
        assertFalse(entityStrong.getAliveStatus());
    }

}
