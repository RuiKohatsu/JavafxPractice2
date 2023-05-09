package com.example.javafxpractice2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.lang.reflect.InvocationTargetException;


import java.text.NumberFormat;


public class UserController{

    @FXML
    private TextField addName;

    @FXML
    private TextField addScore;

    @FXML
    private ComboBox<String> addCompany;

    @FXML
    private TableColumn<User,Integer> iColumn;
    @FXML
    private TableColumn<User,String> cColumn;
    @FXML
    private TableColumn<User,String> nColumn;
    @FXML
    private TableColumn<User,Integer> sColumn;

    @FXML
    private ComboBox<String> editCompany;
    @FXML
    private TextField editName;
    @FXML
    private TextField editScore;
    @FXML
    private Label error;

    @FXML
    private TableView<User> myTableView;

    private int id;

    ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        myTableView.setTableMenuButtonVisible(false);
        System.out.println(myTableView.getItems());
        userList.add(new User(1, "株式会社A", "山田太郎", 85));
        userList.add(new User(2, "株式会社B", "田中宗次", 72));
        userList.add(new User(3, "株式会社C", "佐藤道則", 86));
//        System.out.println(userList.size());
        iColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        nColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        myTableView.setItems(userList);

        ObservableList<String> items = FXCollections.observableArrayList("株式会社A", "株式会社B", "株式会社C");
        addCompany.setItems(items);

        ObservableList<String> items1 = FXCollections.observableArrayList("株式会社A", "株式会社B", "株式会社C");
        editCompany.setItems(items1);
    }

    public void addInfo(ActionEvent actionEvent) {
        if (addCompany.getValue() == null || addName.getText().isEmpty() || addScore.getText().isEmpty() ||
                !"0123456789".contains(addScore.getText()) || Integer.parseInt(addScore.getText()) < 0 || Integer.parseInt(addScore.getText()) > 100) {
            error.setText("エラー：TextFieldへの空白　or 不正な値の設定 or 企業の未選択");
        }else{
            var infoCompany = addCompany.getValue();
            var infoName = addName.getText();
            var infoScore = Integer.parseInt(addScore.getText());
            var idSize = userList.size() + 1;

            error.setText("");

            userList.add(new User(idSize, infoCompany, infoName, infoScore));

        }
    }

    public void selectTable(){
        int index = myTableView.getSelectionModel().getSelectedIndex();
        // 行のデータを取得
        Object clickedRowData = myTableView.getSelectionModel().getSelectedItem();
        // 列のデータを取得
        var specificIdData = ((User) clickedRowData).getId();
        var specificCompanyData = ((User) clickedRowData).getCompany();
        var specificNameData = ((User) clickedRowData).getName();
        var specificScoreData = ((User) clickedRowData).getScore();

        this.id = specificIdData;
        editCompany.setValue(specificCompanyData);
        editName.setText(specificNameData);
        editScore.setText( String.valueOf(specificScoreData));
    }

    public void deleteInfo(ActionEvent actionEvent){
        userList.remove(this.id -1);
        int newId = 1;
        for(User list : userList){
            list.setId(newId);
            newId++;
        }
    }

    public void updateInfo(){
        var infoCompany = editCompany.getValue();
        var infoName = editName.getText();
        var infoScore = Integer.parseInt(editScore.getText());

        User updateUser = new User(this.id, infoCompany, infoName, infoScore);

        userList.set(this.id -1, updateUser);

    }


}