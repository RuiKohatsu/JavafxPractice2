package com.example.javafxpractice2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class User2Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(User2Application.class.getResource("User2-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 570, 420);
        stage.setTitle("ユーザー管理アプリ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}