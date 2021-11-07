/*
     Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until
     there are no such sequences.
     After doing so, return the head of the final linked list.  You may return any such answer.

     Дан связанный список (дносторонний), т.е. каждый элемент ссылается на следующий.
     Нужно удалить из списка ноды, сумма которых равна 0.
     После этого верните заголовок окончательного связанного списка.

    (Обратите внимание, что все последовательности в следующем примере представляют собой сериализацию объектов ListNode.)
    Пример 1：
    Вход: голова = [1,2,-3,3,1]
    Выход:[3,1]
    Быстрый ответ [1,2,1]  Это тоже правильно.

    Пример 2：
    Вход: голова = [1,2,3,-3,4]
    Выход:[1,2,4]

    Пример 3：
    Вход: голова = [1,2,3,-3,-2]
    Выход:[1]

    Подсказки:
    В предоставленном вам связанном списке могут быть 1  К 1000  Узлы.
    Для каждого узла в связанном списке значение узла:-1000 <= node.val <= 1000.
 */

import java.util.HashMap;

public class MyLinkedList {
    public static void main(String[] args) {
        ListNode n8 = new ListNode(-6);
        ListNode n7 = new ListNode(5, n8);
        ListNode n6 = new ListNode(1, n7);
        ListNode n5 = new ListNode(1, n6);
        ListNode n4 = new ListNode(-6, n5);
        ListNode n3 = new ListNode(4, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(3, n2);

        removeZeroSumSublists(n1);

    }

    static public ListNode removeZeroSumSublists(ListNode head) {
        ListNode temp = new ListNode();
        temp.next = head;
        HashMap<Integer, ListNode> hm = new HashMap<>();
        int pSum = 0;
        hm.put(pSum, temp);

        while ( head != null ) {
            pSum += head.val;
            System.out.println("> " + pSum);
            if (hm.containsKey(pSum)) {
                // Удаляем вложения
                ListNode toRemove = hm.get(pSum).next;
                int sum = pSum;
                while (toRemove != head) {
                    sum += toRemove.val;
                    System.out.println(sum);
                    hm.remove(sum);
                    toRemove = toRemove.next;
                }
                //Обновляем ссылку после удаления
                hm.get(pSum).next = head.next;

            }else{
                hm.put(pSum, head);
            }
            head = head.next;
        }
        System.out.println( hm );
        return temp.next;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}