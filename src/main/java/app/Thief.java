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
            //System.out.println("\nThief looking in the house...");

            if (House.noOneInHouse()) {
                stealItemFromHouse();
                notEntering = false;
            }
            else
            {
                log.info("\nThief see other thief in house, waiting...");
                Thread.sleep(500);
            }

        }
    }

    @SneakyThrows
    public void stealItemFromHouse() {
        synchronized (House.itemsInHouse) {
            House.peopleInHouse.add(this);
            System.out.println("\nNo owners or other thieves!\nThief stealing! Thief Thread: " + Thread.currentThread().getId() + " Time: " + LocalTime.now());

            List<Item> itemsToSteal = House.itemsInHouse.stream().sorted(Comparator.comparingDouble(Item::getValue).reversed()).collect(Collectors.toList());//sort(Comparator.comparingDouble(Item::getValue).reversed());

            itemsToSteal.stream()
                    .takeWhile(item -> this.getBagCurrentWeight() + item.getWeight() < this.getBagTotalWeight())
                    .forEach(this::stealItem);

            House.itemsInHouse.removeAll(getAllBagItems());

            this.showStealProfit();
            System.out.println("Items in house after stealing: " + House.itemsInHouse.size());

            House.peopleInHouse.remove(this);
            System.out.println("Thief LEAVE! Thief Thread: " + Thread.currentThread().getId() + " Time: " + LocalTime.now());
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

