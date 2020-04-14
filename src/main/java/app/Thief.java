package app;

import lombok.SneakyThrows;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*Вор; атрибуты: рюкзак. Действия: сложить вещи в рюкзак.*/
public class Thief extends Person/*extends Thread*/ {

    //private Bag bag = new Bag();

   /* public Bag getBag() {
        return this.bag;
    }*/


    /*public Person() {
        int itemsAmount = getRandomNumber();

        for (int i = 0; i < itemsAmount; i++) {
            getAllBagItems().add(new Item());
        }
    }*/


    @SneakyThrows
    @Override
    public void run() {
        //super.run();

        //synchronized (House.itemsInHouse) {




        boolean notEntering = true;

        while (notEntering) {

            if (House.noOneInHouse()) {


                stealItemFromHouse();

                notEntering = false;


            }

            else
            {
                //System.out.println("Thief see other thief in house, waiting...");

                Thread.sleep(500);
            }


        }


        //}

    }



    @SneakyThrows
    public /*synchronized*/ void stealItemFromHouse() {

        //System.out.println("House.locker thief : " + House.locker.isLocked());

        synchronized (House.itemsInHouse) {

            //House.locker.lock();
            House.peopleInHouse.add(this);



            System.out.println("\nNo owners or other thieves!\nThief stealing! Thief Thread: " + Thread.currentThread().getId() + " Time: " + LocalTime.now());

            //Thief thiefInHouse = thievesInHouse.get(0);
            List<Item> itemsToSteal = House.itemsInHouse.stream().sorted(Comparator.comparingDouble(Item::getValue).reversed()).collect(Collectors.toList());//sort(Comparator.comparingDouble(Item::getValue).reversed());

            itemsToSteal.stream()
                    .takeWhile(item -> this.getBagCurrentWeight() + item.getWeight() < this.getBagTotalWeight())
                    .forEach(this::stealItem);

            House.itemsInHouse.removeAll(getAllBagItems());

            this.showStealProfit();
            System.out.println("Items in house after stealing: " + House.itemsInHouse.size());

            //House.locker.unlock();
            //System.out.println("House unlocked, thief leave!");


            House.peopleInHouse.remove(this);
            //System.out.println("Thief leave!");
            //System.out.println("peopleInHouse: " +  House.peopleInHouse.size());
            System.out.println("Thief LEAVE! Thief Thread: " + Thread.currentThread().getId() + " Time: " + LocalTime.now());

            //Thread.yield();
            //Thread.sleep(500);

        }



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
            //System.out.format("Value: %s, Weight: %s", item.value, item.weight);
            System.out.println("Value: " + item.value + " Weight: " + item.weight);
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

