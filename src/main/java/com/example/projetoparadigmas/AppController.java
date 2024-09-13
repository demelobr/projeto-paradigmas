package com.example.projetoparadigmas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    String loadingImagePath = "src/main/resources/com/example/projetoparadigmas/loading.gif";
    String checkImagePath = "src/main/resources/com/example/projetoparadigmas/check.png";
    private List<Integer> listaNumThreads;

    public AppController() {
        listaNumThreads = new ArrayList<>();
    }

    @FXML
    private Button buttonEscolherDiretorio;

    @FXML
    private Button buttonPlay;

    @FXML
    private ChoiceBox<Integer> choiceBoxNumThreads;

    @FXML
    private ImageView imageLoadingComThreads;

    @FXML
    private ImageView imageLoadingSemThreads;

    @FXML
    private Label labelCaminhoDoDiretorio;

    @FXML
    private Text labelQuantidadeDeOcorrenciasComThreads;

    @FXML
    private Text labelQuantidadeDeOcorrenciasSemThreads;

    @FXML
    private Text labelResultado;

    @FXML
    private Text labelTempoTotalComThreads;

    @FXML
    private Text labelTempoTotalSemThreads;

    @FXML
    private TextField textFieldString;

    @FXML
    public void iniciarSimulacao(){
        File dir = new File(labelCaminhoDoDiretorio.getText());
        String phrase = textFieldString.getText();
        int nThreads = choiceBoxNumThreads.getValue();

        imageLoadingSemThreads.setImage(new Image("file:"+loadingImagePath));
        imageLoadingComThreads.setImage(new Image("file:"+loadingImagePath));

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ev) {
                ev.printStackTrace();
            }
            Platform.runLater(() -> {
                // Medição da execução sem threads

                long startWithoutThreads = System.currentTimeMillis();
                int occurrencesWithoutThreads = 0;
                try {
                    occurrencesWithoutThreads = SearchWithoutThreads.searchPhraseInDirectory(dir, phrase);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                long endWithoutThreads = System.currentTimeMillis();
                long timeWithoutThreads = endWithoutThreads - startWithoutThreads;

                imageLoadingSemThreads.setImage(new Image("file:"+checkImagePath));
                labelQuantidadeDeOcorrenciasSemThreads.setText(String.valueOf(occurrencesWithoutThreads));
                labelTempoTotalSemThreads.setText(timeWithoutThreads + " ms");

                try {
                    SearchWithThreads swt = new SearchWithThreads(nThreads, phrase, labelCaminhoDoDiretorio.getText());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                imageLoadingComThreads.setImage(new Image("file:"+checkImagePath));
                labelQuantidadeDeOcorrenciasComThreads.setText(String.valueOf(Buffer.getOcurrences()));
                long timeWithThreads = SearchWithThreads.getTimeExecution();
                labelTempoTotalComThreads.setText(timeWithThreads + " ms");

                double improvement = ((double) (timeWithoutThreads - timeWithThreads) / timeWithoutThreads) * 100;
                labelResultado.setText(String.format("%.2f%%", improvement));
            });
        }).start();

    }

    @FXML
    public void selecionarCaminhoDoDiretorio(MouseEvent event){
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DirectoryChooser dc = new DirectoryChooser();
        File selectFolder = dc.showDialog(stg);
        if(selectFolder != null){
            labelCaminhoDoDiretorio.setText(selectFolder.getAbsolutePath());
        }
    }

    void delayHidePushMsg(){
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ev) {
                ev.printStackTrace();
            }
            Platform.runLater(() -> {

            });
        }).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldString.setText("");
        for(int i = 2; i < 11; i++) listaNumThreads.add(i);
        choiceBoxNumThreads.getItems().addAll(listaNumThreads);
        choiceBoxNumThreads.setValue(listaNumThreads.getFirst());
        labelCaminhoDoDiretorio.setText("");
        labelQuantidadeDeOcorrenciasSemThreads.setText("");
        labelTempoTotalSemThreads.setText("");
        labelQuantidadeDeOcorrenciasComThreads.setText("");
        labelTempoTotalComThreads.setText("");
        labelResultado.setText("");
    }
}