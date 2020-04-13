package app;

import java.util.ArrayList;
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
    }

    private int getRandomThievesAmount() {
        return (int) (Math.random() * 1000 + 1);
    }
}
