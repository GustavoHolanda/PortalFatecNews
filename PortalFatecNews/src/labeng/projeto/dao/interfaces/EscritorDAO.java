package labeng.projeto.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labeng.projeto.models.Escritor;

public interface EscritorDAO {

	public void cadastrarEscritor(int id, Escritor escritor) throws SQLException;
	public List<Escritor> pesquisarEscritores(String nome) throws SQLException;
	public List<Escritor> listarEscritores() throws SQLException;
	public void excluirEscritor(int id) throws SQLException;
	public void AlterarEscritor(Escritor escritor, int id) throws SQLException;
}
