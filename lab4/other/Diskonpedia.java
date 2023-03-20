package ddp2.lab4.other;
import java.io.*;
import java.util.StringTokenizer;

public class Diskonpedia {
    public static final InputReader input = new InputReader(System.in);
    public static final PrintWriter output = new PrintWriter(System.out);

    public static void main (String[] args) {
        
    }




    // fast input reader
    // taken from https://codeforces.com/submissions/Petr
    // further reading:
    // https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
    // https://github.com/williamfiset/FastJavaIO (faster alg)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
