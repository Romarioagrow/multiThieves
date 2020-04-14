package app;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
public class ThievesThread extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        int thievesAmount = getRandomAmount();
        System.out.println("\n\nThievesThread RUN, TOTAL THIEVES: " + thievesAmount + "\n\n");

        for (int i = 0; i < thievesAmount; i++) {

            Thief thief = new Thief();
            thief.start();

        }
    }

    public int getRandomAmount() {
        return (int) (Math.random() * 100 + 1);
    }
}
