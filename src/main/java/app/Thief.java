package app;

public class Thief extends Thread{
    /// Вор; атрибуты: рюкзак. Действия: сложить вещи в рюкзак.
    private Bag bag = new Bag();

    public static void stoleItem(Item item) {
        System.out.println("Stolen item: " + item.toString());
    }

    public Bag getBag() {
        return this.bag;
    }

    public void stealItem(Item item) {
        this.bag.addItemToBag(item);
        showStealProfit();
    }

    public double getBagTotalWeight() {
        return this.bag.getTotalWeight();
    }

    public double getBagCurrentWeight() {
        return this.bag.getBagWeight();
    }

    public double getBagTotalValue() {
        return this.bag.getBagValue();
    }

    public void showStealProfit() {
        System.out.println("Thief`s bag value: " + getBagTotalValue());
        System.out.println("Thief`s bag weight: " + getBagCurrentWeight());
    }

}

