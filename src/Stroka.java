/*
    На вход идёт список строк. Каждая строка - сообщение из телеграмма, в котором может быть кто-то упомянут.
    Упонимание начинается с символа @ и заканчивается запятой, или пробелом. Например:

    @Vasya, @rtor ответь на письмо
    @rtor @vkoba Почему лежит прод?

    Необходимо реализовать метод, который получает на вход список сообщений, а на выходе возвращает всех упомянутых в них участников,
    отсортированных по частоте упоминания сверху сниз. Если частота упоминания одинакова - порядок неважен. Например, в примере выше,
    результатом работы метода будет выход:

    rtor
    vasya
    vkoba

    Тестовые данные:

    List<String> tgMessages = new ArrayList<>();
    tgMessages.add("@Vasya, @rtor ответь на письмо");
    tgMessages.add("@rtor @vkoba Почему лежит прод?");
    tgMessages.add("Прод подняли");
    tgMessages.add("@vkoba спасибо");
*/

import java.util.*;
import java.util.stream.Stream;

public class Stroka {
    public static void main(String[] args) {
        List<String> tgMessages = new ArrayList<>();
        tgMessages.add("@Vasya, @rtor ответь на письмо.");
        tgMessages.add("@rtor @vkoba Почему лежит прод?");
        tgMessages.add("Прод подняли.");
        tgMessages.add("@vkoba спасибо.");

        tgMessages.stream().flatMap(str -> Stream.of(str.split("[ ,.!?]")))
                //.peek(System.out::println)
                .map(str -> str.toLowerCase())
                .filter(s -> s.matches("^@(.+)"))
                //.peek(System.out::println)
                .collect(HashMap::new, (HashMap<String, Integer> map, String word) -> map.put(word, map.containsKey(word) ? (map.get(word) + 1) : 1), HashMap::putAll )
                .entrySet()
                .stream()
                .sorted((map1, map2) -> map1.getValue() == map2.getValue() ? map1.getKey().compareTo(map2.getKey()) : map2.getValue().compareTo(map1.getValue()))
                .forEach((map) -> System.out.println(map.getKey().replaceAll("@", "") + " " + map.getValue() ));

    }
}