package com.example.projetoparadigmas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ThreadHeranca extends Thread {

    private final Buffer buffer;//é thread safe
    private final String threadName;
    private int occurrencesInThread;
    private int occurrenceInFiles;
    private final String phrase;

    public ThreadHeranca(Buffer buf, String threadName, String searchPhrase) {
        this.buffer = buf;
        this.threadName = threadName;
        this.occurrencesInThread = 0;
        this.occurrenceInFiles = 0;
        this.phrase = searchPhrase;
    }

    @Override
    public void run() {
        while (true) {
            File file = buffer.pop();
            if (file != null) {

                try {
                    sleep((long) Math.random());
                } catch (InterruptedException ie) {//se chegar mensagem para interromper sono
                }

                try {
                    occurrenceInFiles = searchPhraseInFile(file, phrase);
                    occurrencesInThread++;
                    buffer.append(occurrenceInFiles);
                    System.out.println(threadName + " encontrou " + occurrenceInFiles + " palavras no arquivo: "+ file.getName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                System.out.println("Arquivos acessados pelo " + threadName + ": " + occurrencesInThread);
                return;
            }
        }
    }

    public int searchPhraseInFile(File file, String phrase) throws IOException {
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