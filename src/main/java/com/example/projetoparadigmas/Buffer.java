package com.example.projetoparadigmas;

import java.io.File;

public class Buffer {//implementação thread safe

    private static int occurences = 0;
    private final File[] files;
    private int top;

    public Buffer(File[] files) {
        this.files = files;
        this.top = files.length - 1;
        occurences = 0;
    }

    public static synchronized void append(int cont) {
        occurences+=cont;
    }

    public synchronized File pop() {
        if (top >= 0) {
            File file = files[top];
            files[top] = null;
            top--;
            return file;
        } else {
            return null;
        }
    }

    public synchronized String toString(){
        return String.valueOf(occurences);
    }

    public static int getOcurrences() {return occurences;}
}