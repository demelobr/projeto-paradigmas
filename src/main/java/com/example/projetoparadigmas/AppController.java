package com.example.projetoparadigmas;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
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
    public void selecionarCaminhoDoDiretorio(MouseEvent event){
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DirectoryChooser dc = new DirectoryChooser();
        File selectFolder = dc.showDialog(stg);
        if(selectFolder != null){
            labelCaminhoDoDiretorio.setText(selectFolder.getAbsolutePath());
        }
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