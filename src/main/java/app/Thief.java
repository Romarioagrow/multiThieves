package app;

import java.util.List;

/// Вор; атрибуты: рюкзак. Действия: сложить вещи в рюкзак.
public class Thief extends Thread {

    private Bag bag = new Bag();

    public Bag getBag() {
        return this.bag;
    }

    public void stealItem(Item item) {
        this.bag.getItems().add(item);
        //showStealProfit();
    }

    public void showStealProfit() {
        List<Item> bagItems = this.bag.getItems();

        System.out.println("Thief's items in the bag: " + bagItems.size());
        bagItems.forEach(item -> {
            System.out.format("\nValue: %s, Weight: %s", item.value, item.weight);
        });
    }

    public static void stoleItem(Item item) {
        System.out.println("Stolen item: " + item.toString());
    }
    public double getBagTotalWeight() {
        return this.bag.getTotalWeight();
    }

    public double getBagCurrentWeight() {
        //return this.bag.getBagWeight();
        return this.bag.getItems().stream().mapToDouble(Item::getWeight).sum();
        /*return*/

    }

    public double getBagTotalValue() {
        return this.bag.getBagValue();
    }
}

