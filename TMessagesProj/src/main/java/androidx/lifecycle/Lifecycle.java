package androidx.lifecycle;

public interface Lifecycle {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}