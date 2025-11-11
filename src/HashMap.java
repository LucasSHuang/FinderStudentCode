public class HashMap {
    private static final int RADIX = 256;
    private static final int DEFAULT_TABLE_SIZE = 8429;

    int tableSize;
    int n;
    String[] keys;
    String[] values;

    public HashMap() {
        tableSize = DEFAULT_TABLE_SIZE;
        n = 0;
        keys = new String[tableSize];
        values = new String[tableSize];
    }
    public int hash(String str) {
        long hashCount = 0;

        // Uses Horner's method to calculate
        for (int i = 0; i < str.length(); i++) {
            // Multiplies previous value by radix and adds the next char and divides by a p value to prevent overflow
            hashCount = (hashCount * RADIX + str.charAt(i)) % tableSize;
        }

        // Modulate by tablesize to prevent index out of bounds errors and then convert to an int
        return (int) (hashCount % tableSize);
    }
    void add(String key, String value) {
        // If table is half full resize
        if (n >= tableSize / 2) {
            resize();
        }
        int index = hash(key);

        // Checks to see if the index is already full and if so move on to another index until one is open
        while (keys[index] != null) {
            // Modulate by table size to make sure it doesn't cause an index out of bounds error
            index = (index + 1) % tableSize;
        }

        // Add the key and value to hashtable at that index
        keys[index] = key;
        values[index] = value;
        n++;
    }

    // Gets the value of a key
    String get(String key) {
        int index = hash(key);

        // Goes until there is an empty spot in the hash table
        while (keys[index] != null) {
            // If the key in the index matches the key then return the value
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + 1) % tableSize;
        }

        // If no match then return null
        return null;
    }

    // Resize the hash table
    void resize() {
        // Temporary variables to keep track of old table
        int oldSize = tableSize;
        String[] oldKeys = keys;
        String[] oldVals = values;

        // Reset and double table size but add one to make it odd for less collisions
        n = 0;
        tableSize *= 2 + 1;
        keys = new String[tableSize];
        values = new String[tableSize];

        // Adds all the old key-value pairs into the new table
        for (int i = 0; i < oldSize; i++) {
            if (oldKeys[i] != null) {
                add(oldKeys[i], oldVals[i]);
            }
        }
    }
}
