package util;

public class Pair<K, L> {

    public final K key;
    public final L value;

    public Pair(K first, L second) {
        this.key = first;
        this.value = second;
    }

    @Override
    public String toString() {
            return key + ":" + value;
        }


}
