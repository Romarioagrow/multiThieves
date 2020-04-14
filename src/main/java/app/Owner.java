package app;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Owner extends Person {
    //List<Item> items = new ArrayList<>();

    /*public Owner() {
        *//*int itemsAmount = (int) (Math.random() * 10 + 1);

        for (int i = 0; i < itemsAmount; i++) {
            getAllBagItems().add(new Item());
        }*//*
    }*/

    /*public List<Item> getAllItems() {
        return this.items;
    }*/

    @Override
    public void run() {

        synchronized (House.itemsInHouse) {
            addItemToHouse();
        }

    }

    public synchronized void addItemToHouse() {
        //System.out.println("Owner entered!\nOwners in house now: " + House.ownersInHouse.size());
        //System.out.println("Owner thread: " + Thread.currentThread().getId() + " Time: " + LocalTime.now());

        System.out.println("Owner entered!\nOwner thread: " + + Thread.currentThread().getId() + " Time: " + LocalTime.now());
        System.out.println("Owner has items: " + getAllBagItems().size());

        getAllBagItems().forEach(item -> {
            System.out.println("Owner adding item to house");
            House.itemsInHouse.add(item);
        });

        System.out.println("Owner added all item, total in house: " + House.itemsInHouse.size());

        Thread.yield();


        /*
        getAllBagItems().forEach(item -> {
            System.out.println("Owner adding item to house");
            House.itemsInHouse.add(item);
        });
        System.out.println("Owner added all item, total in house: " + House.itemsInHouse.size());*/



        //ownersInHouse.removeIf(owner -> true);
        //House.ownersInHouse.remove(ownerInHouse);

        //System.out.println("Owner leave");
        //System.out.println("ownersInHouse: " + House.ownersInHouse.size());


        /*ExecutorService executorService = Executors.newFixedThreadPool(House.ownersInHouse.size());
        try
        {
            for (Owner ownerInHouse : House.ownersInHouse) {
                executorService.submit(() -> {

                    System.out.println("Owner thread: " + Thread.currentThread().getId() + " Time: " + LocalTime.now());
                    System.out.println("Owner has items: " + ownerInHouse.getAllItems().size());

                    ownerInHouse.getAllItems().forEach(item -> {
                        System.out.println("Owner adding item to house");
                        House.itemsInHouse.add(item);
                    });

                    //ownersInHouse.removeIf(owner -> true);
                    House.ownersInHouse.remove(ownerInHouse);
                    System.out.println("Owner added all item, total in house: " + House.itemsInHouse.size());
                    System.out.println("Owner leave");
                    System.out.println("ownersInHouse: " + House.ownersInHouse.size());
                });
            }
        }
        finally {
            executorService.shutdown();
        }*/

        /* ConcurrentModificationException при удалении Owner, не может уйти
        ownersInHouse.parallelStream().forEach(ownerInHouse -> {
            System.out.println("Owner has items: " + ownerInHouse.getAllItems().size());
            ownerInHouse.getAllItems().forEach(item -> {
                System.out.println("Owner adding item to house");
                itemsInHouse.add(item);
            });
            //ownersInHouse.removeIf(owner -> true);
            System.out.println("Owner added all item, total in house: " + itemsInHouse.size());
            System.out.println("Owner leave");
            System.out.println("ownersInHouse: " + ownersInHouse.size());
            Thread.sleep(500);
            //exitOwner.add(ownerInHouse);
        });
        ownersInHouse.removeAll(exitOwner);
        */

    }


    /*public static void addItem(Item item) {
        System.out.println("Add item to house: " + item.toString());
    };

    public void showOwnerItemsInfo() {
        System.out.println("Owner items: " + items.size());
        items.forEach(item -> {
            System.out.println("\nOwner`s item value: " + item.getValue());
            System.out.println("Owner`s item weight: " + item.getWeight());
        });
    }

    public int itemsSize() {
        return this.items.size();
    }

    public boolean hasItemsToAdd() {
        return this.items.size() > 0;
    }

    public Item getItem(int index) {
       return this.items.get(index);
    }
    */
}
