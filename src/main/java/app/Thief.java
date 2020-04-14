package app;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*Вор; атрибуты: рюкзак. Действия: сложить вещи в рюкзак.*/
public class Thief extends Person/*extends Thread*/ {

    //private Bag bag = new Bag();

   /* public Bag getBag() {
        return this.bag;
    }*/


    @Override
    public void run() {
        //super.run();

        synchronized (House.itemsInHouse) {

            stealItemFromHouse();

        }

    }



    public synchronized void stealItemFromHouse() {
        System.out.println("Thief stealing");

        House.locker.lock();
        System.out.println("House locked!");


        //Thief thiefInHouse = thievesInHouse.get(0);
        List<Item> itemsToSteal = House.itemsInHouse.stream().sorted(Comparator.comparingDouble(Item::getValue).reversed()).collect(Collectors.toList());//sort(Comparator.comparingDouble(Item::getValue).reversed());

        itemsToSteal.stream()
                .takeWhile(item -> this.getBagCurrentWeight() + item.getWeight() < this.getBagTotalWeight())
                .forEach(this::stealItem);

        House.itemsInHouse.removeAll(this.getBag().getItems());

        this.showStealProfit();
        System.out.println("\nItems in house after stealing: " + House.itemsInHouse.size());

        House.locker.unlock();
        System.out.println("House unlocked, thief leave!");

        Thread.yield();

        //thievesInHouse.remove(thiefInHouse);
        //System.out.println("Thief leaving.");
    }





    public void stealItem(Item item) {
        getAllBagItems().add(item);
    }

    public void showStealProfit() {
        List<Item> bagItems = getAllBagItems();

        System.out.println("Thief's items in the bag: " + bagItems.size());
        bagItems.forEach(item -> {
            System.out.format("\nValue: %s, Weight: %s", item.value, item.weight);
        });
    }

    public double getBagTotalWeight() {
        return this.getBag().getTotalWeight();
    }

    public double getBagCurrentWeight() {
        return this.getAllBagItems().stream().mapToDouble(Item::getWeight).sum();
    }





    /*public static void stoleItem(Item item) {
        System.out.println("Stolen item: " + item.toString());
    }

    public double getBagTotalValue() {
        return this.bag.getBagValue();
    }*/
}

