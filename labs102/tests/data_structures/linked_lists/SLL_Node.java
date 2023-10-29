package linked_lists;

public class SLL_Node {
    int data;
    SLL_Node next;

    public SLL_Node(int data, SLL_Node next) {
        this.data = data;
        this.next = next;
    }

    public static SLL_Node getNode(SLL_Node sll, int index) {
        int counter = 0;
        for (SLL_Node i = sll; i != null; i = i.next) {
            if (counter == index) {
                return i;
            }

            counter++;
        }

        return null;
    }

    public static void deleteNode(SLL_Node sll, int index) {
        int counter = 0;
        for (SLL_Node i = sll; i != null; i = i.next) {
            if (i.next != null && counter == index) {
                getNode(sll, index - 1).next = i.next;
                break;
            } 
            else if (i.next == null && counter == index) {
                getNode(sll, index - 1).next = null;
                break;
            }

            counter++;
        }
    }

    public static void addNode(SLL_Node sll, int index, int data) {
        int counter = 0;
        for (SLL_Node i = sll; i != null; i = i.next) {
            if (i.next != null && counter == index) {
                getNode(sll, index - 1).next = new SLL_Node(data, i);
                break;
            } 
            else if (i.next == null && counter + 1 == index) {
                getNode(sll, index - 1).next = new SLL_Node(data, null);
                break;
            }

            counter++;
        }
    }
}
