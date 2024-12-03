package gestionDeUsuarioV2;

import java.util.List;

public class UsuarioServicio {
	private UsuarioDAO usuarioDAO;

	public UsuarioServicio() {
		this.usuarioDAO = new UsuarioDAO();
	}

	public List<UsuarioDTO> listarUsuarios() {
		return usuarioDAO.obtenerTodos();
	}

	public boolean iniciarSesion(String idUsuario, String password) {
		String passwordMD5 = HexTransform.generarMD5(password);
		return usuarioDAO.validarCredenciales(idUsuario, passwordMD5);
	}

	public boolean crearUsuario(String idUsuario, String password, String tipoUsuario) {
		String passwordMD5 = HexTransform.generarMD5(password);
		UsuarioDTO nuevoUsuario = new UsuarioDTO(idUsuario, passwordMD5, tipoUsuario, true);
		return usuarioDAO.agregarUsuario(nuevoUsuario);
	}

	public boolean cambiarPassword(String idUsuario, String nuevoPassword) {
		String nuevoPasswordMD5 = HexTransform.generarMD5(nuevoPassword);
		return usuarioDAO.actualizarPassword(idUsuario, nuevoPasswordMD5);
	}

	public boolean eliminarUsuario(String idUsuario) {
		return usuarioDAO.eliminarUsuario(idUsuario);
	}
}
