package com.example.projetoparadigmas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Buscado de String");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        // Código abaixo é de teste. Na hora de integrar na interface gráfica, os inputs que precisaremos são:
        //  - Caminho do diretório
        //  - String de pesquisa
        //  - Número de threads
        // A saída serão:
        //  - Os tempos de de execução de cada uma das abordagens(Com/Sem threads)
        //  - Performance %

//        File dir = new File("src/main/resources/com/example/projetoparadigmas/files"); // Caminhinho do diretório
//        String phrase = "Arquivo número"; // String de pesquisa
//        int nThreads = 4; // Número de threads
//
//        // Medição da execução sem threads
//        long startWithoutThreads = System.currentTimeMillis();
//        int occurrencesWithoutThreads = 0;
//        try {
//            occurrencesWithoutThreads = SearchWithoutThreads.searchPhraseInDirectory(dir, phrase);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        long endWithoutThreads = System.currentTimeMillis();
//        long timeWithoutThreads = endWithoutThreads - startWithoutThreads;
//
//        // Medição da execução com threads
//        long startWithThreads = System.currentTimeMillis();
//        int occurrencesWithThreads = 0;
//        try {
//            occurrencesWithThreads = SearchWithThreads.searchPhraseInDirectory(dir, phrase, nThreads);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        long endWithThreads = System.currentTimeMillis();
//        long timeWithThreads = endWithThreads - startWithThreads;
//
//        // Exibe os resultados
//        System.out.println("Total de ocorrências encontradas (sem threads): " + occurrencesWithoutThreads);
//        System.out.println("Tempo gasto (sem threads): " + timeWithoutThreads + " ms");
//
//        System.out.println("Total de ocorrências encontradas (com threads): " + occurrencesWithThreads);
//        System.out.println("Tempo gasto (com threads): " + timeWithThreads + " ms");
//
//        // Cálculo da melhoria de performance
//        double improvement = ((double) (timeWithoutThreads - timeWithThreads) / timeWithoutThreads) * 100;
//        System.out.printf("A melhoria de performance com threads foi de: %.2f%%\n", improvement);

    }
}