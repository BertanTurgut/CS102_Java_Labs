package linked_lists;


public class _main {
    public static void main(String[] args) {
        SLL_Node head_sll = new SLL_Node(0, new SLL_Node(1, new SLL_Node(2, new SLL_Node(3, new SLL_Node(4, null)))));

        for (SLL_Node x = head_sll; x != null; x = x.next) {
            System.out.println(x.data);
        }

        System.out.println("******");

        System.out.println(SLL_Node.getNode(head_sll, 3).data);

        System.out.println("******");

        SLL_Node.deleteNode(head_sll, 3);
        for (SLL_Node x = head_sll; x != null; x = x.next) {
            System.out.println(x.data);
        }

        System.out.println("******");

        SLL_Node.addNode(head_sll, 2, 5);
        for (SLL_Node x = head_sll; x != null; x = x.next) {
            System.out.println(x.data);
        }

        System.out.println("******");

        DLL_Node head_dll = new DLL_Node(0, null, null);
        DLL_Node.addNode(head_dll, 1, 1);
        DLL_Node.addNode(head_dll, 2, 2);
        DLL_Node.addNode(head_dll, 3, 3);
        DLL_Node.addNode(head_dll, 4, 4);
        DLL_Node.addNode(head_dll, 0, -2);
        DLL_Node.addNode(head_dll, 0, -1);

        for (DLL_Node x = head_dll; x != null; x = x.next) {
            System.out.println(x.data);
        }

        System.out.println("******");

        for (DLL_Node x = head_dll; x != null; x = x.previous) {
            System.out.println(x.data);
        }

        System.out.println("******");
    }
}
