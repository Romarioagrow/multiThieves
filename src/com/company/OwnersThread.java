package com.company;

import java.util.List;

public class OwnersThread extends Thread {
    public static List<Item> house;

    public OwnersThread(List<Item> house) {
        OwnersThread.house = house;
    }

    @Override
    public void run() {
        int ownerAmount = (int) (Math.random() * 10000 + 1);
        int count = 0;

        System.out.println("Total owners: " + ownerAmount);

        while (count < ownerAmount) {
            Owner owner = new Owner();

            for (int i = 0; i < owner.itemsSize(); i++) {
                house.add(owner.getItem(i));
            }

            System.out.println("Items in house after add: " + house.size());
            count++;
        }
    }
}

