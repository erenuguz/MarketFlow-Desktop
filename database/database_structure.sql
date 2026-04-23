-- Market Otomasyonu - Boş Veritabanı Şeması
-- Üretim Zamanı: 23 Nis 2026

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- --------------------------------------------------------
-- Tablo yapısı: `urunler`
-- --------------------------------------------------------

CREATE TABLE `urunler` (
  `id` int(11) NOT NULL,
  `urun_adi` varchar(100) DEFAULT NULL,
  `fiyat` double DEFAULT NULL,
  `stok_miktari` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tablo yapısı: `satislar`
-- --------------------------------------------------------

CREATE TABLE `satislar` (
  `id` int(11) NOT NULL,
  `tarih` datetime DEFAULT current_timestamp(),
  `toplam_tutar` double DEFAULT NULL,
  `odeme_turu` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Tablo yapısı: `satis_detaylari`
-- --------------------------------------------------------

CREATE TABLE `satis_detaylari` (
  `id` int(11) NOT NULL,
  `satis_id` int(11) DEFAULT NULL,
  `urun_adi` text DEFAULT NULL,
  `adet` int(11) DEFAULT NULL,
  `fiyat` double DEFAULT NULL,
  `satir_toplam` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- İndeksler ve Otomatik Artırma (AUTO_INCREMENT)
-- --------------------------------------------------------

ALTER TABLE `satislar`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `satis_detaylari`
  ADD PRIMARY KEY (`id`),
  ADD KEY `satis_id` (`satis_id`);

ALTER TABLE `urunler`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `satislar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `satis_detaylari`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
-- Yabancı Anahtar Kısıtlamaları (İlişkiler)
-- --------------------------------------------------------

ALTER TABLE `satis_detaylari`
  ADD CONSTRAINT `satis_detaylari_ibfk_1` FOREIGN KEY (`satis_id`) REFERENCES `satislar` (`id`) ON DELETE CASCADE;

COMMIT;