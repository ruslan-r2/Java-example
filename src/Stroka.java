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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stroka {
    public static void main(String[] args) {
        List<String> tgMessages = new ArrayList<>();
        tgMessages.add("@Vasya, @rtor ответь на письмо.");
        tgMessages.add("@rtor @vkoba Почему лежит прод?");
        tgMessages.add("Прод подняли.");
        tgMessages.add("@vkoba спасибо.");

        tgMessages.stream()
                .flatMap(str -> Stream.of(str.split("[ ,.!?]")))
                //.peek(System.out::println)
                .map(str -> str.toLowerCase())
                .filter(s -> s.matches("^@(.+)"))
                //.peek(System.out::println)
                .collect(HashMap::new, (HashMap<String, Integer> map, String word) -> map.put(word, map.containsKey(word) ? (map.get(word) + 1) : 1), HashMap::putAll )
                .entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry1.getValue() == entry2.getValue() ? entry1.getKey().compareTo(entry2.getKey()) : entry2.getValue().compareTo(entry1.getValue()))
                .forEach((map) -> System.out.println(map.getKey().replaceAll("@", "") + " " + map.getValue() ));

        tgMessages.stream()
                .map(s -> s.replace(",", " "))
                .flatMap(s -> Stream.of(s.split(" ")))
                .filter(s -> s.startsWith("@"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

}