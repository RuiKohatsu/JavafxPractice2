package com.example.javafxpractice2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class User2Controller {

    @FXML
    private TextField addName;

    @FXML
    private TextField addScore;

    @FXML
    private ComboBox<String> addCompany;

    @FXML
    private TableColumn<User2,Integer> iColumn;
    @FXML
    private TableColumn<User2,String> cColumn;
    @FXML
    private TableColumn<User2,String> nColumn;
    @FXML
    private TableColumn<User2,Integer> sColumn;

    @FXML
    private ComboBox<String> editCompany;
    @FXML
    private TextField editName;
    @FXML
    private TextField editScore;
    @FXML
    private Label error;

    @FXML
    private TableView<User2> myTableView;
    @FXML
    private TextField searchName;

    private int id;

    ObservableList<User2> user2List = FXCollections.observableArrayList();

    ObservableList<User2> searchList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        myTableView.setTableMenuButtonVisible(false);
//        System.out.println(myTableView.getItems());
        user2List.add(new User2(1, "株式会社A", "山田太郎", 85));
        user2List.add(new User2(2, "株式会社B", "田中宗次", 72));
        user2List.add(new User2(3, "株式会社C", "佐藤道則", 86));
//        System.out.println(userList.size());
        iColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        nColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        myTableView.setItems(user2List);

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
            var idSize = user2List.size() + 1;

            error.setText("");

            user2List.add(new User2(idSize, infoCompany, infoName, infoScore));

        }
    }

    public void selectTable(){
        int index = myTableView.getSelectionModel().getSelectedIndex();
        // 行のデータを取得
        Object clickedRowData = myTableView.getSelectionModel().getSelectedItem();
        // 列のデータを取得
        var specificIdData = ((User2) clickedRowData).getId();
        var specificCompanyData = ((User2) clickedRowData).getCompany();
        var specificNameData = ((User2) clickedRowData).getName();
        var specificScoreData = ((User2) clickedRowData).getScore();

        this.id = specificIdData;
        editCompany.setValue(specificCompanyData);
        editName.setText(specificNameData);
        editScore.setText( String.valueOf(specificScoreData));
    }

    public void deleteInfo(ActionEvent actionEvent){
        user2List.remove(this.id -1);
        int newId = 1;
        for(User2 list : user2List){
            list.setId(newId);
            newId++;
        }
    }

    public void updateInfo(){
        var infoCompany = editCompany.getValue();
        var infoName = editName.getText();
        var infoScore = Integer.parseInt(editScore.getText());

        User2 updateUser2 = new User2(this.id, infoCompany, infoName, infoScore);

        user2List.set(this.id -1, updateUser2);

    }

    public void search(){
        var keyword = searchName.getText();
        var idSize = user2List.size();
        for(var i = 0; i < idSize; i++){
            if(user2List.get(i).getName().contains(searchName.getText())){
                searchList.add(new User2(i+1, user2List.get(i).getCompany(), user2List.get(i).getName(), user2List.get(i).getScore()));
            }
        }
        myTableView.setItems(searchList);

    }

    public void back(){
//        var idSize = user2List.size();
        searchList.clear();
        myTableView.setItems(user2List);
    }


}