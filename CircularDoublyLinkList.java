import java.util.NoSuchElementException;

public class CircularDoublyLinkList<T> implements CircularDoublyLinkListInterface<T> {
    private Node<T> head;
    private Node<T> last;
    private int numberOfEntries;

    CircularDoublyLinkList() {
        initialize();
    }

    private void initialize() {
        head = last = null;
        numberOfEntries = 0;
    }

    private void add(Node<T> newNode) {
        newNode.setNextNode(head);
        head.setPreviousNode(newNode);
        newNode.setPreviousNode(last);
        last.setNextNode(newNode);
    }

    @Override
    public void addFirst(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        if (isEmpty()) {
            head = newNode;
            last = newNode;
            newNode.setNextNode(head);
            newNode.setPreviousNode(head);
        } else {
            add(newNode);
            head = newNode;
        }
        numberOfEntries++;
    }

    @Override
    public void addLast(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        if (isEmpty()) {
            head = newNode;
            last = newNode;
            newNode.setNextNode(head);
            newNode.setPreviousNode(head);
        } else {
            add(newNode);
            last = newNode;
        }
        numberOfEntries++;
    }

    @Override
    public void insert(int position, T newEntry) {
        if (position >= 1 && position <= numberOfEntries + 1) {
            Node<T> newNode = new Node<>(newEntry);
            if (position == 1)
                addFirst(newEntry);
            else if (position == numberOfEntries + 1)
                addLast(newEntry);
            else {
                Node<T> previous = getNodeAt(position - 1);
                Node<T> current = previous.getNextNode();
                newNode.setNextNode(current);
                newNode.setPreviousNode(previous);
                previous.setNextNode(newNode);
                current.setPreviousNode(newNode);
                numberOfEntries++;
            }
        } else
            throw new IndexOutOfBoundsException("Index Out Of Bound Must Be From 1 To " + numberOfEntries + 1);
    }

    @Override
    public T update(int position, T newEntry) {
        if (!isEmpty() && position >= 1 && position <= numberOfEntries) {
            T toReturn;
            if (position == 1) {
                toReturn = head.getData();
                head.setData(newEntry);
                return toReturn;
            } else if (position == numberOfEntries) {
                toReturn = last.getData();
                last.setData(newEntry);
                return toReturn;
            } else {
                Node<T> node = getNodeAt(position);
                toReturn = node.getData();
                node.setData(newEntry);
                return toReturn;
            }
        }
        throw new IndexOutOfBoundsException("List Is Empty, Or Index Out Of Bound Must Be From 1 To " + numberOfEntries);
    }

    @Override
    public T deleteFirst() {
        if (!isEmpty()) {
            T toReturn = head.getData();
            Node<T> monarch = head.getNextNode();
            monarch.setPreviousNode(last);
            last.setNextNode(monarch);
            head = monarch;
            numberOfEntries--;
            return toReturn;
        }
        throw new NullPointerException("List Is Empty");
    }

    @Override
    public T deleteLast() {
        if (!isEmpty()) {
            T toReturn = last.getData();
            Node<T> monarch = last.getPreviousNode();
            monarch.setNextNode(head);
            head.setPreviousNode(monarch);
            last = monarch;
            numberOfEntries--;
            return toReturn;
        }
        throw new NullPointerException("List Is Empty");
    }

    @Override
    public T getEntry(int position) {
        if (!isEmpty() && position >= 1 && position <= numberOfEntries)
            if (position == 1)
                return head.getData();
            else if (position == numberOfEntries)
                return last.getData();
            else
                return getNodeAt(position).getData();
        throw new NullPointerException("List Is Empty, Or Index Out Of Bound Must Be From 1 To " + numberOfEntries);
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        initialize();
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public Node<T> getNodeAt(int position) {
        if (!isEmpty()) {
            Node<T> node;
            if (position >= 1 && position <= numberOfEntries / 2) {
                node = head;
                for (int i = 1; i < position; i++)
                    node = node.getNextNode();
            } else {
                node = last;
                for (int i = numberOfEntries; i > position / 2; i--)
                    node = node.getNextNode();
            }
            return node;
        }
        throw new NullPointerException("List Is Empty");
    }

    @Override
    public int searchNode(T key) {
        if (!isEmpty()) {
            Node<T> node = head;
            if (key == head.getData())
                return 1;
            else if (key == last.getData())
                return numberOfEntries;
            for (int i = 2; i < numberOfEntries; i++) {
                node = node.getNextNode();
                if (key == node.getData())
                    return i;
            }
        }
        throw new NoSuchElementException("List Is Empty, Or No Such Element Exists");
    }

    @Override
    public T deleteByAddress(int position) {
        if (!isEmpty() && position >= 1 && position <= numberOfEntries) {
            if (position == 1)
                return deleteFirst();
            else if (position == numberOfEntries)
                return deleteLast();
            T toReturn;
            Node<T> node = getNodeAt(position);
            toReturn = node.getData();
            node.getPreviousNode().setNextNode(node.getNextNode());
            node.getNextNode().setPreviousNode(node.getPreviousNode());
            numberOfEntries--;
            return toReturn;
        }
        throw new IndexOutOfBoundsException("List Is Empty, Or Index Out Of Bound Must Be From 1 to"
                + numberOfEntries);
    }

    @Override
    public T deleteByKey(T key) {
        if (!isEmpty()) {
            if (key == head.getData()) {
                System.out.println(key + " at position 1 is deleted");
                return deleteFirst();
            } else if (key == last.getData()) {
                System.out.println(key + " at position " + numberOfEntries + " is deleted");
                return deleteLast();
            }
            T toReturn;
            int position = searchNode(key);
            Node<T> node = getNodeAt(position);
            toReturn = node.getData();
            System.out.println(key + " at position " + position + " is deleted");
            node.getPreviousNode().setNextNode(node.getNextNode());
            node.getNextNode().setPreviousNode(node.getPreviousNode());
            numberOfEntries--;
            return toReturn;

        }
        throw new NullPointerException("List Is Empty, Or Key Not Found In The List");
    }

    @Override
    public void display() {
        if (!isEmpty()) {
            Node<T> node = head;
            for (int i = 1; i <= numberOfEntries; i++) {
                System.out.println("Position " + i + " element is " + node.getData());
                node = node.getNextNode();
            }
        } else
            System.out.println("List Is Empty, Nothing To Display");
    }

    @Override
    public void displayReverse() {
        if (!isEmpty()) {
            Node<T> node = last;
            for (int i = numberOfEntries; i >= 1; i--) {
                System.out.println("Position " + i + " element is " + node.getData());
                node = node.getPreviousNode();
            }
        } else
            System.out.println("List Is Empty, Nothing To Display");
    }
}
