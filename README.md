# MarketFlow POS 🚀

**MarketFlow** is a modern, high-performance retail management solution. / **MarketFlow**, modern ve yüksek performanslı bir perakende yönetim çözümüdür.

---

## 🌍 Language / Dil
[English](#english) | [Turkish (Türkçe)](#turkish)

---

<a name="english"></a>
## 🇺🇸 English Documentation

### 🚀 Key Features
- **Dynamic Sales Menu:** Tabbed interface for fast product selection.
- **Dual Payment:** Supports Cash and POS (Card) with automatic change calculation.
- **Stock Management:** Automatic inventory updates via SQL Batch processing.
- **Reporting:** Daily statistics (Revenue, Payment Methods, Transaction Count).

### 🛠 Tech Stack
- **Java (JDK 8+), Swing, MySQL, JDBC.**

### 🔧 Installation
1. **Clone:** `git clone https://github.com/erenuguz/MarketFlow-POS.git`
2. **Database:** Import `database/database_structure.sql` into your local MySQL.
3. **Config:** Update `src/DBConnection.java` with your DB credentials.
4. **Library:** Add `mysql-connector-j` (in `/lib`) to your Build Path.

---

<a name="turkish"></a>
## 🇹🇷 Türkçe Dokümantasyon

### 🚀 Öne Çıkan Özellikler
- **Dinamik Satış Menüsü:** Hızlı ürün seçimi için sekmeli arayüz.
- **Çift Ödeme Desteği:** Nakit ve POS ödemeleri, otomatik para üstü hesaplama.
- **Stok Yönetimi:** SQL Batch işlemi ile otomatik stok güncelleme.
- **Raporlama:** Günlük istatistikler (Ciro, Ödeme Yöntemleri, İşlem Sayısı).

### 🛠 Kullanılan Teknolojiler
- **Java (JDK 8+), Swing, MySQL, JDBC.**

### 🔧 Kurulum
1. **Klonlayın:** `git clone https://github.com/erenuguz/MarketFlow-POS.git`
2. **Veritabanı:** `/database/database_structure.sql` dosyasını MySQL'e içe aktarın.
3. **Yapılandırma:** `src/DBConnection.java` dosyasını kendi veritabanı bilgilerinizle güncelleyin.
4. **Kütüphane:** `/lib` klasöründeki MySQL sürücüsünü projenize dahil edin.

---

## 📂 Project Structure / Proje Yapısı

- `src/` : Source Code / Kaynak Kodlar
- `lib/` : External Libraries / Harici Kütüphaneler
- `database/` : SQL Schema / Veritabanı Yapısı
- `.gitignore` : Excluded Files / Git Dışı Dosyalar

## 📄 License / Lisans
MIT License
