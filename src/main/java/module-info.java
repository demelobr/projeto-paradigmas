module com.example.projetoparadigmas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projetoparadigmas to javafx.fxml;
    exports com.example.projetoparadigmas;
}