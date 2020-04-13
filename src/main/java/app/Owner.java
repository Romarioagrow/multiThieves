package app;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    /* Хозяин; атрибуты: Вещи; действия: внести вещи в квартиру*/
    List<Item> items = new ArrayList<>();

    /*Add random owner Items*/
    public Owner() {
        int itemsAmount = (int) (Math.random() * 10 + 1);
        for (int i = 0; i < itemsAmount; i++) {
            this.items.add(new Item());
        }
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

    public List<Item> getAllItems() {
        return this.items;
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
