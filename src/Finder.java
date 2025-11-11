import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Lucas Huang
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    private static final int RADIX = 256;
    private static final long P_VALUE = 89434549747967L;

    private static final int DEFAULT_TABLE_SIZE = 5000;

    int tableSize;
    int n;
    String[] keys;
    String[] values;

    public Finder() {
        tableSize = DEFAULT_TABLE_SIZE;
        n = 0;
        keys = new String[tableSize];
        values = new String[tableSize];
    }

    // Builds the hash table
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            // Split each line by commas to get the keys and values
            String[] tokens = line.split(",");
            String key = tokens[keyCol];
            String val = tokens[valCol];

            // Adds the key and value to the hash table
            add(key, val);
        }
        br.close();
    }

    // Gets value for a given key
    public String query(String key){
        // Gets the value of a key
        String result = get(key);

        // If the value is null then it is invalid
        if (result == null) {
            return INVALID;
        }
        return result;
    }

    // Hash a string
    public int hash(String str) {
        long hashCount = 0;

        // Uses Horner's method to calculate
        for (int i = 0; i < str.length(); i++) {
            // Multiplies previous value by radix and adds the next char and divides by a p value to prevent overflow
            hashCount = (hashCount * RADIX + str.charAt(i)) % P_VALUE;
        }

        // Modulate by tablesize to prevent index out of bounds errors and then convert to an int
        return (int) (hashCount % tableSize);
    }

    // Add a key and value to the hash table
    void add(String key, String value) {
        // If table is half full resize
        if (n >= tableSize / 2) {
            resize();
        }
        int index = hash(key);

        // Checks to see if the index is already full and if so move on to another index until one is open
        while (keys[index] != null) {
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
            index++;
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

        // Reset and double table size
        n = 0;
        tableSize *= 2;
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