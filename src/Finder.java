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

    private HashMap map;

    public Finder() {
        map = new HashMap();
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
            map.add(key, val);
        }
        br.close();
    }

    // Gets value for a given key
    public String query(String key) {
        // Gets the value of a key
        String result = map.get(key);

        // If the value is null then it is invalid
        if (result == null) {
            return INVALID;
        }
        return result;
    }
}