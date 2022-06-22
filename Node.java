public class Node<T> {
    T data;
    Node<T> previousNode;
    Node<T> nextNode;

    Node(T data) {
        setData(data);
        setPreviousNode(null);
        setNextNode(null);
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setPreviousNode(Node<T> previousNode) {
        this.previousNode = previousNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public T getData() {
        return data;
    }

    public Node<T> getPreviousNode() {
        return previousNode;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }
}
