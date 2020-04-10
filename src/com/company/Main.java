package com.company;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
 /*
Задача:
Необходимо реализовать многопоточное приложение, которое решает следующую задачу:

Есть два типа пользователя (два типа потока).
Один - Хозяин, имеет в своем арсенале список вещей (Вещь: цена и вес),
второй - Вор, имеет рюкзак (Рюкзак: предельный вес, который может в себя вместить).
Поток Хозяина выполняет работу по выкладыванию вещей в квартиру. Поток Вора - забирает вещи из квартиры.
При этом Вор должен забрать такие вещи, чтобы их ценность была максимальной и вес их должен быть меньше предельного веса, который может поместиться в рюкзак.

Объектные модели:
1. Вещь; атрибуты: вес, ценность
2. Хозяин; атрибуты: Вещи; действия: внести вещи в квартиру
3. Рюкзак; атрибуты: предельный вес
3. Вор; атрибуты: рюкзак. Действия: сложить вещи в рюкзак.

Ограничения:
1. Если работает поток Хозяина, то вор не должен класть вещи в рюкзак.
2. Если работает Вор, то Хозяин не может войти в квартиру

Возможные ограничения системы:
1. Хозяев может быть 1..n.
2. потоки Хозяев БЕЗ взаимной блокировки: несколько хозяев могут выкладывать вещи в квартиру одновременно
3. Воров может быть 1..m.
4. Потоки Воров со ВЗАИМНОЙ блокировкой: воровать одновременно может только 1 вор."

Обычно тут не понимают следующее:
Воры и хозяева создаются , инициализируются, и запускаются полностью асинхронно, т.е. независимо друг от друга.

Никто не знает кто первый прийдет в дом, хозяин или вор

Хозяева создаются со случайным набором предметов.
Как только появляется возможность они заходят в дом и выкладывают поочереди по-одному предметы со своего набора.

Воры создаются с пустым рюкзаком. Как только появляется возможность,
заходят в дом и берут предметов влазящих по весу в рюкзак желательно как можно выгоднее по цене и уходят

Программа работает пока все потоки не завершатся
*/

    public static void main(String[] args) {
        House house = new House();
        new OwnersThread(house).start();
        new ThievesThread(house).start();

       /*
       List<Item> house = Collections.synchronizedList(new ArrayList<>());
       synchronized (house) {
            new OwnersThread(house).start();
            new ThievesThread(house).start();
        }
        */
    }
}
