-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-05-2026 a las 22:46:47
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `restaurante_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alimento`
--

CREATE TABLE `alimento` (
  `id` int(11) NOT NULL,
  `id_receta` int(11) DEFAULT NULL,
  `precio` double NOT NULL,
  `tipo` varchar(31) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `alimento`
--

INSERT INTO `alimento` (`id`, `id_receta`, `precio`, `tipo`, `nombre`) VALUES
(13, 13, 10000, 'Alimento', 'Empanadas x12 '),
(14, 14, 15000, 'Alimento', 'Locro'),
(15, 15, 16000, 'Alimento', 'Milanesa'),
(16, 16, 9000, 'Alimento', 'Choripan'),
(17, 17, 12000, 'Alimento', 'Matambre a la pizza'),
(18, 18, 8000, 'Alimento', 'Pastel de papa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chef`
--

CREATE TABLE `chef` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `chef`
--

INSERT INTO `chef` (`id`) VALUES
(3),
(4),
(5),
(6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `salario` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `salario`) VALUES
(3, 0),
(4, 0),
(5, 0),
(6, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gerente`
--

CREATE TABLE `gerente` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gerente`
--

INSERT INTO `gerente` (`id`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingrediente_stock`
--

CREATE TABLE `ingrediente_stock` (
  `cantidad_stock` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ingrediente_stock`
--

INSERT INTO `ingrediente_stock` (`cantidad_stock`, `id`, `descripcion`) VALUES
(100, 1, 'Carne picada (Molida)'),
(50, 2, 'Cebolla'),
(30, 3, 'Aceitunas'),
(20, 4, 'Costillar de vaca'),
(100, 5, 'Sal gruesa'),
(40, 6, 'Nalga para milanesa'),
(30, 7, 'Queso Mozzarella'),
(60, 8, 'Tomate fresco'),
(40, 9, 'Maíz blanco'),
(50, 10, 'Chorizo y embutidos'),
(50, 11, 'Pan francés'),
(20, 12, 'Chimichurri'),
(60, 13, 'Huevos'),
(25, 14, 'Bife de chorizo'),
(80, 15, 'Papas'),
(30, 16, 'Queso Provolone'),
(20, 17, 'Leche'),
(15, 18, 'Dulce de Leche'),
(0, 19, 'Carne Vacuna'),
(0, 20, 'Chorizo\\'),
(0, 21, 'Carbon'),
(0, 22, 'Discos de masa'),
(0, 23, 'huevo'),
(0, 24, 'Condimentos'),
(0, 25, 'Porotos'),
(0, 26, 'zapallos'),
(0, 27, 'panceta'),
(0, 28, 'pan rallado'),
(0, 29, 'Pan Frances'),
(0, 30, 'Chimichurri'),
(0, 31, 'Matambre vacuno'),
(0, 32, 'Oregano'),
(0, 33, 'Ajo'),
(0, 34, 'aceite');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `tipo_persona` varchar(31) NOT NULL,
  `cedula` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `tipo_persona`, `cedula`, `correo`, `nombre`, `telefono`) VALUES
(1, 'Gerente', NULL, NULL, 'Lionel Messi', NULL),
(3, 'Chef', NULL, NULL, 'Doña Petrona', NULL),
(4, 'Chef', NULL, NULL, 'Germán Martitegui', NULL),
(5, 'Chef', NULL, NULL, 'Martinez Quarta', NULL),
(6, 'Chef', NULL, NULL, 'Messi', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta`
--

CREATE TABLE `receta` (
  `id` int(11) NOT NULL,
  `id_chef` int(11) DEFAULT NULL,
  `descripcion_proceso` text DEFAULT NULL,
  `nombre_receta` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `receta`
--

INSERT INTO `receta` (`id`, `id_chef`, `descripcion_proceso`, `nombre_receta`) VALUES
(13, 3, 'Las empanadas argentinas son masas rellenas que pueden prepararse al horno o fritas. Existen muchas variantes regionales, pero las más populares llevan carne picada condimentada con cebolla, huevo duro y aceitunas. Son comunes en reuniones familiares y celebraciones.\r\n\r\nLa receta consiste en cocinar el relleno previamente y luego colocarlo dentro de discos de masa. Después se cierran haciendo el característico “repulgue” y se hornean hasta dorarse. Algunas provincias agregan papa, pasas de uva o picante según la tradición local.', 'Empanadas x12 '),
(14, 6, 'Se cocina durante varias horas para que los ingredientes se integren y el caldo tome una textura espesa. Suele servirse caliente con una salsa picante llamada “quiquirimichi”, preparada con aceite, ají molido y cebolla de verdeo.', 'Locro'),
(15, 4, 'La milanesa a la napolitana es una adaptación argentina de la milanesa clásica. Consiste en una feta de carne empanada y frita, cubierta con salsa de tomate, jamón y queso derretido. Generalmente se acompaña con papas fritas o puré.\r\n\r\nPrimero se empana la carne pasándola por huevo y pan rallado, luego se cocina hasta quedar dorada. Después se agrega la salsa, el jamón y el queso, llevándola al horno unos minutos para gratinar.', 'Milanesa'),
(16, 5, 'El choripán es una comida callejera muy popular en Argentina. Se prepara con un chorizo asado servido dentro de un pan crujiente, generalmente acompañado con chimichurri o salsa criolla.\r\n\r\nEl chorizo se cocina a la parrilla hasta quedar bien dorado por fuera y jugoso por dentro. Luego se coloca dentro del pan cortado al medio y se agregan las salsas al gusto. Es habitual comerlo antes del asado principal.', 'Choripan'),
(17, 6, 'El matambre a la pizza es un plato muy popular en reuniones familiares argentinas. Consiste en un corte fino de carne vacuna cocido y cubierto con salsa de tomate, queso y orégano, similar a una pizza.\r\n\r\nPrimero el matambre se hierve o cocina a la parrilla hasta quedar tierno. Luego se le agrega salsa de tomate y abundante queso mozzarella, llevándolo nuevamente al horno o a la parrilla para gratinar. Suele acompañarse con ensalada o papas.', 'Matambre a la pizza'),
(18, 6, 'El pastel de papa es una comida casera clásica de Argentina, similar a un shepherd’s pie. Está compuesto por una base de carne picada condimentada y una capa superior de puré de papas gratinado al horno.\r\n\r\nLa carne se cocina con cebolla y especias, y muchas recetas agregan aceitunas, huevo duro o pasas de uva. Luego se cubre con puré cremoso y se hornea hasta que la superficie quede dorada y ligeramente crocante.', 'Pastel de papa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta_ingredientes`
--

CREATE TABLE `receta_ingredientes` (
  `id_ingrediente_stock` int(11) NOT NULL,
  `id_receta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `receta_ingredientes`
--

INSERT INTO `receta_ingredientes` (`id_ingrediente_stock`, `id_receta`) VALUES
(1, 13),
(2, 13),
(13, 13),
(22, 13),
(24, 13),
(9, 14),
(19, 14),
(20, 14),
(25, 14),
(26, 14),
(2, 15),
(5, 15),
(6, 15),
(7, 15),
(13, 15),
(15, 15),
(28, 15),
(11, 16),
(20, 16),
(21, 16),
(24, 16),
(30, 16),
(7, 17),
(15, 17),
(32, 17),
(33, 17),
(34, 17),
(1, 18),
(2, 18),
(13, 18),
(15, 18),
(34, 18);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alimento`
--
ALTER TABLE `alimento`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKs035ai364ed677hd60uhpmwn2` (`id_receta`);

--
-- Indices de la tabla `chef`
--
ALTER TABLE `chef`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gerente`
--
ALTER TABLE `gerente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ingrediente_stock`
--
ALTER TABLE `ingrediente_stock`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `receta`
--
ALTER TABLE `receta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK107p7ku6vkwv1ss2s4stxu7h0` (`id_chef`);

--
-- Indices de la tabla `receta_ingredientes`
--
ALTER TABLE `receta_ingredientes`
  ADD KEY `FKt5tah1ux9bft4y2ii7ubptesb` (`id_ingrediente_stock`),
  ADD KEY `FK4ke0urhh1cvjui47lvbsow6j7` (`id_receta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alimento`
--
ALTER TABLE `alimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ingrediente_stock`
--
ALTER TABLE `ingrediente_stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `receta`
--
ALTER TABLE `receta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alimento`
--
ALTER TABLE `alimento`
  ADD CONSTRAINT `FKffg22mmtn820govq74j5wacow` FOREIGN KEY (`id_receta`) REFERENCES `receta` (`id`);

--
-- Filtros para la tabla `chef`
--
ALTER TABLE `chef`
  ADD CONSTRAINT `FKmb87r6lu57oebhqpyx3xos46y` FOREIGN KEY (`id`) REFERENCES `empleado` (`id`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FKl6nu1s893o9x7pmxo0wdhfiei` FOREIGN KEY (`id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `gerente`
--
ALTER TABLE `gerente`
  ADD CONSTRAINT `FKp54tdkkpnqu6n1pcgkgg628k9` FOREIGN KEY (`id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `receta`
--
ALTER TABLE `receta`
  ADD CONSTRAINT `FK107p7ku6vkwv1ss2s4stxu7h0` FOREIGN KEY (`id_chef`) REFERENCES `chef` (`id`);

--
-- Filtros para la tabla `receta_ingredientes`
--
ALTER TABLE `receta_ingredientes`
  ADD CONSTRAINT `FK4ke0urhh1cvjui47lvbsow6j7` FOREIGN KEY (`id_receta`) REFERENCES `receta` (`id`),
  ADD CONSTRAINT `FKt5tah1ux9bft4y2ii7ubptesb` FOREIGN KEY (`id_ingrediente_stock`) REFERENCES `ingrediente_stock` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
