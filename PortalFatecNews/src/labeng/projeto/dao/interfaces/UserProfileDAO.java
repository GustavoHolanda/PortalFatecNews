package labeng.projeto.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labeng.projeto.models.Perfil;

public interface UserProfileDAO {

	public void criarPerfil(Perfil perfil) throws SQLException;
	public List<Perfil> pesquisaPerfilPorNome(String nome) throws SQLException;
	public Perfil pesquisaPerfilPorID(long id) throws SQLException;
	public List<Perfil> listarUsuarios() throws SQLException;
	public void excluirPerfil(long id) throws SQLException;
	public void atualizarPerfil(Perfil perfil, long id) throws SQLException;
	public void atualizarTipo(Perfil perfil, long id)throws SQLException;
	public void atualizarSenha(Perfil perfil, long id)throws SQLException;
	public boolean verificaExistenciaEmail(String email) throws SQLException;
	public boolean verificaExistenciaLogin(String login) throws SQLException;
	

}
