package app;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Owner {
    List<Item> items = new ArrayList<>();

    public Owner() {
        int itemsAmount = (int) (Math.random() * 10 + 1);
        for (int i = 0; i < itemsAmount; i++) {
            this.items.add(new Item());
        }
    }

    public List<Item> getAllItems() {
        return this.items;
    }

    public static void addItem(Item item) {
        System.out.println("Add item to house: " + item.toString());
    };

    public void showOwnerItemsInfo() {
        System.out.println("Owner items: " + items.size());
        items.forEach(item -> {
            System.out.println("\nOwner`s item value: " + item.getValue());
            System.out.println("Owner`s item weight: " + item.getWeight());
        });
    }

    public int itemsSize() {
        return this.items.size();
    }

    public boolean hasItemsToAdd() {
        return this.items.size() > 0;
    }

    public Item getItem(int index) {
        return this.items.get(index);
    }
}
