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
import labeng.projeto.dao.interfaces.MateriaDAO;
import labeng.projeto.models.Materia;
import labeng.projeto.models.Usuario;

public class MateriaDAOImpl implements MateriaDAO, Serializable {

	private static final long serialVersionUID = -5126846627348498050L;

	Connection connection;

	public MateriaDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public void novaMateria(Materia mat) throws SQLException {
		String sql = "INSERT INTO Materia VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, mat.getPerfil().getIdPerfil());
		ps.setString(2, mat.getTitulo());
		ps.setString(3, mat.getSubtitulo());
		ps.setDate(4, new Date(mat.getData().getTime()));
		ps.setString(5, mat.getImagem());
		ps.setString(6, mat.getFonteImagem());
		ps.setString(7, mat.getCorpo());
		ps.setString(8, mat.getFonteMateria());
		
		ps.execute();
		ps.close();
	}

	@Override
	public void alterarMateria(Materia mat, long id) throws SQLException {
		String sql = "UPDATE Materia SET titulo = ?, subTitulo = ?,"
				+ " data = ?, imagem = ?, fonteImagem = ?,"
				+ " corpo = ?, fonteMateria = ? WHERE idMateria = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, mat.getTitulo());
		ps.setString(2, mat.getSubtitulo());
		ps.setDate(3, new Date(mat.getData().getTime()));
		ps.setString(4, mat.getImagem());
		ps.setString(5, mat.getFonteImagem());
		ps.setString(6, mat.getCorpo());
		ps.setString(7, mat.getFonteMateria());
		ps.setLong(8, id);
		ps.execute();
		ps.close();
	}

	@Override
	public List<Materia> pesquisaMateriaPorTitulo(String titulo) throws SQLException {
		List<Materia> lista = new ArrayList<Materia>();
		String sql = "SELECT * FROM Materia WHERE titulo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, "%" + titulo + "%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Materia mat = new Materia();
			mat.setIdMateria(rs.getLong("idMateria"));
			mat.setTitulo(rs.getString("titulo"));
			mat.setSubtitulo(rs.getString("subTitulo"));
			mat.setImagem(rs.getString("imagem"));
			mat.setFonteImagem(rs.getString("fonteImagem"));
			mat.setData(rs.getDate("data"));
			mat.setCorpo(rs.getString("corpo"));
			mat.setFonteMateria(rs.getString("fonteMateria"));
			lista.add(mat);
		}
		rs.close();
		ps.close();
		return lista;
	}

	@Override
	public List<Materia> listaMaterias() throws SQLException {
		List<Materia> lista = new ArrayList<Materia>();
		String sql = "SELECT * FROM Materia";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Materia mat = new Materia();
			mat.setIdMateria(rs.getLong("idMateria"));
			mat.setTitulo(rs.getString("titulo"));
			mat.setSubtitulo(rs.getString("subTitulo"));
			mat.setImagem(rs.getString("imagem"));
			mat.setFonteImagem(rs.getString("fonteImagem"));
			mat.setData(rs.getDate("data"));
			mat.setCorpo(rs.getString("corpo"));
			mat.setFonteMateria(rs.getString("fonteMateria"));
			lista.add(mat);
		}
		rs.close();
		ps.close();
		return lista;
	}

	@Override
	public void excluirMateria(long id) throws SQLException {
		String sql = "DELETE FROM Materia WHERE idMateria = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		ps.execute();
		ps.close();
	}

	@Override
	public boolean verificaExistenciaMateria(String materia) throws SQLException {
		Materia m = new Materia();
		List<Materia> listaMateria = new ArrayList<Materia>();
		String sql = "SELECT * FROM Materia WHERE titulo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, materia);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			m.setTitulo("titulo");
			listaMateria.add(m);
			return true;
		}
		ps.close();
		rs.close();
		return false;
	}
}
