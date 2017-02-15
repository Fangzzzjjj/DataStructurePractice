package DataStuctrue.MyMaxHeap;

import java.util.Arrays;

public class MyMaxHeap {
    private int[] data;
    private int length = 0;

    public MyMaxHeap(int[] data) {
        this.data = data;
        this.length = data.length;
        this.buildeHeap();
    }

    private void buildeHeap() {
        int start = getParentIndex(length - 1);
        for (; start >= 0; start--) {
            adjustUpToDown(start);
        }
    }

    public void remove() {
        swap(0, length - 1);
        int[] newData = new int[length - 1];
        for (int i = 0; i < length - 1; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
        this.length = length - 1;
        this.buildeHeap();
    }

    public void insert(int value){
        int[] newData = new int[length+1];
        for(int i = 0; i < length;i++){
            newData[i] = data[i];
        }
        newData[length] = value;
        this.data = newData;
        this.length  = length+1;
        this.adjustDownToup(this.length-1);

    }

    private void adjustDownToup(int node) {
        int parent = getParentIndex(node);
        if(parent >=0 && data[parent] < data[node]){
            swap(parent,node);
            adjustDownToup(parent);
        }
    }

    private void adjustUpToDown(int node) {
        int right = getRightIndex(node);
        int left = getLeftIndex(node);
        int max = node;
        if (right < length && data[right] > data[max]) {
            max = right;
        }
        if (left < length && data[left] > data[max]) {
            max = left;
        }
        if (max != node) {
            swap(node, max);
            adjustUpToDown(max);
        }
    }

    private void swap(int n1, int n2) {
        int temp = data[n1];
        data[n1] = data[n2];
        data[n2] = temp;

    }

    private int getLeftIndex(int node) {
        return (node * 2) + 2;
    }

    private int getRightIndex(int node) {
        return (node * 2) + 1;
    }

    private int getParentIndex(int node) {
        return (node - 1) / 2;
    }

    @Override
    public String toString() {
        return "MyMaxHeap{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length +
                '}';
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(data));
        MyMaxHeap heap = new MyMaxHeap(data);
        System.out.println(heap);
        heap.remove();
        System.out.println(heap);
        heap.insert(100000);
        System.out.println(heap);
    }
}

