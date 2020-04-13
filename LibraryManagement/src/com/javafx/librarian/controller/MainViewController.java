package com.javafx.librarian.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    private Stage stage;
    private Double mousepX;
    private Double mousepY;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tileBar.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        tileBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - mousepX);
            stage.setY(mouseEvent.getScreenY()- mousepY);
        });
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

    public void setMainStage(Stage stage){
        this.stage = stage;
    }

    public void btnQLDGAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/DocGiaView.fxml"));
            AnchorPane docgiaView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(docgiaView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
