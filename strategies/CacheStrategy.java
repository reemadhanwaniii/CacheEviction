package strategies;

public interface CacheStrategy<T> {
    void put(int key,int value);
    int get(int key);
}
