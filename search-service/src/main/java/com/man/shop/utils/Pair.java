package com.man.shop.utils;

/**
 * Created by smanolache on 3/19/2015.
 */
public class Pair<T, V> {
    private T value1;
    private V value2;

    public Pair(){
    }

    public Pair(T value1, V value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public void setValue1(T value1) {
        this.value1 = value1;
    }

    public V getValue2() {
        return value2;
    }

    public void setValue2(V value2) {
        this.value2 = value2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (value1 != null ? !value1.equals(pair.value1) : pair.value1 != null) return false;
        if (value2 != null ? !value2.equals(pair.value2) : pair.value2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value1 != null ? value1.hashCode() : 0;
        result = 31 * result + (value2 != null ? value2.hashCode() : 0);
        return result;
    }
}
