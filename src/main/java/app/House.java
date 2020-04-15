package app;

import lombok.Data;

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
    public static List<Item> itemsInHouse = Collections.synchronizedList(new ArrayList<>());
    public static List<Person> peopleInHouse = Collections.synchronizedList(new ArrayList<>());
    public static ReentrantLock locker = new ReentrantLock();

    public static boolean noOneInHouse() {
        return peopleInHouse.isEmpty();
    }

    public static boolean noThievesInHouse() {
        try {

            synchronized (peopleInHouse) {

                if (peopleInHouse.isEmpty()) return true;

                for (Person person : peopleInHouse) {
                    if (person.getClass().getName().equals("Thief")) return false;
                }
                return true;
            }
            /*Iterator<Person> personIterator = peopleInHouse.iterator();
            while (personIterator.hasNext()) {
                if (personIterator.next().getClass().getName().equals("Thief")) return false;
            }
            return true;*/
        }
        catch (ConcurrentModificationException e) {
            e.printStackTrace();
            return false;
        }

    }



    public static int getPeopleInHouseAmount() {
        return peopleInHouse.size();
    }
}


