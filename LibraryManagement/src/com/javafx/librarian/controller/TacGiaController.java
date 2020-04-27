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
        txtMaTacGia.setDisable(true);
        txtTenTacGia.setDisable(true);
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
            TacGiaService.getInstance().deleteTacGia(temp.getMaTacGia());
            refreshTable();
            clearInput();
        }

    }

    public void btnThem_Click(ActionEvent event) {
//        clearInput();
//        txtMaTacGia.setDisable(false);
//       txtTenTacGia.setDisable(false);
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
            String tenTacGiaMoi = txtTenTacGia.getText();
            TacGia temp = tbhienthi.getSelectionModel().getSelectedItem();
            TacGiaService.getInstance().editTacGia(temp.getMaTacGia(), tenTacGiaMoi);
            refreshTable();
        }
    }

    public void btnLuu_Click(ActionEvent event) {
        int maTacGia = Integer.parseInt(txtMaTacGia.getText());
        String tenTacGia = txtTenTacGia.getText();
        TacGiaService.getInstance().addTacGia(maTacGia, tenTacGia);
        refreshTable();
    }

}
