package gestionDeUsuarioV2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
	private static final String SQL_SELECT_ALL = "SELECT * FROM usuarios";
	private static final String SQL_INSERT = "INSERT INTO usuarios (id_usuario, password, type_user, activo) VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE_PASSWORD = "UPDATE usuarios SET password = ? WHERE id_usuario = ?";
	private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id_usuario = ?";
	private Connection connection;

	public UsuarioDAO() {
		this.connection = ConexionBD.getConexion();
	}

	public boolean validarCredenciales(String idUsuario, String passwordMD5) {
		String SQL_LOGIN = "SELECT COUNT(*) FROM usuarios WHERE id_usuario = ? AND password = ?";
		try (PreparedStatement ps = connection.prepareStatement(SQL_LOGIN)) {
			ps.setString(1, idUsuario);
			ps.setString(2, passwordMD5);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public List<UsuarioDTO> obtenerTodos() {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				UsuarioDTO usuario = new UsuarioDTO(rs.getString("id_usuario"), rs.getString("password"),
						rs.getString("type_user"), rs.getBoolean("activo"));
				usuarios.add(usuario);
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return usuarios;
	}

	public boolean agregarUsuario(UsuarioDTO usuario) {
		try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
			ps.setString(1, usuario.getIdUsuario());
			ps.setString(2, usuario.getPasswordMD5());
			ps.setString(3, usuario.getTipoUsuario());
			ps.setBoolean(4, usuario.isActivo());
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public boolean actualizarPassword(String idUsuario, String nuevoPasswordMD5) {
		try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_PASSWORD)) {
			ps.setString(1, nuevoPasswordMD5);
			ps.setString(2, idUsuario);
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
	
	

	public boolean eliminarUsuario(String idUsuario) {
		try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
			ps.setString(1, idUsuario);
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
}