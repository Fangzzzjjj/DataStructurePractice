package DataStuctrue.MyLinkedList;


public class Demo {
    public static void main(String[] args) {
        MyLinkedList<Integer> l = new MyLinkedList<>();
        l.add(1);
        l.add(3);
        System.out.println(l);
        l.add(1,2);
        System.out.println(l);
        MyLinkedList<Integer> l2 = new MyLinkedList<>(99);
        l.add(l2);
        System.out.println(l);
        System.out.println(l2);
        MyLinkedList<Integer> l3 =l.clone();
        System.out.println(l3);
        System.out.println(l3.contain(1));
        System.out.println(l3.contain(100));
        System.out.println(l3.index(100));
        l.clear();
        l2.clear();
        l3.clear();

    }
}
