package linked_lists;

public class DLL_Node {
    int data;
    DLL_Node previous;
    DLL_Node next;

    public DLL_Node(int data, DLL_Node previous, DLL_Node next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    public static DLL_Node getNode(DLL_Node dll, int index) {
        int counter = 0;
        for (DLL_Node i = dll; i != null; i = i.next) {
            if (counter == index) {
                return i;
            }

            counter++;
        }

        return null;
    }

    public static void deleteNode(DLL_Node dll, int index) {
        int counter = 0;
        for (DLL_Node i = dll; i != null; i = i.next) {
            if (i.next != null && i.previous != null && counter == index) {
                i.next.previous = i.previous;
                i.previous.next = i.next;
            }
            else if (i.next != null && i.previous == null && counter == index) {
                i.next.previous = null;
            }
            else if (i.next == null && counter == index) {
                i.previous.next = null;
            }
        }
    }

    public static void addNode(DLL_Node dll, int index, int data) {
        DLL_Node node = new DLL_Node(data, null, null);

        int counter = 0;
        for (DLL_Node i = dll; i != null; i = i.next) {
            if (i.previous != null && counter == index) {
                node.next = i;
                node.previous = i.previous;
                i.previous.next = node;
                i.previous = node;
                break;
            }
            else if (i.previous == null && counter == index) {
                node.next = i;
                i.previous = node;
                break;
            }
            else if (i.next == null && counter + 1 == index) {
                node.previous = i;
                i.next = node;
                break;
            }

            counter++;
        }
    }
}
