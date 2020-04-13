package app;

import lombok.ToString;

@ToString
public class Item {
    /// Вещь; атрибуты: вес, ценность
    public double value, weight;

    public Item() {
        this.value = (Math.random() * 1000 + 1);
        this.weight = (Math.random() * 10 + 1);
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }
}
