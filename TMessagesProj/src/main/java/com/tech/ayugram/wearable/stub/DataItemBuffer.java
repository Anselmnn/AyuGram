package com.tech.ayugram.wearable.stub;

import java.util.List;

public class DataItemBuffer implements java.util.List<DataItem> {
    public DataItemBuffer() {}

    @Override
    public int size() { return 0; }
    @Override
    public boolean isEmpty() { return true; }
    @Override
    public boolean contains(Object o) { return false; }
    @Override
    public java.util.Iterator<DataItem> iterator() { return new java.util.ArrayList<DataItem>().iterator(); }
    @Override
    public Object[] toArray() { return new Object[0]; }
    @Override
    public <T> T[] toArray(T[] a) { return a; }
    @Override
    public boolean add(DataItem dataItem) { return false; }
    @Override
    public boolean remove(Object o) { return false; }
    @Override
    public boolean containsAll(java.util.Collection<?> c) { return false; }
    @Override
    public boolean addAll(java.util.Collection<? extends DataItem> c) { return false; }
    @Override
    public boolean addAll(int index, java.util.Collection<? extends DataItem> c) { return false; }
    @Override
    public boolean removeAll(java.util.Collection<?> c) { return false; }
    @Override
    public boolean retainAll(java.util.Collection<?> c) { return false; }
    @Override
    public void clear() {}
    @Override
    public DataItem get(int index) { return null; }
    @Override
    public DataItem set(int index, DataItem element) { return null; }
    @Override
    public void add(int index, DataItem element) {}
    @Override
    public DataItem remove(int index) { return null; }
    @Override
    public int indexOf(Object o) { return -1; }
    @Override
    public int lastIndexOf(Object o) { return -1; }
    @Override
    public java.util.ListIterator<DataItem> listIterator() { return new java.util.ArrayList<DataItem>().listIterator(); }
    @Override
    public java.util.ListIterator<DataItem> listIterator(int index) { return new java.util.ArrayList<DataItem>().listIterator(index); }
    @Override
    public java.util.List<DataItem> subList(int fromIndex, int toIndex) { return new java.util.ArrayList<DataItem>(); }
    public void release() {}
}