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
- Java 8 (JDK 1.8) atau lebih baru
- MongoDB 4.4+ (Atlas atau Community Edition)
- Maven 3.3+

## **Instalasi**
1. Clone repositori:
   ```bash
   git clone https://github.com/username/simulasi-mongodb-migration.git
   cd simulasi-mongodb-migration
   ```

2. Build aplikasi (pastikan menggunakan Java 8):
   ```bash
   mvn clean install -Djava.version=1.8
   ```

3. Konfigurasi koneksi MongoDB di `mongodb_config.properties`

## **Menjalankan Aplikasi**
```bash
java -jar target/simulasi.jar
```

## **Struktur Log**
Aplikasi akan membuat folder `logs` otomatis dan menyimpan log dalam format berikut:
- **Client Logs**: `client_a.log`, `client_b.log`
- **Monitoring**: `ping.log`, `application.log`
- **Komparasi**: `compare.log`
- **Verifikasi**: `verval.log`
- **Visual Query**: `visualquery.log`

## **Troubleshooting**
- **Log tidak terbentuk**: Pastikan folder `logs` memiliki hak akses write
- **Koneksi gagal**: Verifikasi konfigurasi di `mongodb_config.properties`
- **Error build**: Update dependencies dengan `mvn clean install -U`

**Develop By Arvino**  
*Januari s.d Februari 2025*  
**"Empowering MongoDB Migration Excellence"**