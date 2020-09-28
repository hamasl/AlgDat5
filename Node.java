public class Node {
    private Node next;
    private final int NUMBER;

    public Node(Node next, int NUMBER) {
        this.next = next;
        this.NUMBER = NUMBER;
    }

    //Copy constructor needed to copy whole Node to avoid aggregation
    public Node(Node original){
        if (original.next != null) this.next = new Node(original.next);
        else this.next = null;
        this.NUMBER = original.NUMBER;
    }

    public Node(int NUMBER) {
        this.NUMBER = NUMBER;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getNUMBER() {
        return NUMBER;
    }

    public Node getTail(){
        Node current = this;
        while (current.getNext() != null){
            current = current.getNext();
        }
        return current;
    }

    @Override
    public String toString() {
        return NUMBER + ((next == null) ? "" : (" -> " + next.toString()));
    }

}
