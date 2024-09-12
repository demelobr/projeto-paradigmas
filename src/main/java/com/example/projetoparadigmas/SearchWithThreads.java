package com.example.projetoparadigmas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SearchWithThreads {
    private static class SearchTask implements Runnable {
        private final File file;
        private final String phrase;
        private int occurrences;

        public SearchTask(File file, String phrase) {
            this.file = file;
            this.phrase = phrase;
        }

        @Override
        public void run() {
            try {
                occurrences = searchPhraseInFile(file, phrase);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public int getOccurrences() {
            return occurrences;
        }
    }

    public static int searchPhraseInDirectory(File dir, String phrase, int nThreads) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        List<Future<?>> futures = new ArrayList<>();
        List<SearchTask> tasks = new ArrayList<>();
        int totalOccurrences = 0;

        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    SearchTask task = new SearchTask(file, phrase);
                    tasks.add(task);
                    futures.add(executor.submit(task));
                }
            }
        }

        for (Future<?> future : futures) {
            future.get(); // Espera a conclusão de todas as threads
        }

        for (SearchTask task : tasks) {
            totalOccurrences += task.getOccurrences();
        }

        executor.shutdown();
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
