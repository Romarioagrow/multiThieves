package app;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class OwnersThread extends Thread {

    //public static House house;

    //ReentrantLock lock = new ReentrantLock();

    List<Owner> allOwners = new ArrayList<>();

    //static int ownersAmount = 0;

    //




    public OwnersThread(/*House house*/) {
       //this.house = house;

        //OwnersThread.house = house;

        /*this.ownersAmount = getRandomOwnersAmount();

        for (int i = 0; i < getRandomOwnersAmount(); i++) {
            allOwners.add(new Owner());
        }*/



        //System.out.println("Total owners: " + allOwners.size());
    }

    @SneakyThrows
    @Override
    public void run() {

        int ownersAmount = getRandomAmount();

        synchronized (House.itemsInHouse) {

            for (int i = 0; i < ownersAmount; i++) {


                Owner owner = new Owner();
                owner.start();
                /// if house.persons содержит thief to ждать

            }

        }


        //ownersCycle();

    }

    @SneakyThrows
    private synchronized void ownersCycle() {

        int ownersAmount = getRandomAmount();




        /*int counter = 0;
        do {
            Owner owner = this.allOwners.get(counter);
            House.ownersInHouse.add(owner);
            owner.addItemToHouse();
            //House.ownersInHouse.get()
            House.ownersInHouse.remove(owner);
            counter++;
        }
        while (!House.locker.isLocked() && counter < this.allOwners.size());*/

        /*for (Owner owner : allOwners)
        {
            boolean ownerNotEntering = true;

            //House.locker.lock();
            //try {

            while(ownerNotEntering) {
                System.out.println("\nSomeone in?");

                if (House.thievesInHouse.isEmpty())
                //if (!House.locker.isLocked())
                {
                    *//*House.locker.lock();*//*

                    ownerNotEntering = false;

                    System.out.println("No thieves, Owner entering..");

                    House.ownersInHouse.add(owner);
                    owner.addItemToHouse();

                *//*for (Owner ownerInHouse : House.ownersInHouse) {
                    ownerInHouse.addItemToHouse();
                }*//*
                    House.ownersInHouse.remove(owner);
                    *//*House.locker.unlock();*//*
                    Thread.sleep(500);

                    //notifyAll();
                    //Thread.sleep(500);
                    *//*for (Owner ownerInHouse : House.ownersInHouse) {
                        ownerInHouse.addItemToHouse();
                        House.ownersInHouse.remove(ownerInHouse);
                        System.out.println("Owner leave.");
                    }*//*

                    //House.addItemToHouse();
                    //Thread.sleep(100);
                    //вот мне не совсем понятно, Когда именно хоязевы должны выполнять свой цикл расскаладки вещей, когда

                }
                else
                {
                    System.out.println("Thief in the House! Waiting...\n");
                    //Thread.sleep(500);
                    //this.lock.lock();
                }
                //
            }
        }*/
            /*finally {
                House.locker.unlock();
            }*/
        //}
    }

    public int getRandomAmount() {
        return (int) (Math.random() * 1000 + 1);
    }
}

