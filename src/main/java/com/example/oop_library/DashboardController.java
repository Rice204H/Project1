package com.example.oop_library;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private GridPane featuredBooks;

    @FXML
    private VBox centerArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox dashboardMenu;

    @FXML
    private HBox categoryMenu;

    @FXML
    private HBox booksMenu;

    @FXML
    private HBox adminMenu;

    @FXML
    private HBox selectedMenu;

    private VBox oldCenterArea = new VBox();

    // Phương thức khởi tạo
    public DashboardController() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.widthProperty().addListener((obs, oldVal, newVal) -> showFeaturedBooks());
        showFeaturedBooks();
    }

    /**
     * Xử lý sự kiện khi nhấn vào Dashboard
     */
    @FXML
    public void handleDashboardClick() {
        setSelectedMenu(dashboardMenu);
        switchToDashboard();
    }

    /**
     * Xử lý sự kiện khi nhấn vào Category
     */
    @FXML
    public void handleCategoryClick() {
        setSelectedMenu(categoryMenu);
        System.out.println("Category clicked!");
    }

    /**
     * Xử lý sự kiện khi nhấn vào Books Borrowed
     */
    @FXML
    public void handleBooksClick() {
        setSelectedMenu(booksMenu);
        System.out.println("Books clicked!");
    }

    /**
     * Xử lý sự kiện khi nhấn vào Admin
     */
    @FXML
    public void handleAdminClick() {
        setSelectedMenu(adminMenu);
        switchToAdmin();
    }

    /**
     * Hiển thị danh sách Featured Books trong GridPane
     */
    public void showFeaturedBooks() {
        double scrollPaneWidth = scrollPane.getWidth();
        featuredBooks.getChildren().clear();

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < Books.featuredBooksList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/oop_library/bCard.fxml"));
                VBox card = fxmlLoader.load();
                bookCardController cardController = fxmlLoader.getController();
                cardController.setData(Books.featuredBooksList.get(i), this);

                featuredBooks.add(card, column, row);
                GridPane.setMargin(card, new Insets(2));
                column++;

                if (column >= (int) (scrollPaneWidth / (card.getPrefWidth() + 2))) {
                    column = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Chuyển đổi nội dung sang màn hình Dashboard
     */
    public void switchToDashboard() {
        centerArea.getChildren().clear();
        centerArea.getChildren().addAll(oldCenterArea.getChildren());
    }

    /**
     * Chuyển đổi nội dung sang màn hình Admin
     */
    public void switchToAdmin() {
        oldCenterArea.getChildren().clear();
        oldCenterArea.getChildren().addAll(centerArea.getChildren());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/oop_library/AdminView.fxml"));
            VBox adminPane = fxmlLoader.load();

            centerArea.getChildren().clear();
            centerArea.getChildren().add(adminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chuyển đổi nội dung sang màn hình Support
     */
    @FXML
    private void switchToSupport(MouseEvent event) {
        try {
            // Tải file FXML của Support
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/oop_library/Support.fxml"));
            VBox supportPane = fxmlLoader.load();

            // Hiển thị nội dung Support trong centerArea
            oldCenterArea.getChildren().clear();
            oldCenterArea.getChildren().addAll(centerArea.getChildren());

            centerArea.getChildren().clear();
            centerArea.getChildren().add(supportPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Đặt menu được chọn và thay đổi giao diện
     */
    private void setSelectedMenu(HBox menu) {
        if (selectedMenu != null) {
            selectedMenu.setStyle("");
        }
        menu.setStyle("-fx-background-color: #BCE6FF;"); // Màu nền cho menu được chọn
        selectedMenu = menu;
    }

    public void switchToBookDetail(Books b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'switchToBookDetail'");
    }
}
