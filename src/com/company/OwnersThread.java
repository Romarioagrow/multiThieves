package com.company;

import java.util.ArrayList;
import java.util.List;

public class OwnersThread extends Thread {
    public static House house;

    List<Owner> owners = new ArrayList<>();

    public OwnersThread(House house) {
        OwnersThread.house = house;

        for (int i = 0; i < getRandomOwnersAmount(); i++) {
            owners.add(new Owner());
        }
    }

    @Override
    public void run() {
        System.out.println("Total owners: " + owners.size());

        for (Owner owner : owners) {

            try
            {
                House.addItem(owner);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getRandomOwnersAmount() {
        return (int) (Math.random() * 1000 + 1);
    }
}

