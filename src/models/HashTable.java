package models;

public class HashTable {
    private Entry[] table;
    private int size;
    private int count;
    private HashFunction hashFunction;

    public HashTable(int size, HashFunction hashFunction) {
        this.size = size;
        this.hashFunction = hashFunction;
        this.table = new Entry[size];
        this.count = 0;
    }

    public void insert(String key, String value) {
        int hash = Math.abs(hashFunction.hash(key, size));
        while (table[hash] != null && !table[hash].key.equals(key)) {
            hash = (hash + 1) % size;
        }
        table[hash] = new Entry(key, value);
        count++;
    }

    public String search(String key) {
        int hash = Math.abs(hashFunction.hash(key, size));
        int originalHash = hash;
        while (table[hash] != null) {
            if (table[hash].key.equals(key)) {
                return table[hash].value;
            }
            hash = (hash + 1) % size;
            if (hash == originalHash) {
                break;
            }
        }
        return null;
    }

    public class Entry {
        String key;
        String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public interface HashFunction {
        int hash(String key, int size);
    }
}

