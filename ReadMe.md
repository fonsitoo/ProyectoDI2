- Steam Community Market FX

Proyecto de Desarrollo de Interfaces implementado en Java utilizando JavaFX y patrón MVC.
La aplicación simula el mercado de compra-venta de items de la plataforma Steam, permitiendo la gestión de usuarios, publicaciones y administración de inventario con persistencia en base de datos MySQL.

-- Descripción Técnica

El proyecto ha sido desarrollado siguiendo una arquitectura por capas estricta para garantizar la mantenibilidad y escalabilidad del código.

--- Tecnologías y Patrones
* Lenguaje: Java 21 (OpenJDK).
* Interfaz Gráfica: JavaFX con diseño definido en archivos FXML.
* Gestión de Dependencias: Maven.
* Base de Datos: MySQL (Conector JDBC mysql-connector-j).
* Patrón Arquitectónico: Modelo-Vista-Controlador (MVC).
* Patrones de Diseño:
    * DAO (Data Access Object): Para desacoplar la lógica de negocio del acceso a datos (ItemDAO, UsuarioDAO, CategoriaDAO).
    * Singleton: Implementado en la clase ConexionBD para gestionar una única instancia de conexión y en UserSession para mantener el estado del usuario.

-- Funcionalidades Implementadas

--- 1. Gestión de Datos y Persistencia
* Conexión a base de datos relacional MySQL.
* Implementación de relaciones 1:N (Usuario-Items) y N:M (Items-Categorías) mediante tabla intermedia.
* Operaciones CRUD (Lectura, Inserción y Borrado) a través de sentencias SQL preparadas (PreparedStatement) para evitar inyección SQL.

--- 2. Lógica de Negocio
* Cálculos automáticos: El sistema calcula en tiempo real la comisión de la plataforma (15%) sobre el precio de venta.
* Traducción de valores: Interpretación del valor numérico "float" (desgaste del arma) a su denominación en texto (ej. "Factory New") mediante listeners en los componentes.
* Gestión de Sesiones:** Sistema de login validado contra base de datos y persistencia del usuario activo mediante Singleton.

--- 3. Interfaz de Usuario (UI)
* Componentes avanzados: Uso de TableView para listados, Slider para valores numéricos, ListView con selección múltiple para categorías, RadioButtons y CheckBox.
* Navegación: Cambio dinámico de escenas mediante una clase de utilidad (SceneSwitcher).
* Estilos: Personalización visual mediante hoja de estilos CSS (style.css) para replicar la identidad visual de la plataforma original.

--- 4. Seguridad
* Control de Acceso (RBAC): Funcionalidad restringida según el rol del usuario. El botón de eliminación de items solo es visible y accesible para usuarios con rol de Administrador.

-- Instrucciones de Instalación

1.  Base de Datos:
    * Ejecutar el script database.sql incluido en el proyecto en un servidor MySQL local.
    * Verificar que la base de datos marketplacesteam_db ha sido creada correctamente.
    * Asegurar que las credenciales en edu.arf.liceo.utils.ConexionBD coinciden con la configuración local.

2.  Ejecución:
    * Compilar el proyecto con Maven para descargar las dependencias.
    * Ejecutar la clase principal MainApp.

-- Credenciales de Prueba

Para la verificación de los distintos roles de usuario, se pueden utilizar las siguientes cuentas preconfiguradas:

| Rol           | Usuario | Contraseña | Privilegios |
|:--------------| :--- | :--- | :--- |
| Administrador | GabeN | admin123 | Acceso total (Venta y Eliminación). |
| Usuario       | TraderPro | 1234 | Acceso limitado (Solo Venta). |
| Usuario       | NoobPlayer | 0000 | Acceso limitado. |

-- Estructura del Proyecto

El código fuente se organiza en los siguientes paquetes bajo edu.arf.liceo:

* controller: Controladores de las vistas FXML (LoginController, MarketController, SellController`).
* dao: Clases de acceso a datos.
* model: Clases POJO que representan las entidades de la base de datos (Item, Usuario, Categoria).
* utils: Clases de utilidad transversal (ConexionBD, SceneSwitcher, UserSession).