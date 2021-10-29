/*
    Метод обходит бинарное дерево и считает сумму всех узлов.

 */

class BynaryTree {
    public static void main(String[] args) {

        Node n5 = new Node(10);
        Node n4 = new Node(10);
        Node n3 = new Node(10, n4, n5);
        Node n2 = new Node(10);
        Node n1 = new Node(10, n2, n3);

        System.out.println( summBenaryTree(n1) );

    }

    static int summBenaryTree(Node elem) {
        int summ = 0;
        summ += elem.num;
        if (elem.left != null) {
            summ += summBenaryTree(elem.left);
        }
        if (elem.right != null) {
            summ += summBenaryTree(elem.right);
        }
        return summ;
    }

    static public class Node {
        int num;
        Node left;
        Node right;
        public Node(int num) {
            this.num = num;
        }
        public Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }
}