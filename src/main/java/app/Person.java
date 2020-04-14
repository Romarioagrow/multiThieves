package app;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends Thread {

    private Bag bag = new Bag();

    public List<Item> getAllBagItems() {
        return this.bag.getItems();
    }

    public int getRandomNumber() {
        return (int) (Math.random() * 10 + 1);
    }

    @Override
    public void run() {
        System.out.println("Я родился!");
    }
}
