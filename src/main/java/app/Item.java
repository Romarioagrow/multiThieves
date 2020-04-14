package app;

import lombok.Data;
import lombok.ToString;

/*Вещь; атрибуты: вес, ценность*/

@Data
@ToString
public class Item {
    public double value, weight;

    public Item() {
        this.value = (Math.random() * 1000 + 1);
        this.weight = (Math.random() * 10 + 1);
    }
}
