package org.rplbo.app.ug8.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.rplbo.app.ug8.InventoryItem;
import org.rplbo.app.ug8.UmbrellaApp;
import org.rplbo.app.ug8.UmbrellaDBManager;

import java.net.URL;
import java.util.ResourceBundle;

public class UmbrellaController implements Initializable {
    @FXML private TextField txtItem, txtAcquired, txtUsed;
    @FXML private TableView<InventoryItem> tableInventory;
    @FXML private TableColumn<InventoryItem, String> colName;
    @FXML private TableColumn<InventoryItem, Integer> colAcquired, colUsed, colStock;

    private UmbrellaDBManager db;
    private ObservableList<InventoryItem> masterData = FXCollections.observableArrayList();
    private InventoryItem selectedItem;

    @FXML
    private void handleLogout() {
        try {
            UmbrellaApp.switchScene("login-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new UmbrellaDBManager();
        System.out.println("LOG: OPERATIVE " + UmbrellaApp.loggedInUser + " ACCESS GRANTED.");

        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colAcquired.setCellValueFactory(new PropertyValueFactory<>("acquired"));
        colUsed.setCellValueFactory(new PropertyValueFactory<>("used"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("totalStock"));

        tableInventory.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedItem = newVal;
                txtItem.setText(newVal.getItemName());
                txtAcquired.setText(String.valueOf(newVal.getAcquired()));
                txtUsed.setText(String.valueOf(newVal.getUsed()));
                txtItem.setDisable(true);
            }
        });
        refreshTable();
    }

    @FXML
    private void handleSave() {
        // ==============================================================================
        // TODO 1: LOGIKA PERBARUI/UPDATE DATA (GRUP A)
        // ==============================================================================
        // 1. Pastikan ada item yang dipilih (cek apakah selectedItem tidak sama dengan null).
        // 2. Ambil nilai teks terbaru dari txtAcquired dan txtUsed, lalu ubah menjadi Integer.
        // 3. HITUNG TOTAL STOCK BARU:
        //    Rumus: stock = acquired - used
        // 4. Buat objek InventoryItem baru menggunakan data yang diperbarui.
        //    PENTING: Ambil nama item dari selectedItem.getItemName(), jangan dari TextBox!
        // 5. Panggil db.updateItem(). Jika berhasil (mengembalikan true), panggil:
        //    - refreshTable()
        //    - clearFields()
        // ==============================================================================

        // --- TULIS KODE ANDA DI BAWAH INI ---


    }

    @FXML
    private void handleAdd() {
        // ==============================================================================
        // TODO 2: LOGIKA TAMBAH DATA (GRUP A)
        // ==============================================================================
        // 1. Ambil nilai teks dari txtAcquired dan txtUsed, lalu ubah menjadi Integer.
        // 2. HITUNG TOTAL STOCK:
        //    Rumus: stock = acquired - used
        // 3. Ambil nilai String dari field txtItem untuk nama item.
        // 4. Buat objek InventoryItem baru menggunakan data-data di atas.
        // 5. Panggil metode addItem() dari objek 'db' dan masukkan objek item tersebut.
        // 6. Panggil metode refreshTable() agar data baru muncul di tabel.
        // ==============================================================================

        // --- TULIS KODE ANDA DI BAWAH INI ---


    }

    @FXML
    private void handleDelete() {
        // ==============================================================================
        // TODO 3: LOGIKA HAPUS DATA
        // ==============================================================================
        // 1. Ambil item yang sedang dipilih oleh user di tableInventory.
        // 2. Cek jika item tersebut ada (tidak null):
        //    a. (Opsional/Nilai Plus) Tampilkan Alert konfirmasi penghapusan.
        //    b. Panggil db.deleteItem() dengan parameter nama item tersebut.
        //    c. Jika berhasil terhapus dari database, hapus juga dari 'masterData'.
        //    d. Panggil clearFields().
        // 3. Jika null (user belum memilih baris), tampilkan Alert bertipe WARNING
        //    yang meminta user memilih item terlebih dahulu.
        // ==============================================================================

        // --- TULIS KODE ANDA DI BAWAH INI ---


    }

    @FXML
    private void clearFields() {
        txtItem.clear();
        txtAcquired.clear();
        txtUsed.clear(); // Pastikan input used juga dibersihkan
        txtItem.setDisable(false);
        txtUsed.setDisable(false);
        selectedItem = null;
    }

    @FXML
    private void refreshTable() {
        masterData.setAll(db.getAllItems());
        tableInventory.setItems(masterData);
    }
}