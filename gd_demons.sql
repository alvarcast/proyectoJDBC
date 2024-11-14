-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2024 a las 15:35:36
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gd_demons`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `beaten_level`
--

CREATE TABLE `beaten_level` (
  `id` int(11) NOT NULL,
  `music_rate` float NOT NULL,
  `gameplay_rate` float NOT NULL,
  `deco_rate` float NOT NULL,
  `fx_rate` float NOT NULL,
  `enjoyment` float NOT NULL,
  `total_attempts` int(11) NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fav_levels`
--

CREATE TABLE `fav_levels` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `lid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `level`
--

CREATE TABLE `level` (
  `id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `creator` varchar(20) NOT NULL,
  `difficulty` varchar(13) NOT NULL,
  `diff_num` float NOT NULL,
  `attempts` int(11) NOT NULL,
  `beaten` tinyint(1) NOT NULL,
  `start_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `level_beaten_level`
--

CREATE TABLE `level_beaten_level` (
  `lid` int(11) NOT NULL,
  `blid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal_info`
--

CREATE TABLE `personal_info` (
  `uid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `rec_email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `personal_info`
--

INSERT INTO `personal_info` (`uid`, `name`, `surname`, `email`, `rec_email`) VALUES
(5, 'Alvaro', 'Aguado', 'alvaroaguado05@gmail.com', 'alvaroaguado05@outlook.es'),
(6, 'Joe', 'Escobedo', 'joe@gmail.com', 'joe2@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(5, 'alvarcast', '1234'),
(6, 'Xx_Joe-Biden69_xX', '1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_level`
--

CREATE TABLE `user_level` (
  `uid` int(11) NOT NULL,
  `lid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `beaten_level`
--
ALTER TABLE `beaten_level`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `fav_levels`
--
ALTER TABLE `fav_levels`
  ADD PRIMARY KEY (`id`),
  ADD KEY `uid` (`uid`),
  ADD KEY `lid` (`lid`);

--
-- Indices de la tabla `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `level_beaten_level`
--
ALTER TABLE `level_beaten_level`
  ADD KEY `lid` (`lid`),
  ADD KEY `blid` (`blid`);

--
-- Indices de la tabla `personal_info`
--
ALTER TABLE `personal_info`
  ADD KEY `uid` (`uid`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user_level`
--
ALTER TABLE `user_level`
  ADD KEY `uid` (`uid`),
  ADD KEY `lid` (`lid`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `beaten_level`
--
ALTER TABLE `beaten_level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `fav_levels`
--
ALTER TABLE `fav_levels`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `level`
--
ALTER TABLE `level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `fav_levels`
--
ALTER TABLE `fav_levels`
  ADD CONSTRAINT `fav_levels_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fav_levels_ibfk_2` FOREIGN KEY (`lid`) REFERENCES `level` (`id`);

--
-- Filtros para la tabla `level_beaten_level`
--
ALTER TABLE `level_beaten_level`
  ADD CONSTRAINT `level_beaten_level_ibfk_1` FOREIGN KEY (`lid`) REFERENCES `level` (`id`),
  ADD CONSTRAINT `level_beaten_level_ibfk_2` FOREIGN KEY (`blid`) REFERENCES `beaten_level` (`id`);

--
-- Filtros para la tabla `personal_info`
--
ALTER TABLE `personal_info`
  ADD CONSTRAINT `personal_info_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `user_level`
--
ALTER TABLE `user_level`
  ADD CONSTRAINT `user_level_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `user_level_ibfk_2` FOREIGN KEY (`lid`) REFERENCES `level` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
