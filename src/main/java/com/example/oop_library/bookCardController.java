package com.example.oop_library;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class bookCardController {

    @FXML
    private Label bookAuthor;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;

    @FXML
    private VBox bookCard;

    private Books book; // Đổi tên từ 'b' để rõ ràng hơn.

    private DashboardController dashboardController;

    /**
     * Thiết lập dữ liệu cho thẻ sách.
     * @param book đối tượng sách cần hiển thị.
     * @param dashboardController controller chính của dashboard.
     */
    public void setData(Books book, DashboardController dashboardController) {
        try {
            // Kiểm tra xem hình ảnh có tồn tại không
            Image image = new Image(book.getThumbNail(), true); // true để bật tải ảnh không đồng bộ
            bookImage.setImage(image);
            bookName.setText(book.getBookName());
            bookAuthor.setText(book.getBookAuthor());

            this.dashboardController = dashboardController;
            this.book = book;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi thiết lập dữ liệu: " + e.getMessage());
        }
    }

    /**
     * Xử lý khi chuột di chuyển vào thẻ sách.
     */
    @FXML
    private void handleMouseEnter() {
        if (bookCard != null) {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), bookCard);
            scaleTransition.setToX(1.05); // Phóng to 5%.
            scaleTransition.setToY(1.05);
            scaleTransition.play();
        }
    }

    /**
     * Xử lý khi chuột di chuyển ra khỏi thẻ sách.
     */
    @FXML
    private void handleMouseExit() {
        if (bookCard != null) {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), bookCard);
            scaleTransition.setToX(1.0); // Trở về kích thước gốc.
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        }
    }

    /**
     * Xử lý khi nhấn chuột vào thẻ sách.
     * @param e sự kiện chuột.
     */
    @FXML
    private void handleMouseClick(MouseEvent e) {
        if (dashboardController != null && book != null) {
            dashboardController.switchToBookDetail(book);
        } else {
            System.err.println("DashboardController hoặc Book chưa được khởi tạo!");
        }
    }
}
