package com.example.projetoparadigmas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SearchWithoutThreads {
    public static int searchPhraseInDirectory(File dir, String phrase) throws IOException {
        int totalOccurrences = 0;

        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    totalOccurrences += searchPhraseInFile(file, phrase);
                }
            }
        }
        return totalOccurrences;
    }

    public static int searchPhraseInFile(File file, String phrase) throws IOException {
        int occurrences = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                occurrences += countOccurrences(line, phrase);
            }
        }

        return occurrences;
    }

    private static int countOccurrences(String line, String phrase) {
        int count = 0;
        int idx = 0;

        while ((idx = line.indexOf(phrase, idx)) != -1) {
            count++;
            idx += phrase.length();
        }

        return count;
    }
}
