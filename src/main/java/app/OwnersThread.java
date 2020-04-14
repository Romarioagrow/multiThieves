package app;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
public class OwnersThread extends Thread {

    @Override
    @SneakyThrows
    public void run() {
        int ownersAmount = getRandomAmount();
        System.out.println("\n\nOwnersThread RUN, TOTAL OWNERS: " + ownersAmount + "\n\n");

        for (int i = 0; i < ownersAmount; i++) {

            Owner owner = new Owner();
            owner.start();
        }
    }


    public int getRandomAmount() {
        return (int) (Math.random() * 100 + 1);
    }
}

