# Tools Simulasi Migrasi MongoDB Atlas

## **Fitur Utama**
- **Verifikasi & Validasi Migrasi**: Memastikan konsistensi data antara database sumber dan tujuan
- **Simulasi HA/DR**: Menguji skenario failover dan recovery pada MongoDB Atlas
- **Komparasi Data**: Membandingkan koleksi dan dokumen antar database
- **Visual Query Builder**: Membangun query MongoDB dengan antarmuka grafis
- **Logging Terpusat**: Menyimpan log proses di folder `logs/` dengan format:
  ```
  logs/
  ├── client_a.log
  ├── client_b.log
  ├── ping.log
  ├── application.log
  ├── compare.log
  ├── verval.log
  └── visualquery.log
  ```

## **Persyaratan Sistem**
- Java 21 (JDK 21) atau lebih baru
- MongoDB 4.4+ (Atlas atau Community Edition)
- Maven 3.9+

## **Instalasi**
1. Clone repositori:
   ```bash
   git clone https://github.com/username/simulasi-mongodb-migration.git
   cd simulasi-mongodb-migration
   ```

2. Build aplikasi:
   ```bash
   mvn clean install -Djava.version=21
   ```

3. Konfigurasi koneksi MongoDB di `mongodb_config.properties`

## **Menjalankan Aplikasi**
```bash
java -jar target/simulasi.jar
```

## **Struktur Log File**

Aplikasi akan membuat folder `logs` otomatis dan menyimpan log dalam format berikut:

```
logs/
├── client_a.log        # Log untuk SimulasiHADR_ClientA
├── client_b.log        # Log untuk SimulasiHADR_ClientB
├── ping.log            # Log untuk SimulasiHADR_Ping
├── application.log     # Log aplikasi utama
├── compare.log         # Log untuk modul Komparasi DB
├── verval.log          # Log untuk modul Verifikasi dan Validasi
└── visualquery.log      # Log untuk modul Visual Query
```

**Catatan:**
- Folder `logs` akan dibuat otomatis saat pertama kali aplikasi dijalankan
- Setiap modul memiliki file log terpisah
- Log disimpan dalam format tanggal, waktu, level, dan pesan

## **Troubleshooting**
- **Log tidak terbentuk**: Pastikan folder `logs` memiliki hak akses write
- **Koneksi gagal**: Verifikasi konfigurasi di `mongodb_config.properties`
- **Error build**: Update dependencies dengan `mvn clean install -U`

## **Struktur Kode dan Fungsi File**

Berikut adalah penjelasan fungsi dari setiap file utama dalam aplikasi:

### **1. ConfigLoader.java**
- Bertanggung jawab untuk memuat dan menyimpan konfigurasi dari file properties
- Menyediakan method untuk mengakses nilai konfigurasi berdasarkan key
- Membuat folder logs jika belum ada
- Mengelola konfigurasi untuk semua modul

### **2. MainMenu.java**
- Menampilkan menu utama aplikasi
- Mengatur navigasi ke modul-modul yang tersedia
- Menangani event klik tombol untuk membuka modul yang dipilih

### **Modul Komparasi DB**
#### **KomparasiDB_Screen1.java**
- Menampilkan form input konfigurasi komparasi database
- Menyimpan konfigurasi ke file properties
- Navigasi ke screen2 untuk memulai proses komparasi

#### **KomparasiDB_Screen2.java**
- Menampilkan log proses komparasi database
- Menangani proses komparasi secara real-time
- Menyediakan tombol untuk menghentikan proses

### **Modul Simulasi HADR**
#### **SimulasiHADR_Screen1.java**
- Menampilkan form konfigurasi simulasi HA/DR
- Menyimpan konfigurasi koneksi MongoDB
- Memulai simulasi dengan membuka screen2

#### **SimulasiHADR_Screen2.java**
- Menampilkan monitoring simulasi HA/DR
- Menampilkan log dari Client A, Client B, dan Ping
- Menyediakan kontrol untuk menghentikan simulasi

#### **SimulasiHADR_ClientA.java**
- Simulasi client dengan retryWrites=true
- Melakukan insert data ke MongoDB secara berkala
- Mencatat log proses ke file

#### **SimulasiHADR_ClientB.java**
- Simulasi client tanpa retryWrites
- Melakukan insert data ke MongoDB secara berkala
- Mencatat log proses ke file

#### **SimulasiHADR_Ping.java**
- Melakukan ping ke cluster MongoDB secara berkala
- Memantau ketersediaan dan response time
- Mencatat log hasil ping

### **Modul Verifikasi dan Validasi Migrasi**
#### **VerValMigrasi_Screen1.java**
- Menampilkan form konfigurasi verifikasi dan validasi
- Menyimpan konfigurasi ke file properties
- Memulai proses verifikasi dan validasi

#### **VerValMigrasi_Screen2.java**
- Menampilkan log proses verifikasi dan validasi
- Menangani proses verifikasi secara real-time
- Menyediakan tombol untuk menghentikan proses

#### **VerValMigrasi_Proses.java**
- Melakukan proses verifikasi dan validasi migrasi
- Membandingkan data antara database sumber dan tujuan
- Mencatat hasil verifikasi ke log file

### **Modul Visual Query**
#### **VisualQuery_Screen1.java**
- Menampilkan form konfigurasi koneksi MongoDB
- Menyimpan URI koneksi ke file properties
- Navigasi ke screen2 untuk membangun query

#### **VisualQuery_Screen2.java**
- Menyediakan antarmuka untuk membangun query MongoDB
- Menampilkan hasil query
- Menyediakan tombol untuk menjalankan query

#### **VisualQuery_Builder.java**
- Komponen untuk membangun query secara visual
- Menyediakan pilihan field, operator, dan value
- Menampilkan query yang telah dibangun

## **Konfigurasi Properties**

Setiap modul memiliki file konfigurasi properties terpisah di folder `config/`:

### **1. simulasihadr.properties**
```properties
# Konfigurasi Simulasi HADR
URI_CLIENTA=cluster0-shard-00-00.xxx.mongodb.net:27017
URI_CLIENTB=cluster0-shard-00-01.xxx.mongodb.net:27017
URI_PING=cluster0-shard-00-02.xxx.mongodb.net:27017
DB_NAME=simulasiDB
COLLECTION_NAME=simulasiCollection
INTERVAL_THREAD_SLEEP=500
```

### **2. komparasidb.properties**
```properties
# Konfigurasi Komparasi DB
URI_DATABASE1=cluster1-shard-00-00.xxx.mongodb.net:27017
URI_DATABASE2=cluster2-shard-00-00.xxx.mongodb.net:27017
DB_NAME1=database1
DB_NAME2=database2
COLLECTION_NAME=comparisonCollection
```

### **3. vervalmigrasi.properties**
```properties
# Konfigurasi Verifikasi dan Validasi Migrasi
URI_VERVAL_DATABASE1=source-cluster-shard-00-00.xxx.mongodb.net:27017
URI_VERVAL_DATABASE2=target-cluster-shard-00-00.xxx.mongodb.net:27017
VERVAL_DBNAME1=sourceDB
VERVAL_DBNAME2=targetDB
VERVAL_COLLECTION=migrationCollection
```

### **4. visualquery.properties**
```properties
# Konfigurasi Visual Query
URI_VISUALQUERY=cluster0-shard-00-00.xxx.mongodb.net:27017
DB_NAME=visualQueryDB
COLLECTION_NAME=queryCollection
```

**Catatan:**
- Ganti nilai contoh dengan konfigurasi MongoDB yang sesuai
- Pastikan folder `config` sudah dibuat di root direktori proyek
- File properties akan dibuat otomatis saat pertama kali menyimpan konfigurasi

**Develop By Arvino**  
*Januari s.d Februari 2025*  
**"Empowering MongoDB Migration Excellence"**