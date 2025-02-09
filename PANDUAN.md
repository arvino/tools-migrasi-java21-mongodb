# Tools Simulasi Migrasi MongoDB Atlas

## **Overview**
Tools Simulasi Migrasi MongoDB Atlas adalah solusi komprehensif untuk menguji dan memvalidasi proses migrasi database MongoDB, khususnya pada lingkungan MongoDB Atlas. Aplikasi ini dirancang untuk membantu developer dan database administrator dalam:

1. Memastikan integritas data pasca migrasi
2. Menguji skenario High Availability (HA) dan Disaster Recovery (DR)
3. Membandingkan struktur dan konten koleksi database
4. Membangun query MongoDB secara visual

**Key Features**:
✅ Verifikasi konsistensi data antar cluster  
✅ Simulasi failover otomatis dengan mekanisme retry  
✅ Komparasi dokumen real-time antar koleksi  
✅ Visual query builder dengan antarmuka intuitif  
✅ Monitoring koneksi dan performance cluster  

Dikembangkan khusus untuk memenuhi kebutuhan:
- Migrasi antar cluster MongoDB Atlas
- Validasi replikasi data cross-region
- Pengujian skenario disaster recovery
- Optimasi query database

**Teknologi Utama**:
- Java Swing untuk antarmuka grafis
- MongoDB Java Driver 4.4+
- Maven Build System
- Logback untuk sistem logging terpusat

Tools ini telah terintegrasi dengan fitur-fitur unggulan MongoDB Atlas seperti:
- Automatic Retryable Writes
- Multi-Document ACID Transactions
- Atlas Search (Opsional)
- Real-time Performance Metrics

**Develop By Arvino**  
*Januari s.d Februari 2025*  
**"Empowering MongoDB Migration Excellence"**

## **Menu Utama**
1. **Verifikasi dan Validasi Migrasi MongoDB Atlas**  
   - Memverifikasi konsistensi data antara database sumber dan tujuan setelah migrasi
2. **Simulasi HA dan DR MongoDB Atlas**  
   - Mensimulasikan skenario High Availability dan Disaster Recovery
3. **Komparasi Collection dan Document MongoDB**  
   - Membandingkan struktur dan isi koleksi antara dua database
4. **Visual Query Builder MongoDB Atlas**  
   - Membangun query MongoDB secara visual dengan antarmuka GUI

---

## **Persyaratan Software**
Sebelum menjalankan aplikasi, pastikan sistem Anda memenuhi persyaratan berikut:

### **1. Java Development Kit (JDK)**
- Versi minimal: **JDK 8 (1.8)**
- Versi yang direkomendasikan: **Java SE 8u401**
- Unduh JDK dari [situs resmi Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) atau [OpenJDK](https://openjdk.org/).

### **2. Apache Maven**
- Versi minimal: **Maven 3.3.1** atau lebih baru.
- Unduh Maven dari [situs resmi Apache Maven](https://maven.apache.org/download.cgi).

### **3. MongoDB**
- Versi minimal: **MongoDB 4.4** atau lebih baru.
- Unduh MongoDB dari [situs resmi MongoDB](https://www.mongodb.com/try/download/community).

### **4. MongoDB Atlas (Opsional)**
- Jika menggunakan MongoDB Atlas, pastikan Anda memiliki akun dan cluster yang aktif.
- Dapatkan URI koneksi dari dashboard MongoDB Atlas.

---

## **Instalasi dan Konfigurasi**

### **1. Instalasi JDK**
#### **Windows**
1. Unduh installer JDK dari situs resmi.
2. Jalankan installer dan ikuti petunjuk instalasi.
3. Setelah instalasi selesai, tambahkan JDK ke environment variable:
   - Buka **System Properties** > **Environment Variables**.
   - Tambahkan path JDK ke variabel `JAVA_HOME` (misalnya: `C:\Program Files\Java\jdk-17`).
   - Tambahkan `%JAVA_HOME%\bin` ke variabel `Path`.

#### **macOS**
1. Unduh installer JDK dari situs resmi.
2. Jalankan installer dan ikuti petunjuk instalasi.
3. Setelah instalasi selesai, buka terminal dan jalankan perintah berikut untuk memverifikasi instalasi:
   ```bash
   java -version
   ```

---

### **2. Instalasi Maven**
#### **Windows**
1. Unduh Maven dari situs resmi.
2. Ekstrak file zip ke direktori yang diinginkan (misalnya: `C:\Program Files\apache-maven-3.8.6`).
3. Tambahkan Maven ke environment variable:
   - Buka **System Properties** > **Environment Variables**.
   - Tambahkan path Maven ke variabel `MAVEN_HOME` (misalnya: `C:\Program Files\apache-maven-3.8.6`).
   - Tambahkan `%MAVEN_HOME%\bin` ke variabel `Path`.

#### **macOS**
1. Unduh Maven dari situs resmi.
2. Ekstrak file zip ke direktori yang diinginkan (misalnya: `/usr/local/apache-maven-3.8.6`).
3. Tambahkan Maven ke environment variable:
   - Buka terminal dan edit file `~/.bash_profile` atau `~/.zshrc`:
     ```bash
     export MAVEN_HOME=/usr/local/apache-maven-3.8.6
     export PATH=$MAVEN_HOME/bin:$PATH
     ```
   - Jalankan perintah berikut untuk memverifikasi instalasi:
     ```bash
     mvn -v
     ```

---

### **3. Instalasi MongoDB**
#### **Windows**
1. Unduh installer MongoDB dari situs resmi.
2. Jalankan installer dan ikuti petunjuk instalasi.
3. Setelah instalasi selesai, jalankan MongoDB sebagai service atau secara manual.

#### **macOS**
1. Unduh MongoDB dari situs resmi.
2. Ekstrak file zip ke direktori yang diinginkan (misalnya: `/usr/local/mongodb`).
3. Tambahkan MongoDB ke environment variable:
   - Buka terminal dan edit file `~/.bash_profile` atau `~/.zshrc`:
     ```bash
     export PATH=/usr/local/mongodb/bin:$PATH
     ```
   - Jalankan perintah berikut untuk memverifikasi instalasi:
     ```bash
     mongod --version
     ```

---

## **Menjalankan Aplikasi**

### **1. Clone Repository**
Clone repository proyek ke direktori lokal Anda:
```bash
git clone https://github.com/username/repository-name.git
cd repository-name
```

### **2. Build Proyek**
Jalankan perintah berikut untuk membangun proyek:
```bash
mvn clean install
```

### **3. Menjalankan Aplikasi**
#### **Windows**
1. Buka Command Prompt atau PowerShell.
2. Jalankan aplikasi dengan perintah berikut:
   ```bash
   java -jar target/simulasi.jar
   ```

#### **macOS**
1. Buka Terminal.
2. Jalankan aplikasi dengan perintah berikut:
   ```bash
   java -jar target/simulasi.jar
   ```

---

## **Konfigurasi Aplikasi**
1. **Konfigurasi MongoDB**:
   - Buka file `mongodb_config.properties` di direktori proyek.
   - Sesuaikan nilai `USERNAME`, `PASSWORD`, `DB_NAME`, dan `COLLECTION_NAME` sesuai dengan konfigurasi MongoDB Anda.

2. **Konfigurasi Logging**:
   - **Verifikasi & Validasi**: `verval.log`
   - **Visual Query**: `visualquery.log`

---

## **Troubleshooting**
1. **Error "no main manifest attribute"**:
   - Pastikan file `MANIFEST.MF` di dalam file JAR memiliki atribut `Main-Class` yang benar.
   - Verifikasi konfigurasi di `pom.xml` dan build ulang proyek.

2. **Error koneksi MongoDB**:
   - Pastikan MongoDB sedang berjalan.
   - Verifikasi URI koneksi di file `mongodb_config.properties`.

3. **Error Maven build**:
   - Pastikan Maven dan JDK sudah terinstal dengan benar.
   - Jalankan perintah `mvn clean install` untuk membersihkan dan membangun ulang proyek.

---

## **Catatan Penting**
- Pastikan semua persyaratan software sudah terinstal sebelum menjalankan aplikasi.
- Jika menggunakan MongoDB Atlas, pastikan URI koneksi sudah benar dan cluster sedang aktif.

---

## **Modul-modul Aplikasi**

### **1. Modul Verifikasi dan Validasi Migrasi MongoDB**

#### **1.1. Fitur Utama**
- **Verifikasi Jumlah Dokumen**: Memeriksa apakah jumlah dokumen di database sumber dan tujuan sama.
- **Validasi Konsistensi Data**: Memastikan data di database sumber dan tujuan konsisten.
- **Logging**: Menyimpan log verifikasi dan validasi di file `verval.log`.

#### **1.2. Cara Menggunakan**
1. Buka aplikasi dan pilih **Simulasi Verifikasi dan Validasi Migrasi MongoDB**.
2. Isi form konfigurasi koneksi untuk database sumber dan tujuan.
3. Klik **Simpan Konfigurasi** untuk menyimpan data ke `mongodb_config.properties`.
4. Klik **Running Verifikasi dan Validasi** untuk memulai proses.
5. Pantau log verifikasi dan validasi di layar berikutnya.

#### **1.3. Troubleshooting**
- **Error Koneksi**: Pastikan URI koneksi benar dan database sedang berjalan.
- **Data Tidak Konsisten**: Periksa proses migrasi dan pastikan tidak ada data yang hilang atau berubah.

### **2. Modul Simulasi HA/DR MongoDB**
- **SimulasiHADR_ClientA**: Client yang melakukan insert data ke MongoDB dengan retryWrites=true.
- **SimulasiHADR_ClientB**: Client yang melakukan insert data ke MongoDB tanpa retryWrites.
- **SimulasiHADR_Ping**: Script untuk memantau koneksi dan response time ke cluster MongoDB.
- **SimulasiHADR_Screen1**: GUI untuk konfigurasi simulasi HA/DR.
- **SimulasiHADR_Screen2**: GUI untuk monitoring simulasi HA/DR.

### **3. Modul Komparasi MongoDB**
- **KomparasiDB_Screen1**: GUI untuk konfigurasi komparasi MongoDB.
- **KomparasiDB_Screen2**: GUI untuk monitoring dan menampilkan log komparasi MongoDB.

### **4. Modul Visual Query Builder MongoDB Atlas**

#### **4.1. Fitur Utama**
- **Visual Query Builder**: Membangun query MongoDB secara visual dengan antarmuka yang mudah digunakan.
- **Konfigurasi Koneksi**: Menyimpan URI koneksi MongoDB untuk query.
- **Eksekusi Query**: Menjalankan query dan menampilkan hasilnya.

#### **4.2. Cara Menggunakan**
1. Buka aplikasi dan pilih **Visual Query Builder MongoDB Atlas**.
2. Isi form konfigurasi koneksi MongoDB.
3. Klik **Simpan URI** untuk menyimpan konfigurasi.
4. Klik **Running Verifikasi dan Validasi** untuk membuka layar query builder.
5. Gunakan antarmuka visual untuk membangun query MongoDB.
6. Klik **Run Query** untuk menjalankan query dan melihat hasilnya.

#### **4.3. Troubleshooting**
- **Error Koneksi**: Pastikan URI koneksi benar dan database sedang berjalan.
- **Query Gagal**: Periksa sintaks query dan pastikan field yang digunakan ada di koleksi.

---

**Develop By Arvino * Jan s.d Feb 2025 * Selamat Mencoba !** 🚀