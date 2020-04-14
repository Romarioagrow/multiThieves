package app;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.java.Log;

import java.time.LocalTime;

@Log
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Owner extends Person {

    public Owner() {
        int itemsAmount = getRandomNumber();

        for (int i = 0; i < itemsAmount; i++) {
            getAllBagItems().add(new Item());
        }
    }

    @Override
    @SneakyThrows
    public void run() {
        boolean notEntering = true;

        while(notEntering) {
            if (House.noThievesInHouse()) {

                addItemToHouse();
                notEntering = false;
            }
            else
            {
                log.info("\nHouse is busy by Thief! Owner waiting!");
                Thread.sleep(500);
            }
        }
    }

    @SneakyThrows
    public void addItemToHouse() {
        synchronized (House.itemsInHouse) {

            House.peopleInHouse.add(this);

            System.out.println("\nNo thieves on house, Owner entered!\nOwner thread: " + Thread.currentThread().getId() + " Time: " + LocalTime.now());
            System.out.println("Owner has items: " + getAllBagItems().size());

            getAllBagItems().forEach(item -> {
                System.out.println("Owner adding item to house");
                House.itemsInHouse.add(item);
            });

            System.out.println("Owner added all item, total in house: " + House.itemsInHouse.size());
            House.peopleInHouse.remove(this);
        }
    }
}
