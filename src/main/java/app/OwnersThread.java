package app;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class OwnersThread extends Thread {
    public static House house;

    List<Owner> allOwners = new ArrayList<>();

    public OwnersThread(House house) {
        OwnersThread.house = house;

        for (int i = 0; i < getRandomOwnersAmount(); i++) {
            allOwners.add(new Owner());
        }

        System.out.println("Total owners: " + allOwners.size());
    }

    @SneakyThrows
    @Override
    public void run() {
        for (Owner owner : allOwners)
        {
            boolean ownerNotEntering = true;
            while(ownerNotEntering)
            {
                System.out.println("\nSomeone in?");

                if (House.thievesInHouse.isEmpty())
                {
                    System.out.println("No thieves, Owner entering..");
                    House.ownersInHouse.add(owner);
                    House.addItemToHouse();
                    Thread.sleep(100);
                    ownerNotEntering = false;
                }
                else
                {
                    System.out.println("Thief in the House! Waiting...\n");
                    Thread.sleep(1000);
                }
            }
        }
    }

    private int getRandomOwnersAmount() {
        return (int) (Math.random() * 1000 + 1);
    }
}

