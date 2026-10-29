
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class reverse {

    // Reverse the linked list
    static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;       // Save next node
            current.next = previous;   // Reverse the link
            previous = current;        // Move previous forward
            current = next;            // Move current forward
        }

        return previous;
    }

    // Display linked list
    static void display(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        System.out.print("Original queue: ");
        display(head);

        head = reverse(head);

        System.out.print("Reversed queue: ");
        display(head);
    }
}
