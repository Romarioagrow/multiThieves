package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ThievesThread extends Thread {
    public static House house;

    List<Thief> thieves = new ArrayList<>();

    public ThievesThread(House house) {
        ThievesThread.house = house;

        for (int i = 0; i < getRandomThievesAmount(); i++) {
            thieves.add(new Thief());
        }
    }


    @Override
    public void run() {
        System.out.println("Total thieves: " + thieves.size());

        for (Thief thief : thieves) {

            try
            {
                House.stealItem(thief);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*int thievesAmount = (int) (Math.random() * 10000 + 1);
        int count = 0;

        System.out.println("Total thieves: " + thievesAmount);

        //notify();
        while (count < thievesAmount)
        {
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
        }*/
    }



    private int getRandomThievesAmount() {
        return (int) (Math.random() * 1000 + 1);
    }
}
