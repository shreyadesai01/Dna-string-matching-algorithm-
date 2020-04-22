
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


/**
 *
 * @author shreya
 */
public class FinalProject {
    
    private static int[] lookup = new int[256];
    private static int count = 0;

    /**
     * This is the main method of the class
     *
     * @param args
     */
    public static void main(String[] args) {
        String text = readFile("DNA.txt");
        String pattern = "GGCATATGAAAATTTATTACTACAGTGTTTT";

        long time = System.currentTimeMillis();
        search(text, pattern);
        System.out.println("");
        System.out.println("Pattern occurs " + count + " times!");
        System.out.println("It took " + (System.currentTimeMillis() - time) + " ms to search the pattern!");
    }

    /**
     * This method searches the pattern in the given text and prints the
     * position at which it occurs in the string
     *
     * @param t
     * @param p
     */
    private static void search(String t, String p) {

        char text[] = t.toLowerCase().toCharArray();
        char pattern[] = p.toLowerCase().toCharArray();

        int textLength = text.length;
        int patternLength = pattern.length;

        badTable(p);
        int shift = 0;

        while (shift <= (textLength - patternLength)) {

            int j = patternLength - 1;

            while (j >= 0 && compare(pattern[j], text[shift + j])) {
                j--;
            }

            if (j < 0) {
                System.out.println("Pattern occurs at position = " + shift);
                count++;

                if ((shift + patternLength) < textLength) {
                    shift = shift + (patternLength - lookup[text[shift + patternLength]]);
                } else {
                    shift = shift + 1;
                }

            } else {
                shift = shift + max(1, j-lookup[text[shift+j]]);
            }
        }
    }

    /**
     * This method inserts value in an array at position equals to the Ascii
     * value of the pattern
     *
     * @param pattern
     */
    private static void badTable(String pattern) {
        Arrays.fill(lookup, -1);
        for (int i = 0; i < pattern.length(); i++) {
            lookup[(int) pattern.toLowerCase().charAt(i)] = i;
        }
    }

    /**
     * This method returns the max of the passed two parameters
     *
     * @param a
     * @param b
     * @return
     */
    private static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * This method reads whole file into a string and returns that
     *
     * @param filePath
     * @return
     */
    private static String readFile(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * This method checks if the character is same
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean compare(char a, char b) {

        int c = Character.compare(a, b);

        if (c == 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
