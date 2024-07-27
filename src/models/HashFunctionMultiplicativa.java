package models;
public class HashFunctionMultiplicativa implements HashTable.HashFunction {
    public int hash(String key, int size) {
        int hash = 7;
        for (char c : key.toCharArray()) {
            hash = hash * 31 + c;
        }
        return hash % size;
    }
}