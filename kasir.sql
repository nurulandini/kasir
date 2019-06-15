-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2019 at 04:44 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

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
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode_barang` int(11) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `nama_kategori` text NOT NULL,
  `harga_barang` int(10) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `tanggal_exp` date NOT NULL,
  `nama_dist` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `nama_kategori`, `harga_barang`, `jumlah_barang`, `tanggal_masuk`, `tanggal_exp`, `nama_dist`) VALUES
(1, 'Mommy Poko', 'Popok', 50000, 93, '2017-05-03', '2018-03-01', 'PT.Kerta Jaya'),
(2, 'Lays', 'Makanan', 30000, -3, '2019-04-04', '2020-05-31', 'PT. Kita Bisa'),
(3, 'Sari Roti', 'Makanan', 5000, 48, '2019-02-03', '2019-05-03', 'PT.Indah Lestari'),
(4, 'Frisian Flag Cokelat', 'Makanan', 4600, 116, '2019-06-03', '2004-01-01', 'PT.Medan Jaya'),
(5, 'Sprite', 'Minuman', 5000, 116, '2019-05-12', '2020-03-05', 'PT.Kita Bisa'),
(6, 'Coca-cola', 'Minuman', 50000, 5, '2018-06-06', '2010-10-04', 'PT. Jaya Indah '),
(7, 'ali', 'Makanan', 300, 40, '2019-06-14', '2019-06-15', 'PT. Jaya Indah ');

-- --------------------------------------------------------

--
-- Table structure for table `distributor`
--

CREATE TABLE `distributor` (
  `kode_dist` int(20) NOT NULL,
  `nama_distributor` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `distributor`
--

INSERT INTO `distributor` (`kode_dist`, `nama_distributor`, `alamat`) VALUES
(1, 'PT. Jaya Indah ', 'Medan'),
(2, 'PT. Maju Jaya', 'Jakarta'),
(3, 'PT. Indah Lestari', 'Bandung'),
(4, 'PT. Medan Jaya', 'Medan'),
(5, 'PT. Kita Bisa', 'Deli Serdang'),
(6, 'PT.Kerta Jaya', 'Aceh');

-- --------------------------------------------------------

--
-- Stand-in structure for view `expired`
-- (See below for the actual view)
--
CREATE TABLE `expired` (
`kode_barang` int(11)
,`nama_barang` varchar(30)
,`tanggal_exp` date
);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`) VALUES
(1, 'Makanan'),
(2, 'Minuman'),
(3, 'Popok'),
(4, 'Barang'),
(5, 'Susu');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `id` int(7) NOT NULL,
  `nama_lengkap` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id`, `nama_lengkap`, `Password`) VALUES
(1, 'Tria Riskiani', 'tria12345'),
(2, 'Nurul Andini', 'nurul12345'),
(3, 'Fahmi Rizal', 'fahmi12345');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
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
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`kode_transaksi`, `tanggal`, `id`, `total_transaksi`, `bayar`, `kembali`) VALUES
(1906040001, '2019-06-04', 2, 96000, 100000, 4000),
(1906040002, '2019-06-04', 2, 18400, 20000, 1600),
(1906060001, '2019-06-05', 3, 168002, 100000, -68002);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_detail`
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
-- Dumping data for table `transaksi_detail`
--

INSERT INTO `transaksi_detail` (`kode_transaksi`, `kode_barang`, `harga`, `total_barang`, `total`, `tanggal`) VALUES
(190604001, 1, 56000, 1, 56000, '2019-06-04 10:29:38'),
(1906040001, 1, 56000, 1, 56000, '2019-06-04 10:33:58'),
(1906040001, 2, 30000, 1, 30000, '2019-06-04 10:34:04'),
(1906040001, 3, 5000, 2, 10000, '2019-06-04 10:34:12'),
(1906040002, 4, 4600, 4, 18400, '2019-06-04 10:44:04'),
(1906060001, 1, 56000, 2, 2, '2019-06-06 11:09:12'),
(1906060001, 1, 56000, 3, 168000, '2019-06-06 11:09:47');

-- --------------------------------------------------------

--
-- Structure for view `expired`
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
  ADD KEY `kode_dist` (`nama_dist`);

--
-- Indexes for table `distributor`
--
ALTER TABLE `distributor`
  ADD PRIMARY KEY (`kode_dist`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

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
  MODIFY `kode_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `distributor`
--
ALTER TABLE `distributor`
  MODIFY `kode_dist` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id`) REFERENCES `barang` (`kode_barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
