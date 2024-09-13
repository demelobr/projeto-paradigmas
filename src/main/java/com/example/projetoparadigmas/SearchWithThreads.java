package com.example.projetoparadigmas;

import java.io.File;

public class SearchWithThreads {
    private static long timeExecutionWithThreads;
    private int numberOfThreads;
    private String searchPhrase;
    private String directory;

    public SearchWithThreads(int numberOfThreads, String searchPhrase, String directory) throws InterruptedException {
        this.numberOfThreads = numberOfThreads;
        this.searchPhrase = searchPhrase;
        this.directory = directory;
        timeExecutionWithThreads = 0;
        this.run();
    }

    private void run() throws InterruptedException {
        long timeStartWithThreads = System.currentTimeMillis();

        File dir = new File(this.directory); // Caminhinho do diretório

        File[] files = dir.listFiles(); // Carrega os arquivos do diretório

        if (files != null) {

            Buffer buffer = new Buffer(files);
            Thread[] threads = new Thread[this.numberOfThreads];

            for (int i = 0; i < this.numberOfThreads; i++) {
                threads[i] = new ThreadHeranca(buffer, "Thread "+ i, this.searchPhrase);
                threads[i].start();
            }

            for (int i = 0; i < this.numberOfThreads; i++) {
                threads[i].join();
            }
        }

        long timeEndWithThreads = System.currentTimeMillis();
        timeExecutionWithThreads = timeEndWithThreads - timeStartWithThreads;

    }

    public static long getTimeExecution() { return timeExecutionWithThreads; }
}
