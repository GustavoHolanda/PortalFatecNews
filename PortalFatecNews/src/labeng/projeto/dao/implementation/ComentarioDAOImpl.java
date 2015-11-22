package labeng.projeto.dao.implementation;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import labeng.projeto.dao.generic.GenericDAO;
import labeng.projeto.dao.interfaces.ComentarioDAO;
import labeng.projeto.models.Comentario;

public class ComentarioDAOImpl implements ComentarioDAO, Serializable {

	private static final long serialVersionUID = 5503845982463422352L;
	Connection connection;

	public ComentarioDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public void novoComentario(Comentario com) throws SQLException {
		String sql = "INSERT INTO Comentario VALUES(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, com.getMateria().getIdMateria());
		ps.setLong(2, com.getPerfil().getIdPerfil());
		ps.setDate(3, new Date(com.getData().getTime()));
		ps.setString(4, com.getCorpo());
		ps.execute();
		ps.close();

	}

	@Override
	public void alterarComentario(Comentario com, long id) throws SQLException {
		String sql = "UPDATE Comentario SET corpo = ? WHERE idComentario = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, com.getCorpo());
		ps.setLong(2, id);
		ps.execute();
		ps.close();

	}

	@Override
	public List<Comentario> listarComentariosPorMateria(long id) throws SQLException {
		List<Comentario> lista = new ArrayList<Comentario>();
		String sql = "SELECT * FROM Comentario WHERE idMateria = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Comentario com = new Comentario();
			com.setIdComentario(rs.getLong("idComentario"));
			com.setData(rs.getDate("data"));
			com.setCorpo(rs.getString("corpo"));
			lista.add(com);
		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public void excluirComentario(long id) throws SQLException {
		String sql = "DELETE FROM Comentario WHERE idComentario = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		
		ps.execute();
		ps.close();
	}

}
