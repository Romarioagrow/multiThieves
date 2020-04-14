package app;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
public class Person extends Thread {

    private Bag bag = new Bag();

    public List<Item> getAllBagItems() {
        return this.bag.getItems();
    }

    /*public Person() {
        int itemsAmount = getRandomNumber();

        for (int i = 0; i < itemsAmount; i++) {
            getAllBagItems().add(new Item());
        }
    }*/

    public int getRandomNumber() {
        return (int) (Math.random() * 10 + 1);
    }

    @Override
    public void run() {
        //super.run();
        System.out.println("Я родился!");
    }


}
