
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    // Insert at the beginning
    void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
    }

    // Insert at the end
    void addLast(int token) {
        Node newNode = new Node(token);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    // Insert at a given 0-based index
    void insertAt(int index, int token) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            addFirst(token);
            return;
        }

        Node temp = head;

        for (int i = 0; i < index - 1; i++) {
            if (temp == null) {
                throw new IndexOutOfBoundsException("Invalid index");
            }
            temp = temp.next;
        }

        if (temp == null) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node newNode = new Node(token);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Display the list
    void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }
}

public class insert {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        // Starting list
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        // Perform operations
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        // Print final list
        list.display();
    }
}
