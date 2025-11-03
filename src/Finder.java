import java.io.BufferedReader;
import java.io.IOException;
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

    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // TODO: Complete the buildTable() function!
        br.close();
    }

    public String query(String key){
        // TODO: Complete the query() function!
        if (key.length() == 1 || key.length() == 2) {

        }
        return INVALID;
    }

    public long getHashCount(String str) {
        long hashCount = 0;
        for (int i = 0; i < str.length(); i++) {
            hashCount = (hashCount * RADIX + str.charAt(i)) % P_VALUE;
        }
        return hashCount;
    }
}