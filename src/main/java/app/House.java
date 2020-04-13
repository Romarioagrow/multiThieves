package app;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/*
Нужно обеспечить условие чтобы хозяева могли выкладывать вещи одновременно, но при условии что в этот момент вор не ворует из дома.
Выкладка вещей хозяином в цикле по одной а не addAll

Хозяин сначала должен проверить может ли он войти (если дома есть вор то не может).
Если вошел то выкладывает по одной пока не закончатся вещи и выходит (при этом список его рюкзака после этого должен быть пуст)

Вор должен проверить нет ли дома вора или хозяина - если нет то приходит и берет всё что нужно
Важно соблюсти условие - чтобы вещи не дублировались и не исчезали
Т.е. общая масса вещей перед началом работы и в конце работы должна быть одинаковой.
Просто вначале все вещи находятся в рюкзаках хозяев. А в конце распределены по ворам и дому
*/

public class House {
    public static List<Item> itemsInHouse = Collections.synchronizedList(new ArrayList<>());
    public static List<Owner> ownersInHouse = Collections.synchronizedList(new ArrayList<>());
    public static List<Thief> thievesInHouse = Collections.synchronizedList(new ArrayList<>());

    public static synchronized void addItemToHouse() {
        System.out.println("Owner entered!\nOwners in house now: " + ownersInHouse.size());

        ExecutorService executorService = Executors.newFixedThreadPool(ownersInHouse.size());
        try
        {
            for (Owner ownerInHouse : ownersInHouse) {
                executorService.submit(() -> {

                    System.out.println("Owner thread: " + Thread.currentThread().getId() + " Time: " + LocalTime.now());
                    System.out.println("Owner has items: " + ownerInHouse.getAllItems().size());

                    ownerInHouse.getAllItems().forEach(item -> {
                        System.out.println("Owner adding item to house");
                        itemsInHouse.add(item);
                    });

                    //ownersInHouse.removeIf(owner -> true);
                    ownersInHouse.remove(ownerInHouse);
                    System.out.println("Owner added all item, total in house: " + itemsInHouse.size());
                    System.out.println("Owner leave");
                    System.out.println("ownersInHouse: " + ownersInHouse.size());
                });
            }
        }
        finally {
            executorService.shutdown();
        }

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

    public static synchronized void stealItemFromHouse() throws InterruptedException {
        System.out.println("Thief stealing");

        Thief thiefInHouse = thievesInHouse.get(0);
        itemsInHouse.sort(Comparator.comparingDouble(Item::getValue).reversed());

        itemsInHouse.stream()
                .takeWhile(item -> thiefInHouse.getBagCurrentWeight() + item.getWeight() < thiefInHouse.getBagTotalWeight())
                .forEach(thiefInHouse::stealItem);

        itemsInHouse.removeAll(thiefInHouse.getBag().getItems());

        thiefInHouse.showStealProfit();
        System.out.println("\nItems in house after stealing: " + itemsInHouse.size());

        thievesInHouse.remove(thiefInHouse);
        System.out.println("Thief leaving.");
    }
}


