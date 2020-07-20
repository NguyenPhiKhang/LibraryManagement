package com.javafx.librarian.controller;

import com.javafx.librarian.model.Sach;
import com.javafx.librarian.model.TheLoai;
import com.javafx.librarian.service.SachService;
import com.javafx.librarian.service.TacGiaService;
import com.javafx.librarian.service.TheLoaiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class SachController implements Initializable {
    //region khai báo biến controls
    @FXML
    public TableView<Sach> tableSach;
    @FXML
    public TableColumn<Sach, String> colMaSach;
    @FXML
    public TableColumn<Sach, String> colTenSach;
    @FXML
    public TableColumn<Sach, String> colMaTheLoai;
    @FXML
    public TableColumn<Sach, String> colMaTacGia;
    @FXML
    public TableColumn<Sach, Integer> colNamXB;
    @FXML
    public TableColumn<Sach, String> colNXB;
    @FXML
    public TableColumn<Sach, Date> colNgayNhap;
    @FXML
    public TableColumn<Sach, Integer> colTriGia;
    @FXML
    public TableColumn<Sach, String> colTinhTrang;
    @FXML
    public TextField txtMaSach;
    @FXML
    public TextField txtTenSach;
    @FXML
    public TextField txtMaTheLoai;
    @FXML
    public TextField txtMaTacGia;
    @FXML
    public TextField txtNamXB;
    @FXML
    public TextField txtNXB;
    @FXML
    public TextField txtNgayNhap;
    @FXML
    public TextField txtTriGia;
    @FXML
    public RadioButton rdbTrong;
    @FXML
    public RadioButton rdbDangMuon;
    @FXML
    public Button btnThemSach;
    @FXML
    public Button btnXoaSach;
    @FXML
    public Button btnSuaSach;
    @FXML
    public TextField textTimKiem;
    @FXML
    public AnchorPane panelSach;
    @FXML
    public ImageView imgAnhBia;
    //endregion

    //region controller
    private ObservableList<Sach> listSach;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCell();
        loadData();

        txtMaSach.setDisable(true);
        txtTenSach.setDisable(true);
        txtMaTacGia.setDisable(true);
        txtMaTheLoai.setDisable(true);
        txtNamXB.setDisable(true);
        txtNXB.setDisable(true);
        txtNgayNhap.setDisable(true);
        txtTriGia.setDisable(true);
        rdbTrong.setDisable(true);
        rdbDangMuon.setDisable(true);
    }

    private void setCell() {
        colMaSach.setCellValueFactory(cellData -> cellData.getValue().maSachProperty());
        colTenSach.setCellValueFactory(cellData -> cellData.getValue().tenSachProperty());
        colMaTheLoai.setCellValueFactory(cellData -> cellData.getValue().maTheLoaiProperty());
        colMaTacGia.setCellValueFactory(cellData -> cellData.getValue().maTacGiaProperty());
        colNamXB.setCellValueFactory(cellData -> cellData.getValue().namXBProperty().asObject());
        colNXB.setCellValueFactory(cellData -> cellData.getValue().nxbProperty());
        colNgayNhap.setCellValueFactory(cellData -> cellData.getValue().ngayNhapProperty());
        colTriGia.setCellValueFactory(cellData -> cellData.getValue().triGiaProperty().asObject());
        colTinhTrang.setCellValueFactory(cellData -> cellData.getValue().tinhTrangProperty() );
    }

    private void loadData() {
        listSach = FXCollections.observableArrayList(SachService.getInstance().getAllSach());
        tableSach.setItems(listSach);
    }

    public void refreshTable() {
        listSach.clear();
        loadData();
        clearInput();
    }

    public void clearInput() {
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtMaTacGia.setText("");
        txtMaTheLoai.setText("");
        txtNamXB.setText("");
        txtNXB.setText("");
        txtNgayNhap.setText("");
        txtTriGia.setText("");
        rdbTrong.setSelected(true);
    }

    public void bindingData() {
        Sach temp = tableSach.getSelectionModel().getSelectedItem();
        System.out.println(TacGiaService.getInstance().getTacGiaByID(temp.getMaTacGia()));
        txtMaSach.setText(temp.getMaSach());
        txtTenSach.setText(temp.getTenSach());
        txtMaTacGia.setText((String.valueOf(temp.getMaTacGia())) + " - " + TacGiaService.getInstance().getTacGiaByID(temp.getMaTacGia()).getTenTacGia());
        txtMaTheLoai.setText((String.valueOf(temp.getMaTheLoai())) + " - " + TheLoaiService.getInstance().getTheLoaiByID(temp.getMaTheLoai()).getTenTheLoai());
        txtNamXB.setText((String.valueOf(temp.getNamXB())));;
        txtNXB.setText(temp.getNXB());
        txtNgayNhap.setText((String.valueOf(temp.getNgayNhap())));
        txtTriGia.setText((String.valueOf(temp.getTriGia())));
        if (String.valueOf(temp.getTinhTrang()).equals("Trống"))
            rdbTrong.setSelected(true);
        else
            rdbDangMuon.setSelected(true);

        imgAnhBia.setImage(temp.getImage());
        imgAnhBia.setCache(true);

    }

    public void btnThemSach_Click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/AddSachDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            //
            AddSachController addSachController = loader.getController();
            addSachController.setSachController(this);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSuaSach_Click(ActionEvent event) {
        if (tableSach.getSelectionModel().getSelectedIndex() >= 0) {
            Sach temp = tableSach.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/EditSachDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                //
                EditSachController editSachController = loader.getController();
                editSachController.setSachController(this);
                editSachController.setEditSach(temp);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnXoaSach_Click(ActionEvent event) {

        if (tableSach.getSelectionModel().getSelectedIndex() >= 0) {
            Sach temp = tableSach.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa sách");
            alert.setHeaderText("Bạn muốn xóa sách này ra khỏi danh sách?");
            alert.setContentText("[" + temp.getMaSach() + "] " + temp.getTenSach());

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
            } else if (option.get() == ButtonType.OK) {
                SachService.getInstance().deleteSach(temp.getMaSach());
                refreshTable();
            } else if (option.get() == ButtonType.CANCEL) {
            } else {
            }
        }

    }
}
