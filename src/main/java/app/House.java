package app;

import lombok.Data;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/*
Нужно обеспечить условие чтобы хозяева могли выкладывать вещи одновременно, но при условии что в этот момент вор не ворует из дома.
Выкладка вещей хозяином в цикле по одной а не addAll

Хозяин сначала должен проверить может ли он войти (если дома есть вор то не может).
Если вошел то выкладывает по одной пока не закончатся вещи и выходит (при этом список его рюкзака после этого должен быть пуст)

Вор должен проверить нет ли дома вора или хозяина - если нет то приходит и берет всё что нужно
Важно соблюсти условие - чтобы вещи не дублировались и не исчезали
Т.е. общая масса вещей перед началом работы и в конце работы должна быть одинаковой.
Просто вначале все вещи находятся в рюкзаках хозяев. А в конце распределены по ворам и дому
*/
@Data
public class House {
    public static volatile List<Item> itemsInHouse = Collections.synchronizedList(new ArrayList<>());
    public static volatile List<Person> peopleInHouse = Collections.synchronizedList(new ArrayList<>());
    public static volatile ReentrantLock locker = new ReentrantLock();


    public static synchronized boolean isHouseUnlocked() {
        return !locker.isLocked();
    }

    public static synchronized boolean isNobodyInHouse() {

        synchronized (locker) {

            synchronized (peopleInHouse) {

                boolean isNobodyInHouse = !locker.isLocked() && peopleInHouse.isEmpty();

                if (isNobodyInHouse) {

                    lockHouse();

                    System.out.println("No owners or other thieves!\nThief entering house! Thief Thread: " + Thread.currentThread().getId() + " Time of start: " + LocalTime.now());

                    return true;
                }

                return false;

            }

        }
    }


    public static synchronized void lockHouse() {
        locker.lock();
        System.out.println("\nThread: " + Thread.currentThread().getId() + ", House is locked by Thief");
    }

    public static synchronized void unlockHouse() {
        locker.unlock();
    }

    public static int getPeopleInHouseAmount() {
        return peopleInHouse.size();
    }
}


