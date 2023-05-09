module com.example.javafxpractice2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxpractice2 to javafx.fxml;
    exports com.example.javafxpractice2;
}