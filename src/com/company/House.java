package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class House {

    static List<Item> itemsInHouse = Collections.synchronizedList(new ArrayList<>());

    public static synchronized void addItem(Owner owner) throws InterruptedException {
        System.out.println("\nOwner adding");

        itemsInHouse.addAll(owner.getAllItems());
        System.out.println("Total items after add: " + itemsInHouse.size());
        Thread.sleep(500);
    }

    public static synchronized void stealItem(Thief thief) throws InterruptedException {
        System.out.println("\nThief stealing");

        itemsInHouse.sort(Comparator.comparingDouble(Item::getValue).reversed());

        for (int i = 0; i < itemsInHouse.size() ; i++) {

            Item itemToSteal = itemsInHouse.get(i);

            if (thief.getBagCurrentWeight() + itemToSteal.getWeight() < thief.getBagTotalWeight())
            {
                thief.stealItem(itemToSteal);
                itemsInHouse.remove(itemToSteal);
            }
            else break;
        }
        System.out.println("Item in house after steal: " + itemsInHouse.size());

        Thread.sleep(500);
    }


    public static void showItemsInHouseInfo(List<Item> items) {
        System.out.println();
        items.forEach(item -> {
            System.out.println(item.getValue());
        });
    }

}


