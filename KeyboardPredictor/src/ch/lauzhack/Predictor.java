package ch.lauzhack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Loic on 19.11.2016.
 */
public class Predictor {
    private ProbabilitiesDatabase probs;
    private String alphabet = "abcdefghijklmnopqrstuvwxyz -'";
    private int n;

    public Predictor(int n) {
        this.probs = new ProbabilitiesDatabase(n);
        this.n = n;
        probs.loadTextFile("big.txt");
        probs.loadTextFile("words2.txt");
    }

    public boolean isValidChar(char c) {
        return alphabet.contains("" + c);
    }

    public double computeProbability(String prev, char next) {
        double denom = probs.getNgramProbabilities(prev);
        System.out.println(prev + ": " + denom);
        if (denom <= 0) {
            return 0;
        }
        return probs.getNgramProbabilities(prev + next)/denom;
    }

    public List<CharProbPair> getNextChar(String text) {
        List<CharProbPair> letters = new ArrayList<>();

        String last = text;

        letters.clear();
        for (char c : alphabet.toCharArray()) {
            letters.add(new CharProbPair(c, computeProbability(last, c)));
        }
        Collections.sort(letters);

        // No prediction ? Only show space
        if (letters.get(0).getProbability() == 0) {
            letters.clear();
            letters.add(new CharProbPair(' ', 1.0));
        }

        return letters;
    }
}
