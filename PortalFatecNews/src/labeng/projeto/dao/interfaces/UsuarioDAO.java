package labeng.projeto.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labeng.projeto.models.Usuario;

public interface UsuarioDAO {
	
	public boolean verificaExistenciaEmail(String email) throws SQLException;
	public boolean verificaExistenciaLogin(String login) throws SQLException;
	
	public int cadastrarUsuario(Usuario u) throws SQLException;
	public Usuario pesquisarUsuario(int id) throws SQLException;
	public List<Usuario> listarUsuarios() throws SQLException;
	
	public void excluirUsuario(int id) throws SQLException;
	public void alterarSenha(Usuario u, int id) throws SQLException;
}

