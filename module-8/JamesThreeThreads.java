import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.util.Random;

public class JamesThreeThreads extends JFrame {

    private static final int CHAR_COUNT = 10_000; // characters per thread

    private final JTextArea textArea = new JTextArea(20, 60);
    private final Random random = new Random();

    public JamesThreeThreads() {
        super("Three Threads");

        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // Random lowercase letter a–z 
    public char randomLetter() {
        return (char) ('a' + random.nextInt(26));
    }

    // Random digit 0–9 
    public char randomDigit() {
        return (char) ('0' + random.nextInt(10));
    }

    // Random symbol from the specified set
    public char randomSymbol() {
        char[] symbols = {'!', '@', '#', '$', '%', '&', '*'};
        return symbols[random.nextInt(symbols.length)];
    }

    // Three worker threads
    public void startThreads() {
        Thread lettersThread = new Thread(() -> {
            for (int i = 0; i < CHAR_COUNT; i++) {
                char c = randomLetter();
                appendToTextArea(c);
            }
        }, "LettersThread");

        Thread digitsThread = new Thread(() -> {
            for (int i = 0; i < CHAR_COUNT; i++) {
                char c = randomDigit();
                appendToTextArea(c);
            }
        }, "DigitsThread");

        Thread symbolsThread = new Thread(() -> {
            for (int i = 0; i < CHAR_COUNT; i++) {
                char c = randomSymbol();
                appendToTextArea(c);
            }
        }, "SymbolsThread");

        lettersThread.start();
        digitsThread.start();
        symbolsThread.start();
    }

        private void appendToTextArea(char c) {
        SwingUtilities.invokeLater(() -> textArea.append(String.valueOf(c)));
    }

        private static void runTests() {
        JamesThreeThreads instance = new JamesThreeThreads();

        // Test each method a bunch of times
        for (int i = 0; i < 1000; i++) {
            char letter = instance.randomLetter();
            if (letter < 'a' || letter > 'z') {
                throw new AssertionError("randomLetter() returned invalid char: " + letter);
            }

            char digit = instance.randomDigit();
            if (digit < '0' || digit > '9') {
                throw new AssertionError("randomDigit() returned invalid char: " + digit);
            }

            char symbol = instance.randomSymbol();
            if ("!@#$%&*".indexOf(symbol) == -1) {
                throw new AssertionError("randomSymbol() returned invalid char: " + symbol);
            }
        }

        System.out.println("All tests passed.");
    }

    // Main method 

    public static void main(String[] args) {
        // Run simple tests first
        runTests();

        // Then start the GUI and threads
        SwingUtilities.invokeLater(() -> {
            JamesThreeThreads frame = new JamesThreeThreads();
            frame.setVisible(true);
            frame.startThreads();
        });
    }
}
