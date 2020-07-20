package com.javafx.librarian.controller;

import com.javafx.librarian.model.Account;
import com.javafx.librarian.service.AccountService;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    public MenuButton lbUsername;
    public MenuItem menuItemInfo;
    public MenuItem menuItemDMK;
    public AnchorPane paneMain;
    private Stage stage;
    private Double mousepX;
    private Double mousepY;

    @FXML
    public Label lbNameAccount;
    @FXML
    public JFXButton btnTrangChu;
    @FXML
    public BorderPane borderPaneMain;
    @FXML
    public JFXButton btnQLDocGIa;
    @FXML
    public AnchorPane tileBar;
    @FXML
    public JFXButton btnMaximize;
    @FXML
    public JFXButton btnClose;
    @FXML
    public FontAwesomeIcon iconClose;
    @FXML
    public JFXButton btnMinimize;
    @FXML
    public FontAwesomeIcon iconMinimize;
    @FXML
    public FontAwesomeIcon iconMaximize;

    Account User;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tileBar.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        tileBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - mousepX);
            stage.setY(mouseEvent.getScreenY() - mousepY);
        });

//        btnTrangChu.requestFocus();
    }

    public void tiledBarButtonMouseEnter(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: red; -fx-background-radius: 15");
        iconClose.setVisible(true);
        btnMinimize.setStyle("-fx-background-color: #d7d744; -fx-background-radius: 15");
        iconMinimize.setVisible(true);
        btnMaximize.setStyle("-fx-background-color: #248c19; -fx-background-radius: 15");
        iconMaximize.setVisible(true);
    }

    public void tiledBarButtonMouseExit(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: #d6d6d6; -fx-background-radius: 15");
        iconClose.setVisible(false);
        btnMinimize.setStyle("-fx-background-color: #d6d6d6; -fx-background-radius: 15");
        iconMinimize.setVisible(false);
        btnMaximize.setStyle("-fx-background-color: #d6d6d6; -fx-background-radius: 15");
        iconMaximize.setVisible(false);
    }

    public void btnCloseAction(ActionEvent actionEvent) {
//        System.exit(0);
        stage.close();
    }

    public void btnMinimizeAction(ActionEvent actionEvent) {
        stage.setIconified(!stage.isIconified());
    }

    public void btnMaximizeAction(ActionEvent actionEvent) {
        stage.setFullScreen(!stage.isFullScreen());
    }

    public void setMainStage(Stage stage, Account user) {
        this.stage = stage;
        this.User = new Account(user.getUsername(), user.getPassword(), user.getIdper(), user.getName(), user.getEmail());
        lbNameAccount.setText(this.User.getName());
        lbUsername.setText(this.User.getUsername());
        if(user.getIdper()==1){
            menuItemInfo.setVisible(true);
            menuItemDMK.setVisible(false);
        }else{
            menuItemInfo.setVisible(false);
            menuItemDMK.setVisible(true);
        }
    }

    public void btnTrangChuAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/TrangChuView.fxml"));
            AnchorPane frmDocgiaView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(frmDocgiaView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnQLDGAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/docgia/frmDocGiaView.fxml"));
            AnchorPane frmDocgiaView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(frmDocgiaView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnQLSAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/MenuSachView.fxml"));
            AnchorPane menuSachView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(menuSachView);

            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnQLNVAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/NhanVienView.fxml"));
            AnchorPane NVView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(NVView);
            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnQLMTAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/MenuMuonTraView.fxml"));
            AnchorPane MTView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(MTView);
            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void MenuThongTinClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/infoUserView.fxml"));
            AnchorPane infoView = (AnchorPane) loader.load();

            InfoUserController controller = loader.getController();
            controller.setAccount(this.User);

            borderPaneMain.setCenter(infoView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnBCTKAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/MenuBaoCao.fxml"));
            AnchorPane MTView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(MTView);

            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void MenuDangXuatClicked(ActionEvent actionEvent) throws IOException {
        String tilte = "Sign Out";
        String message = "Đăng xuất thành công!";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));

        Stage stageLogin = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/login/LoginView.fxml"));
        AnchorPane rootLayout = loader.load();

        LoginViewController controller = loader.getController();
        controller.setMainStage(stageLogin);

        Scene scene = new Scene(rootLayout);
        scene.setFill(Color.TRANSPARENT);
        stageLogin.setScene(scene);
        stageLogin.initStyle(StageStyle.TRANSPARENT);
        this.stage.hide();
        stageLogin.show();
    }

    public void menuItemDMKClicked(ActionEvent actionEvent){
                try {
                    String mk = User.getPassword();
                    // Load the fxml file and create a new stage for the popup dialog.
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../view/DoiMatKhauView.fxml"));
                    AnchorPane page = (AnchorPane) loader.load();

                    DoiMatKhauController controller = loader.getController();
                    controller.setMK(User);

                    // Create the dialog Stage.
                    Stage dialogStage = new Stage();
                    Scene scene = new Scene(page);
                    scene.setFill(Color.TRANSPARENT);
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.initOwner(paneMain.getScene().getWindow());
                    dialogStage.initStyle(StageStyle.TRANSPARENT);
                    dialogStage.setScene(scene);

                    // Show the dialog and wait until the user closes it
                    dialogStage.showAndWait();

                    if (!mk.equals(User.getPassword())) {
                        int rs = AccountService.getInstance().editUser(User);
                        System.out.println(rs);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    public void btnHeThongAction(ActionEvent actionEvent)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/MenuHeThong.fxml"));
            AnchorPane MTView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(MTView);
            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
