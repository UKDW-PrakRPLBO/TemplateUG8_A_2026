package org.rplbo.app.ug8.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import org.rplbo.app.ug8.UmbrellaApp;
import org.rplbo.app.ug8.UmbrellaDBManager;

public class LoginController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblStatus;

    @FXML
    private void handleLogin(ActionEvent event) {
        // TODO : Hapus Kode dibawah ini sebelum mengerjakan (Hanya Sebagai Contoh)
        // ==============================================================================
        String user = txtUsername.getText();
        String pass = txtPassword.getText();

        // Autentikasi Hardcoded (Tidak tersambung ke database)
        if (user.equals("admin") && pass.equals("admin")) {
            try {
                // Berhasil: Pindah ke halaman Inventory
                UmbrellaApp.switchScene("umbrella-view.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lblStatus.setText("ACCESS DENIED: INVALID CREDENTIALS");
        }
        // ==============================================================================

        // ==============================================================================
        // TODO 1: PROSES AUTENTIKASI (LOGIN)
        // ==============================================================================
        // 1. Ambil input teks dari txtUsername dan txtPassword.
        // 2. Buat instansiasi dari class UmbrellaDBManager.
        // 3. Panggil metode validateUser() dari db manager tersebut.
        // 4. Jika hasil validasi berhasil (tidak null):
        //    a. Simpan nama user ke variabel statis UmbrellaApp.loggedInUser.
        //    b. Pindah ke halaman "umbrella-view.fxml" menggunakan UmbrellaApp.switchScene().
        // 5. Jika gagal, tampilkan pesan error "AUTHENTICATION FAILED" pada lblStatus.
        // ==============================================================================

        // --- TULIS KODE ANDA DI BAWAH INI ---


    }
}