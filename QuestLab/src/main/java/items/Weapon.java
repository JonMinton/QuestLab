package items;

public enum Weapon {
    SWORD("Sword", 1.0, 100, 1.0),
    AXE("Axe", 1.25, 80, 0.8),
    SPEAR("Spear", 0.8, 50, 1.2),
    SLEDGEHAMMER("Sledgehammer", 2.0, 80, 0.5),
    FEATHER("Feather", 0.1, 15, 1.5),
    FISTS("Fists", 0.5, 100, 0.9);

    private final String name;
    private final double damageModifier;
    private final int durability;

    private final double accuracyModifier;



    Weapon(String name, double damageModifier, int durability, double accuracyModifier) {
        this.name = name;
        this.damageModifier = damageModifier;
        this.durability = durability;
        this.accuracyModifier = accuracyModifier;
    }

    public String getName() {
        return name;
    }

    public double getDamageModifier() {
        return damageModifier;
    }

    public int getDurability() {
        return durability;
    }

    public double getAccuracyModifier() {
        return accuracyModifier;
    }

}
