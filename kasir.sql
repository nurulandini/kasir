-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 04 Jun 2019 pada 05.45
-- Versi Server: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kode_barang` int(11) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `jenis_barang` varchar(20) NOT NULL,
  `harga_barang` int(10) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `tanggal_exp` date NOT NULL,
  `kode_dist` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `jenis_barang`, `harga_barang`, `jumlah_barang`, `tanggal_masuk`, `tanggal_exp`, `kode_dist`) VALUES
(1, 'Mommy Poko', 'Perlengkapan', 56000, 98, '2017-05-02', '2018-03-01', 1),
(2, 'Lays', 'Makanan', 30000, 0, '2019-04-08', '2020-05-31', 2),
(3, 'Sari Roti', 'Makanan', 5000, 48, '2019-02-03', '2019-05-03', 3),
(4, 'Frisian Flag Cokelat', 'Minuman', 4600, 116, '2019-06-03', '2004-01-01', 4),
(5, 'Sprite', 'Minuman', 5000, 116, '2019-05-12', '2020-03-05', 5);

-- --------------------------------------------------------

--
-- Struktur dari tabel `distributor`
--

CREATE TABLE `distributor` (
  `kode_dist` int(20) NOT NULL,
  `nama_distributor` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `distributor`
--

INSERT INTO `distributor` (`kode_dist`, `nama_distributor`, `alamat`) VALUES
(1, 'PT. Jaya Indah ', 'Medan'),
(2, 'PT. Maju Jaya', 'Jakarta'),
(3, 'PT. Indah Lestari', 'Bandung'),
(4, 'PT. Medan Jaya', 'Medan'),
(5, 'PT. Kita Bisa', 'Deli Serdang');

-- --------------------------------------------------------

--
-- Stand-in structure for view `expired`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `expired` (
`kode_barang` int(11)
,`nama_barang` varchar(30)
,`tanggal_exp` date
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id` int(7) NOT NULL,
  `nama_lengkap` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id`, `nama_lengkap`, `Password`) VALUES
(1, 'Tria Riskiani', 'tria12345'),
(2, 'Nurul Andini', 'nurul12345'),
(3, 'Fahmi Rizal', 'fahmi12345');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `kode_transaksi` int(15) NOT NULL,
  `tanggal` date NOT NULL,
  `id` int(30) NOT NULL,
  `total_transaksi` int(100) NOT NULL,
  `bayar` int(100) NOT NULL,
  `kembali` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`kode_transaksi`, `tanggal`, `id`, `total_transaksi`, `bayar`, `kembali`) VALUES
(1906040001, '2019-06-04', 2, 96000, 100000, 4000),
(1906040002, '2019-06-04', 2, 18400, 20000, 1600);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_detail`
--

CREATE TABLE `transaksi_detail` (
  `kode_transaksi` int(15) NOT NULL,
  `kode_barang` int(100) NOT NULL,
  `harga` int(100) NOT NULL,
  `total_barang` int(100) NOT NULL,
  `total` int(100) NOT NULL,
  `tanggal` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi_detail`
--

INSERT INTO `transaksi_detail` (`kode_transaksi`, `kode_barang`, `harga`, `total_barang`, `total`, `tanggal`) VALUES
(190604001, 1, 56000, 1, 56000, '2019-06-04 10:29:38'),
(1906040001, 1, 56000, 1, 56000, '2019-06-04 10:33:58'),
(1906040001, 2, 30000, 1, 30000, '2019-06-04 10:34:04'),
(1906040001, 3, 5000, 2, 10000, '2019-06-04 10:34:12'),
(1906040002, 4, 4600, 4, 18400, '2019-06-04 10:44:04');

-- --------------------------------------------------------

--
-- Struktur untuk view `expired`
--
DROP TABLE IF EXISTS `expired`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `expired`  AS  select `barang`.`kode_barang` AS `kode_barang`,`barang`.`nama_barang` AS `nama_barang`,`barang`.`tanggal_exp` AS `tanggal_exp` from `barang` where (`barang`.`tanggal_exp` <= now()) order by `barang`.`tanggal_exp` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`),
  ADD KEY `kode_dist` (`kode_dist`);

--
-- Indexes for table `distributor`
--
ALTER TABLE `distributor`
  ADD PRIMARY KEY (`kode_dist`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD KEY `kode_barang` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `kode_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `distributor`
--
ALTER TABLE `distributor`
  MODIFY `kode_dist` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`kode_dist`) REFERENCES `distributor` (`kode_dist`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id`) REFERENCES `barang` (`kode_barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
