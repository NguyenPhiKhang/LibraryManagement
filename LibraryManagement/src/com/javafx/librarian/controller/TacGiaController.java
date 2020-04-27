package com.javafx.librarian.controller;

import com.javafx.librarian.model.TacGia;
import com.javafx.librarian.service.TacGiaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TacGiaController implements Initializable {
    //region khai báo biến controls
    @FXML
    public TableView<TacGia> tbhienthi;
    @FXML
    public TableColumn<TacGia, Integer> tbcMaTacGia;
    @FXML
    public TableColumn<TacGia, String> tbcTenTacGia;
    @FXML
    public TextField txtTenTacGia;
    @FXML
    public TextField txtMaTacGia;
    @FXML
    public Button btnThem;
    @FXML
    public Button btnXoa;
    @FXML
    public Button btnSua;
    @FXML
    public Button btnLuu;
    @FXML
    public Tab tpTheLoai;
    //endregion

    //region controller
    private ObservableList<TacGia> listTacGia;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCell();
        loadData();
    }

    private void setCell() {
        tbcMaTacGia.setCellValueFactory(cellData -> cellData.getValue().maTacGiaProperty().asObject());
        tbcTenTacGia.setCellValueFactory(cellData -> cellData.getValue().tenTacGiaProperty());
    }

    private void loadData() {
        TacGiaService.getInstance().getAllTacGia();
        listTacGia = FXCollections.observableArrayList(TacGiaService.getInstance().getAllTacGia());
        tbhienthi.setItems(listTacGia);
    }

    public void refreshTable() {
        listTacGia.clear();
        loadData();
    }

    private void clearInput() {
        txtTenTacGia.setText("");
        txtMaTacGia.setText("");
    }

    public void bindingData() {
        TacGia temp = tbhienthi.getSelectionModel().getSelectedItem();
        txtMaTacGia.setText((String.valueOf(temp.getMaTacGia())));
        txtTenTacGia.setText(temp.getTenTacGia());
    }

    public void btnXoa_Click(ActionEvent event) {

        if (tbhienthi.getSelectionModel().getSelectedIndex() >= 0) {
            TacGia temp = tbhienthi.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa tác giả");
            alert.setHeaderText("Bạn muốn xóa tác giả này ra khỏi danh sách?");
            alert.setContentText("[" + temp.getMaTacGia() + "] " + temp.getTenTacGia());

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
            } else if (option.get() == ButtonType.OK) {
                TacGiaService.getInstance().deleteTacGia(temp.getMaTacGia());
                refreshTable();
                clearInput();
            } else if (option.get() == ButtonType.CANCEL) {
            } else {
            }
        }

    }

    public void btnThem_Click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/AddTacGiaDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Thêm sách");
            dialogStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            //
            AddTacGiaController addTacGia = loader.getController();
            addTacGia.setTacGia(this);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSua_Click(ActionEvent event) {
        if (tbhienthi.getSelectionModel().getSelectedIndex() >= 0) {
            TacGia temp = tbhienthi.getSelectionModel().getSelectedItem();
            //
            //refreshTable();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/EditTacGiaDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Thêm sách");
                dialogStage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                //
                EditTacGiaController editTacGia = loader.getController();
                editTacGia.setTacGia(this);
                editTacGia.setEditTacGia(temp);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
