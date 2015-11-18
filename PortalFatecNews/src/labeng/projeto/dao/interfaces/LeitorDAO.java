package labeng.projeto.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labeng.projeto.models.Leitor;

public interface LeitorDAO {

	public void cadastrarLeitor(int id, Leitor leitor) throws SQLException;
	public List<Leitor> pesquisarLeitores(String nome) throws SQLException;
	public List<Leitor> listarLeitores() throws SQLException;
	public void excluirLeitor(int id) throws SQLException;
	public void AlterarLeitor(Leitor leitor, int id) throws SQLException;
	
}
