package Btree_Hash;

import graph.ListLinked;
import modelo.Producto;

public class HashO {
    private ListLinked<Producto>[] table;
    private int size;

    public HashO(int size) {
        this.size = size;
        table = new ListLinked[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ListLinked<>();
        }
    }

    public int hash(int key) {
        return key % size;
    }

    public void insert(Producto prod) {
        int index = hash(prod.getKey());
        table[index].add(prod);
    }

    public Producto search(int key) {
        int index = hash(key);
        return table[index].findByKey(key);  
    }
}

