
class Node {
    public final int val;
    public Node next;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}


class I {
    //Частичный разворот
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node previous = node1;
        Node current;

        for (int i = 2; i < 6; i++) {
            current = new Node(i);
            previous.next = current;
            previous = current;
        }
        current = Reverse(node1, 2, 5);
    }

    public static Node Reverse(Node head, int left, int right) {
        Node current = head;
        Node first;
        Node second;
        Node previous = null;
        int size = right - left + 1;
        if (size < 2) {
            return head;
        }
        Node[] array = new Node[size];

        for (int i = 1; i < left; i++) {

            previous = current;
            current = current.next;
        }
        first = previous;

        for (int i = 0; i < size; i++) {
            array[i] = current;
            current = current.next;
        }
        second = current;
        if (first != null) {
            first.next = array[size - 1];
        }
        array[0].next = second;
        for (int i = 1; i < size; i++) {
            array[i].next = array[i - 1];
        }
        if (left == 1) {
            return array[size - 1];
        }
        return head;


    }
}