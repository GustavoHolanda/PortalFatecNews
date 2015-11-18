package labeng.projeto.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labeng.projeto.models.Materia;


public interface MateriaDAO {
	public void cadastrarMateria(int id, Materia materia) throws SQLException;
	public List<Materia> pesquisarMaterias(String nome) throws SQLException;
	public List<Materia> listarMaterias() throws SQLException;
	public void excluirMateria(int id) throws SQLException;
	public void AlterarMateria(Materia materia, int id) throws SQLException;
}
