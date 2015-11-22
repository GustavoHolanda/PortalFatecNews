package labeng.projeto.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labeng.projeto.models.Comentario;

public interface ComentarioDAO {

	public void novoComentario(Comentario com) throws SQLException;
	public void alterarComentario(Comentario com, long id) throws SQLException;
	public List<Comentario> listarComentariosPorMateria(long id) throws SQLException;
	public void excluirComentario(long id) throws SQLException;
	
}
