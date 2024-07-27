package models;
public class HashFunctionFNV_1a implements HashTable.HashFunction {
    public int hash(String key, int size) {
        int hash = 0x811c9dc5;
        for (char c : key.toCharArray()) {
            hash ^= c;
            hash *= 0x01000193;
        }
        return Math.abs(hash) % size;
    }
}