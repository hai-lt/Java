//package views.rmi;

import java.io.Serializable;

public class ArraySerializable implements Serializable {
    int[] values;
    public ArraySerializable(int[] values){
        this.values = values;
    };

    public int sum() {
        int a = 0;
        for (int i = 0; i < values.length; i++) {
            a += values[i];
        }
        return a;
    }

    public ArraySerializable slice(int start, int tail) {
        tail += 1;
        tail = values.length < tail ? values.length : tail;
        int[] a = new int[tail - start];
        int j = 0;
        for (int i = start; i < tail; i++) {
            a[j] = values[i];
            j++;
        }
        return new ArraySerializable(a);
    }

    public void show() {
        System.out.println("In Serializable: Start testing has elements: " + values.length);
        String a = "";
        for (int i :
                values) {
            a += i + ", ";
        }
        System.out.println("End serializeable: " + sum());
    }

    public int size() {
        return values.length;
    }
}
