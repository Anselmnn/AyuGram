package com.tech.ayugram.wearable.stub;

import java.util.List;

/**
 * Phase 2: Stub for com.google.android.gms.wearable.DataEventBuffer
 * Wearable dependency removed in Phase 2
 */
public class DataEventBuffer implements java.util.List<DataEvent> {
    public DataEventBuffer() {}

    @Override
    public int size() { return 0; }
    @Override
    public boolean isEmpty() { return true; }
    @Override
    public boolean contains(Object o) { return false; }
    @Override
    public java.util.Iterator<DataEvent> iterator() { return new java.util.ArrayList<DataEvent>().iterator(); }
    @Override
    public Object[] toArray() { return new Object[0]; }
    @Override
    public <T> T[] toArray(T[] a) { return a; }
    @Override
    public boolean add(DataEvent dataEvent) { return false; }
    @Override
    public boolean remove(Object o) { return false; }
    @Override
    public boolean containsAll(java.util.Collection<?> c) { return false; }
    @Override
    public boolean addAll(java.util.Collection<? extends DataEvent> c) { return false; }
    @Override
    public boolean addAll(int index, java.util.Collection<? extends DataEvent> c) { return false; }
    @Override
    public boolean removeAll(java.util.Collection<?> c) { return false; }
    @Override
    public boolean retainAll(java.util.Collection<?> c) { return false; }
    @Override
    public void clear() {}
    @Override
    public DataEvent get(int index) { return null; }
    @Override
    public DataEvent set(int index, DataEvent element) { return null; }
    @Override
    public void add(int index, DataEvent element) {}
    @Override
    public DataEvent remove(int index) { return null; }
    @Override
    public int indexOf(Object o) { return -1; }
    @Override
    public int lastIndexOf(Object o) { return -1; }
    @Override
    public java.util.ListIterator<DataEvent> listIterator() { return new java.util.ArrayList<DataEvent>().listIterator(); }
    @Override
    public java.util.ListIterator<DataEvent> listIterator(int index) { return new java.util.ArrayList<DataEvent>().listIterator(index); }
    @Override
    public java.util.List<DataEvent> subList(int fromIndex, int toIndex) { return new java.util.ArrayList<DataEvent>(); }
    public void release() {}
}