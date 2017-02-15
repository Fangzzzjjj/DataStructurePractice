package DataStuctrue.MyLinkedList;

import java.util.Collection;
import java.util.Iterator;

class MyLinkedList<AnyType> implements Iterable<AnyType> {
    private int theSize;
    private Node<AnyType> head;         //head.obj and last.obj are always null
    private Node<AnyType> last;         //they just show the begin and end of the chain

    MyLinkedList() {
        clear();
    }

    MyLinkedList(AnyType obj) {
        clear();
        add(obj);
    }

    MyLinkedList(AnyType[] objs) {
        clear();
        for (AnyType obj : objs) {
            add(obj);
        }
    }

    MyLinkedList(Collection<AnyType> collection) {
        for (AnyType obj : collection){
            add(obj);
        }
    }

    int size() {
        return theSize;
    }

    boolean isEmpty() {
        return theSize == 0;
    }

    void add(AnyType obj) {
        add(theSize, obj);
    }

    void add(int index, AnyType obj) {
        addBefore(getNode(index), obj);
    }


    public MyLinkedList<AnyType> clone() {
        MyLinkedList<AnyType> newLinkedList = new MyLinkedList<>();
        for (AnyType obj : this) {
            newLinkedList.add(obj);
        }
        return newLinkedList;
    }


    void add(MyLinkedList<AnyType> linkedList) {
        MyLinkedList<AnyType> newLinkedList = linkedList.clone();
        theSize += newLinkedList.size();
        last.prev.next = newLinkedList.head.next;
        newLinkedList.head.next.prev = last.prev;
        last = newLinkedList.last;
        newLinkedList.clear();
    }

    private void addBefore(Node node, AnyType obj) {
        Node newNode = new Node<>(obj, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
        theSize++;
    }

    AnyType remove(int index) {
        Node<AnyType> node = getNode(index);
        node.next.prev = node.prev;
        node.prev.next = node.next;
        theSize--;
        return node.obj;
    }

    AnyType set(int index, AnyType obj) {
        Node<AnyType> node = getNode(index);
        AnyType oldVal = node.obj;
        node.obj = obj;
        return oldVal;
    }

    AnyType get(int index) {
        return getNode(index).obj;
    }

    int index(AnyType obj) {
        int index = -1;
        for (AnyType i : this) {
            index++;
            if (i == obj) {
                return index;
            }
        }
        return -1;
    }

    void show() {
        System.out.println(this.toString());
    }

    boolean contain(AnyType obj) {
        for (AnyType i : this) {
            if (i == obj) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<AnyType> node = head.next;
        for (int i = 0; i < theSize - 1; i++) {
            sb.append(node.obj.toString()).append(",");
            node = node.next;
        }
        sb.append(node.obj.toString()).append("]");
        return sb.toString();
    }

    private Node<AnyType> getNode(int index) {
        if (index < 0 || index > theSize) {
            throw new IndexOutOfBoundsException();
        }
        Node<AnyType> node;
        if (index < theSize / 2) {
            node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = theSize; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    void clear() {
        head = new Node<>(null, null, null);
        last = new Node<>(null, head, null);
        head.next = last;
        theSize = 0;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new linkedListIterator<>();
    }

    private static class Node<AnyType> {
        AnyType obj;
        Node<AnyType> prev;
        Node<AnyType> next;

        Node(AnyType o, Node<AnyType> p, Node<AnyType> n) {
            obj = o;
            prev = p;
            next = n;

        }
    }

    private class linkedListIterator<AnyType> implements Iterator<AnyType> {
        private Node<AnyType> current;

        {
            current = (Node<AnyType>) head.next;
        }

        @Override
        public boolean hasNext() {
            return current != last;
        }

        @Override
        public AnyType next() {
            AnyType nextItem = current.obj;
            current = current.next;
            return nextItem;
        }
    }
}
