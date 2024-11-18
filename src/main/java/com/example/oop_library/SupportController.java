package com.example.oop_library;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SupportController {
    @FXML
    private TextField title, sender, email, phone;
    @FXML
    private TextArea content;

    @FXML
    private void handleSubmit() {
        // Xử lý logic gửi câu hỏi
        System.out.println("Tiêu đề: " + title.getText());
        System.out.println("Người gửi: " + sender.getText());
        System.out.println("Email: " + email.getText());
        System.out.println("Số điện thoại: " + phone.getText());
        System.out.println("Nội dung: " + content.getText());
    }
}
