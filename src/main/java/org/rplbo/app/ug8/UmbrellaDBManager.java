package org.rplbo.app.ug8;

import java.sql.*;
import java.util.ArrayList;

/**
 * Kelas Manajer Database untuk Terminal Umbrella Corporation.
 * Menangani pembuatan tabel otomatis, autentikasi agen, dan operasi CRUD Inventaris.
 */
public class UmbrellaDBManager {

    // Konstanta URL koneksi SQLite. Ubah nama file .db di sini jika diperlukan.
    private static final String URL = "jdbc:sqlite:umbrella_inventory.db";

    /**
     * Constructor: Dipanggil pertama kali saat aplikasi berjalan.
     * Bertugas memastikan file database dan tabel sudah tercipta, serta mengisi data agen awal.
     */
    public UmbrellaDBManager() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            // 1. Pembuatan Tabel Users
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id_users INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nama_lengkap TEXT, " +
                    "no_id_card TEXT, " +
                    "username TEXT UNIQUE, " +
                    "password TEXT)");

            // 2. Pembuatan Tabel Inventory
            stmt.execute("CREATE TABLE IF NOT EXISTS inventory (" +
                    "item_name TEXT PRIMARY KEY, " +
                    "acquired INTEGER, " +
                    "used INTEGER, " +
                    "total_stock INTEGER)");

            // 3. Injeksi Data Agen (Hanya dieksekusi jika tabel users masih kosong)
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.execute("INSERT INTO users (nama_lengkap, no_id_card, username, password) " +
                        "VALUES ('Leon S. Kennedy', 'RPD-99812', 'leon', '123')");
            }

        } catch (SQLException e) {
            System.err.println("Database Initialization Error: " + e.getMessage());
        }
    }

    /**
     * Memvalidasi kredensial login agen saat mengakses terminal.
     * Menggunakan PreparedStatement untuk mencegah serangan SQL Injection.
     */
    public String validateUser(String user, String pass) {
        String sql = "SELECT nama_lengkap FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nama_lengkap");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * CREATE: Menambahkan catatan item baru ke dalam database inventaris.
     */
    public void addItem(InventoryItem item) {
        String sql = "INSERT INTO inventory VALUES(?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getItemName());
            pstmt.setInt(2, item.getAcquired());
            pstmt.setInt(3, item.getUsed());
            pstmt.setInt(4, item.getTotalStock());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * READ: Mengambil seluruh data item dari tabel inventaris untuk ditampilkan di tabel JavaFX.
     */
    public ArrayList<InventoryItem> getAllItems() {
        ArrayList<InventoryItem> list = new ArrayList<>();
        String sql = "SELECT * FROM inventory";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new InventoryItem(
                        rs.getString("item_name"),
                        rs.getInt("acquired"),
                        rs.getInt("used"),
                        rs.getInt("total_stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * UPDATE: Memperbarui jumlah item (acquired, used, total_stock) berdasarkan nama item.
     */
    public boolean updateItem(InventoryItem item) {
        String sql = "UPDATE inventory SET acquired = ?, used = ?, total_stock = ? WHERE item_name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getAcquired());
            pstmt.setInt(2, item.getUsed());
            pstmt.setInt(3, item.getTotalStock());
            pstmt.setString(4, item.getItemName());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * DELETE: Menghapus catatan item dari database berdasarkan nama item.
     */
    public boolean deleteItem(String itemName) {
        String sql = "DELETE FROM inventory WHERE item_name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, itemName);

            // Mengembalikan true jika ada baris yang berhasil dihapus
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}