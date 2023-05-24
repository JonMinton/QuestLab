package items;

public enum Wearable {
    CHAINMAIL("Chainmail", 0.60, 90),
    PLATEARMOUR("Plate Armour", 0.90, 100),
    LEATHERARMOUR("Leather Armour", 0.40, 90),
    GIMPSUIT("Gimp Suit", 0.69, 69),
    ROBES("Robes", 0.15, 40),
    NOTHING("Nothing", 0, 0);

    private final String name;
    private final double damageNegation;

    private final int durability;


    public String getName() {
        return name;
    }

    public double getDamageNegation() {
        return damageNegation;
    }

    public int getDurability() {
        return durability;
    }

    Wearable(String name, double damageNegation, int durability) {
        this.name = name;
        this.damageNegation = damageNegation;
        this.durability = durability;
    }


}
