package app;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Bag {
    /*Рюкзак; атрибуты: предельный вес*/

    List<Item> items = new ArrayList<>();

    private final double totalWeight = 20;

    private double bagWeight = 0, bagValue = 0;
}
