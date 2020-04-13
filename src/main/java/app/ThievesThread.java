package app;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ThievesThread extends Thread {
    public static House house;
    public List<Thief> allThieves = new ArrayList<>();

    public ThievesThread(House house) {
        ThievesThread.house = house;

        for (int i = 0; i < getRandomThievesAmount(); i++) {
            allThieves.add(new Thief());
        }

        System.out.println("Total thieves: " + allThieves.size());
    }

    @SneakyThrows
    @Override
    public void run() {
        for (Thief thief : allThieves)
        {
            boolean thiefNotEntering = true;
            while(thiefNotEntering)
            {
                try
                {
                    System.out.println("\nAre some owners or other thieves in the house?");

                    if (House.ownersInHouse.isEmpty() && House.thievesInHouse.isEmpty())
                    {
                        System.out.println("No ownersInHouse and no thievesInHouse, Thief ready!");
                        House.thievesInHouse.add(thief);
                        House.stealItemFromHouse();
                        Thread.sleep(1000);
                        thiefNotEntering = false;
                    }
                    else
                    {
                        System.out.println("Somebody in, need to wait!\n");
                        Thread.sleep(500);
                    }
                }
                catch (InterruptedException | ConcurrentModificationException e) {
                    e.printStackTrace();
                    break;
                }
            }

        }
    }

    private int getRandomThievesAmount() {
        return (int) (Math.random() * 1000 + 1);
    }
}
