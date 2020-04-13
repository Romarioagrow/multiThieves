package app;

public class Bag {
    /// Рюкзак; атрибуты: предельный вес
    private final double totalWeight = 20;
    private double bagWeight = 0, bagValue = 0;

    public Bag() {
    }

    public void addItemToBag(Item item) {
        setBagWeight(getBagWeight() + item.getWeight());
        setBagValue(getBagValue() + item.getValue());
    }

    public void setBagWeight(double bagWeight) {
        this.bagWeight = bagWeight;
    }

    public void setBagValue(double bagValue) {
        this.bagValue = bagValue;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public double getBagWeight() {
        return bagWeight;
    }

    public double getBagValue() {
        return bagValue;
    }
}
