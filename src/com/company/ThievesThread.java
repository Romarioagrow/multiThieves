package com.company;

import java.util.Comparator;
import java.util.List;

public class ThievesThread extends Thread {
    public static List<Item> house;

    public ThievesThread(List<Item> house) {
        ThievesThread.house = house;
    }

    @Override
    public void run() {
        int thievesAmount = (int) (Math.random() * 10000 + 1);
        int count = 0;

        System.out.println("Total thieves: " + thievesAmount);

        //notify();
        while (count < thievesAmount) {
            Thief thief = new Thief();

            System.out.println("\nThief stealing");

            house.sort(Comparator.comparingDouble(Item::getValue));


            for (int i = 0; thief.getBagCurrentWeight() <= thief.getBagTotalWeight() ; i++) {


                if (i < house.size())
                {
                    Item itemToSteal = house.get(i);
                    thief.stealItem(itemToSteal);
                    house.remove(itemToSteal);
                }


            }

            thief.showStealProfit();
            System.out.println("Item in house after steal: " + house.size());

            count++;
        }
    }
}
