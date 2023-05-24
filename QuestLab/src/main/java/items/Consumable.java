package items;

public enum Consumable {

    WEAKPOTION("Weak Potion", 5.0),
    POTIONPOTION("Potion Potion", 11.0),
    BULKYPOTION("Bulky Potion", 18.5),
    BULLPOISON("Bull Poison", -15.0);

    private final String name;

    private final double hpImpact;

    Consumable(String name, double hpImpact) {
        this.name = name;
        this.hpImpact = hpImpact;
    }

    public String getPotionName() {
        return this.name;
    }

    public double getHpImpact() {
        return this.hpImpact;
    }
}
