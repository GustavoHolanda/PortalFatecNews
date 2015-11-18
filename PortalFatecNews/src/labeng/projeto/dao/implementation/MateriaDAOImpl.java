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


public class MateriaDAOImpl implements MateriaDAO, Serializable {

	private static final long serialVersionUID = 8309655263098922449L;
	Connection connection;

	public MateriaDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public void cadastrarMateria(int id, Materia materia) throws SQLException {
		String sql = "INSERT INTO Materia VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setDate(1, new Date(materia.getData().getTime()));
		ps.setString(2, materia.getTitulo());
		ps.setString(3, materia.getSubTitulo());
		ps.setString(4, materia.getImagem());
		ps.setString(5, materia.getFonteImagem());
		ps.setString(6, materia.getCorpoMateria());
		ps.setString(7, materia.getFonteMateria());
		ps.setInt(8,materia.getEscritor().getId());
		ps.execute();
		ps.close();
		System.out.println("Matéria " + materia.getTitulo() + " " + materia.getSubTitulo() + " cadastrada com sucesso");

	}

	@Override
	public List<Materia> pesquisarMaterias(String nome) throws SQLException {
		List<Materia> lista = new ArrayList<Materia>();
		String sql = "SELECT * FROM Materia WHERE nome LIKE ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, "%" + nome + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Materia m = new Materia();
			m.setId(rs.getInt("id"));
			m.setData(rs.getDate("data"));
			m.setTitulo(rs.getString("titulo"));
			m.setSubTitulo(rs.getString("subtitulo"));
			m.setImagem(rs.getString("imagem"));
			m.setFonteImagem(rs.getString("fonteImagem"));
			m.setCorpoMateria(rs.getString("corpoMateria"));
			m.setFonteMateria(rs.getString("fonteMateria"));
			//m.setEscritor();
			
			
			lista.add(m);
			System.out.println("Matéria encontrada: " + m.getTitulo() + " " + m.getSubTitulo());
		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public List<Materia> listarMaterias() throws SQLException {
		List<Materia> lista = new ArrayList<Materia>();
		String sql = "SELECT * FROM Materia";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Materia e = new Materia();
			Materia m = new Materia();
			m.setId(rs.getInt("id"));
			m.setData(rs.getDate("data"));
			m.setTitulo(rs.getString("titulo"));
			m.setSubTitulo(rs.getString("subtitulo"));
			m.setImagem(rs.getString("imagem"));
			m.setFonteImagem(rs.getString("fonteImagem"));
			m.setCorpoMateria(rs.getString("corpoMateria"));
			m.setFonteMateria(rs.getString("fonteMateria"));
			//m.setEscritor();
			lista.add(e);
			System.out.println("Usuário com o nome: " + m.getTitulo() + " " + e.getSubTitulo());
		}
		ps.close();
		rs.close();
		return lista;

	}

	@Override
	public void excluirMateria(int id) throws SQLException {
		String sql = "DELETE FROM Materia WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		System.out.println("Materia com o id " + id + " foi excluido com sucesso");

	}

	@Override
	public void AlterarMateria(Materia materia, int id) throws SQLException {
		String sql = "UPDATE Materia SET data = ?,  WHERE id = ? ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setDate(1, new Date(materia.getData().getTime()));
		ps.setString(2, materia.getTitulo());
		ps.setString(3, materia.getSubTitulo());
		ps.setString(4, materia.getImagem());
		ps.setString(5, materia.getFonteImagem());
		ps.setString(6, materia.getCorpoMateria());
		ps.setString(7, materia.getFonteMateria());
		ps.setInt(8,materia.getEscritor().getId());
		ps.setInt(9, id);

		ps.execute();
		ps.close();

	}

}
