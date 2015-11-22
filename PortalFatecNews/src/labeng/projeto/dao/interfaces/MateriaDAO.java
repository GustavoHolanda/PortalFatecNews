package labeng.projeto.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labeng.projeto.models.Materia;

public interface MateriaDAO {

	public void novaMateria(Materia mat) throws SQLException;
	public void alterarMateria(Materia mat, long id) throws SQLException;
	public List<Materia> pesquisaMateriaPorTitulo(String titulo) throws SQLException;
	public List<Materia> listaMaterias() throws SQLException;
	public void excluirMateria(long id) throws SQLException;
	public boolean verificaExistenciaMateria(String materia) throws SQLException;
}
