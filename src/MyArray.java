/*
    Дан массив интов и вторым аргументом дано количество возможных повторений значений внутри этого массива.
    Вернуть надо массив интов.
    соответственно....new int[] { 20, 37, 20, 21 }, 1 ответ { 20, 37, 21 }
    { 1, 2, 3, 1, 1, 2, 1, 2, 3, 3, 2, 4, 5, 3, 1 }, 3 ) ответ { 1, 2, 3, 1, 1, 2, 2, 3, 3, 4, 5 }
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;

public class MyArray {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 1, 1, 2, 1, 2, 3, 3, 2, 4, 5, 3, 1};
        int n = 3;

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(array(arr, n)));
        System.out.println(Arrays.toString(countVal(arr, n)));

    }

    // Решение без стрима
    public static int[] array(int[] arr, int count) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (frequency(arr, arr[i]) > count) {
                arr = deletElementFromArray(arr, i);
            }
        }
        return arr;
    }

    // Удаляем элемент из массива по индексу элемента.
    // Метод возвращает новый массив - 1 элемент.
    public static int[] deletElementFromArray(int[] array, int index) {
        int[] copy = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != index) {
                copy[j++] = array[i];
            }
        }
        return copy;
    }

    // Этот метот считает сколько раз встречается elem в переданном массиве arr[].
    public static int frequency(int[] arr, int elem) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == elem) {
                count++;
            }
        }
        return count;
    }

    // Решение при помощи стрима.
    public static int[] countVal(int[] arr, int count) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        for (int i = list.size() - 1; i >= 0; i--) {
            if (Collections.frequency(list, list.get(i)) > count) {
                list.remove(i);
            }
        }
        return list.stream().mapToInt(x -> x).toArray();
    }
}