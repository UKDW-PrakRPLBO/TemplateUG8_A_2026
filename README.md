# TemplateUG8_A_2026

Lengkapilah program daftar catatan sederhana ini agar memiliki fitur-fitur berikut:

## UI dan Alur Program
- Aplikasi terdiri dari 2 UI yang berbeda, yaitu **Form Login** dan **Form Daftar Catatan**.
  ![Form Login](/img/LoginForm.PNG)    
  ![Form Utama](/img/MainForm.PNG)  

- **Autentikasi Login:** Untuk dapat mengakses form daftar catatan, pengguna harus melakukan login terlebih dahulu menggunakan kredensial *hardcode* dengan username: **admin** dan password: **admin**.

- **Insert Data Catatan:** User harus menginputkan Judul dan Isi, kemudian menekan tombol simpan. Jika proses simpan berhasil, maka program akan menampilkan pesan *"Catatan Ditambahkan!"* dalam bentuk *dialog box*.  

- **Update Data Catatan:** Untuk mengubah data catatan, user terlebih dahulu memilih catatan yang ingin diubah dengan mengeklik salah satu item pada tabel catatan. Data catatan yang dipilih akan ditampilkan pada form input, sehingga user dapat melakukan perubahan. Tombol simpan digunakan untuk melakukan penyimpanan data terbaru ke sistem. Pesan *"Catatan Diperbarui!"* dalam bentuk *dialog box* akan ditampilkan jika data berhasil diperbarui.  

- **Delete Data Catatan:** Untuk menghapus data catatan, user terlebih dahulu memilih catatan yang ingin dihapus dengan mengeklik salah satu item pada tabel catatan. Data catatan yang dipilih akan ditampilkan pada form input, dan user dapat menekan tombol hapus untuk menghapus data dari sistem. Pesan *"Catatan Dihapus!"* dalam bentuk *dialog box* akan ditampilkan jika data berhasil dihapus.  

- **Database:** Seluruh data catatan aplikasi harus disimpan di dalam **SQLite**.
