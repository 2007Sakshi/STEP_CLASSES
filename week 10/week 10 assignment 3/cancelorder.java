
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class cancelorder {

    // Remove every node matching the given code
    static Node removeAll(Node head, int code) {

        // Remove matching nodes from the beginning
        while (head != null && head.data == code) {
            head = head.next;
        }

        // Remove matching nodes from the remaining list
        Node current = head;

        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    // Display the linked list
    static void display(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        // Create list: 5 -> 3 -> 5 -> 8 -> 5
        Node head = new Node(5);
        head.next = new Node(3);
        head.next.next = new Node(5);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(5);

        int code = 5;

        System.out.print("Original list: ");
        display(head);

        head = removeAll(head, code);

        System.out.print("After removal: ");
        display(head);
    }
}
