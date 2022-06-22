public interface CircularDoublyLinkListInterface<T> {
    //1 - this method will add a new node at the start of the list
    void addFirst(T newEntry);

    //2 - this method will add a new node at the last of a list
    void addLast(T newEntry);

    //3 - this method will add/insert a new node at a given position
    void insert(int position, T newEntry);

    //4 - this method will update data of a node and return the previous data that was updated
    T update(int position, T newEntry);

    //5 - this method will delete the first node and return its data
    T deleteFirst();

    //6 - this method will delete the last node and return its data
    T deleteLast();

    //7 - this method will return the entry at given position
    T getEntry(int position);

    //8 - this method will return the size of list (number of elements in the list)
    int getLength();

    //9 - this method will delete all the nodes of a list
    void clear();

    //10 - this method will return true if the list is empty and false if not
    boolean isEmpty();

    //11 - this method will return the index/position of a node in the list if it is equal to key (target value)
    int searchNode(T key);

    //12 - this method will delete a node at given position and return its data
    T deleteByAddress(int position);

    //this method will delete the target value from list
    T deleteByKey(T key);

    //13 - this method will display the elements of the list
    void display();

    //14 - this method will display the elements of the list in the reverse order
    void displayReverse();
}
