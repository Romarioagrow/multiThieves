package app;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ThievesThread extends Thread {
    public static House house;
    //public List<Thief> allThieves = new ArrayList<>();

    public ThievesThread(/*House house*/) {

        //ThievesThread.house = house;

        /*for (int i = 0; i < getRandomThievesAmount(); i++) {
            allThieves.add(new Thief());
        }

        System.out.println("Total thieves: " + allThieves.size());*/
    }

    //@SneakyThrows
    @SneakyThrows
    @Override
    public void run() {
        int thievesAmount = getRandomAmount();
        System.out.println("\n\nThievesThread RUN, TOTAL THIEVES: " + thievesAmount + "\n\n");

        //int ownersAmount = getRandomAmount();

        //synchronized (house.itemsInHouse) {

        for (int i = 0; i < thievesAmount; i++) {


            //synchronized (House.locker) {

            //System.out.println("House.locker thief : " + House.locker.isLocked());

            ///boolean notEntering = true;

            ///while (notEntering) {


            ///System.out.println("noOneInHouse" + House.noOneInHouse());
            ///if (House.noOneInHouse()) {

            /// notEntering = false;
            Thief thief = new Thief();
            thief.start();

            //}
                /*else
                {
                    System.out.println("Thief see other thief in house, waiting...");

                    Thread.sleep(500);
                }*/

            // }


            //}


            /// if house.persons содержит thief to ждать

        }

        //}

        //thievesCycle();

    }

    public int getRandomAmount() {
        return (int) (Math.random() * 100 + 1);
    }


    @SneakyThrows
    public synchronized void thievesCycle() {

        /*for (Thief thief : allThieves)
        {
            boolean thiefNotEntering = true;

            //House.locker.lock();

            while (thiefNotEntering) {
                try {
                    System.out.println("\nAre some owners or other thieves in the house?");

                    if (House.ownersInHouse.isEmpty()*//* && House.thievesInHouse.isEmpty()*//*) {

                        try {

                            House.locker.lock();

                            System.out.println("No ownersInHouse and no thievesInHouse, Thief ready!");
                            thiefNotEntering = false;

                            House.thievesInHouse.add(thief);
                            thief.stealItemFromHouse();

                            House.thievesInHouse.remove(thief);
                            System.out.println("Thief leaving.");
                            //Thread.sleep(500);
                            //notifyAll();
                            //House.stealItemFromHouse();
                            //Thread.sleep(1000);

                        }
                        finally {
                            House.locker.unlock();
                        }
                    }
                    else
                    {
                        System.out.println("Somebody in, need to wait!\n");
                        //wait();
                        Thread.sleep(500);
                    }
                }
                catch (ConcurrentModificationException e) {
                    e.printStackTrace();
                    break;
                }
                *//*finally {
                    House.locker.unlock();
                }*//*
         *//*catch (InterruptedException e) {
                    e.printStackTrace();
                }*//*
         *//*finally {
                    House.locker.unlock();
                }*//*
         *//*catch (InterruptedException e) {
                    e.printStackTrace();
                }*//*
                //}
            }
        }*/

    }

    private int getRandomThievesAmount() {
        return (int) (Math.random() * 1000 + 1);
    }
}
