package app;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*Вор; атрибуты: рюкзак. Действия: сложить вещи в рюкзак.*/
@Log
public class Thief extends Person {

    @Override
    @SneakyThrows
    public void run() {
        boolean notEntering = true;

        while (notEntering) {
            if (House.noOneInHouse()) {
                House.locker.lock();
                System.out.println("\nHouse is locked by Thief");

                stealItemFromHouse();
                notEntering = false;

                House.locker.unlock();
                System.out.println("House is unlocked!");
            }
            else
            {
                System.out.println("\nThread: " + Thread.currentThread().getId() + ", Thief see someone in house, waiting...\n");
                Thread.sleep(500);
            }
        }
    }

    @SneakyThrows
    public void stealItemFromHouse() {
        synchronized (House.itemsInHouse) {

            System.out.println("People in house before thief enter: " + House.peopleInHouse.size());
            House.peopleInHouse.add(this);

            /*House.locker.lock();
            System.out.println("House is locked by Thief");*/

            System.out.println("No owners or other thieves!\nThief stealing! Thief Thread: " + Thread.currentThread().getId() + " Time of start: " + LocalTime.now());

            List<Item> itemsToSteal = House.itemsInHouse.stream().sorted(Comparator.comparingDouble(Item::getValue).reversed()).collect(Collectors.toList());//sort(Comparator.comparingDouble(Item::getValue).reversed());

            if (!itemsToSteal.isEmpty())
            {
                itemsToSteal.stream()
                        .takeWhile(item -> this.getBagCurrentWeight() + item.getWeight() < this.getBagTotalWeight())
                        .forEach(this::stealItem);

                House.itemsInHouse.removeAll(getAllBagItems());
                this.showStealProfit();
            }
            else
            {
                System.out.println("Nothing to steal here... :(");
            }
            System.out.println("Items in house after stealing: " + House.itemsInHouse.size());

            House.peopleInHouse.remove(this);
            System.out.println("Thief LEAVE! Thief Thread: " + Thread.currentThread().getId() + " Time of end: " + LocalTime.now());

            /*House.locker.unlock();
            System.out.println("House is unlocked!");*/
            //Thread.sleep(100);
        }
    }

    public void stealItem(Item item) {
        getAllBagItems().add(item);
    }

    public void showStealProfit() {
        List<Item> bagItems = getAllBagItems();

        System.out.println("Thief's items in the bag: " + bagItems.size());
        bagItems.forEach(item -> {
            ///System.out.format("Value: %s, Weight: %s", item.value, item.weight);
            System.out.println("Value: " + item.value + " Weight: " + item.weight);
        });
    }

    public double getBagTotalWeight() {
        return this.getBag().getTotalWeight();
    }

    public double getBagCurrentWeight() {
        return this.getAllBagItems().stream().mapToDouble(Item::getWeight).sum();
    }
}

