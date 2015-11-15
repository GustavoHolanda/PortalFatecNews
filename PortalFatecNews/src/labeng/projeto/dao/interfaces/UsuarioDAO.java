package labeng.projeto.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labeng.projeto.models.Usuario;

public interface UsuarioDAO {

	public void cadastrarUsuario(Usuario u) throws SQLException;
	public List<Usuario> pesquisarUsuario(String nome) throws SQLException;
	public void excluirUsuario(int id) throws SQLException;
	public void alterarUsuario(Usuario u, int id) throws SQLException;
}

