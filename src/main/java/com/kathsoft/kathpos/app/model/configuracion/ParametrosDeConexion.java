package com.kathsoft.kathpos.app.model.configuracion;

/**
 * Parámetros de conexión introducidos por el instalador.
 *
 * <p>Es un objeto de configuración, no una entidad de negocio. La contraseña
 * no forma parte de {@link #toString()} para evitar filtraciones en logs.</p>
 *
 * @param host servidor MySQL/MariaDB o dirección IP
 * @param port puerto TCP de la base de datos
 * @param name esquema o base de datos
 * @param user usuario de la base de datos
 * @param password contraseña, que puede estar vacía en entornos configurados así
 * @param params parámetros JDBC, sin el prefijo {@code ?}
 */
public record ParametrosDeConexion(
        String host, int port, String name, String user, String password, String params) {

    /**
     * Valida los campos y evita que el host o el esquema modifiquen la URL JDBC.
     *
     * @throws IllegalArgumentException si existe un valor inválido
     */
    public void validar() {
        if (host == null || host.isBlank() || host.matches(".*[/?#\\s].*")) {
            throw new IllegalArgumentException("El servidor debe ser un host o dirección IP válida");
        }
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("El puerto debe estar entre 1 y 65535");
        }
        if (name == null || !name.matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("El nombre de la base de datos es obligatorio y sólo acepta letras, números, guiones y guion bajo");
        }
        if (user == null || user.isBlank()) {
            throw new IllegalArgumentException("El usuario de la base de datos es obligatorio");
        }
        if (password == null) {
            throw new IllegalArgumentException("La contraseña no puede ser nula; utilice una cadena vacía si corresponde");
        }
        if (params == null || params.contains("#") || params.contains("\\r") || params.contains("\\n")) {
            throw new IllegalArgumentException("Los parámetros JDBC contienen caracteres no admitidos");
        }
    }

    @Override
    public String toString() {
        return "ParametrosDeConexion[host=" + host + ", port=" + port
                + ", name=" + name + ", user=" + user + ", params=" + params
                + ", password=<oculta>]";
    }
}
