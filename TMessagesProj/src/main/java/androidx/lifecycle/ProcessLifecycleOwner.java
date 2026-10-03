package androidx.lifecycle;

public class ProcessLifecycleOwner {
    private static ProcessLifecycleOwner instance = new ProcessLifecycleOwner();

    public static ProcessLifecycleOwner get() {
        return instance;
    }

    public Lifecycle getLifecycle() {
        return new Lifecycle() {
            @Override
            public void addObserver(Observer observer) {}

            @Override
            public void removeObserver(Observer observer) {}
        };
    }
}