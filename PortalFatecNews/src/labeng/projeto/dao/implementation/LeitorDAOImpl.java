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
import labeng.projeto.dao.interfaces.LeitorDAO;
import labeng.projeto.models.Leitor;

public class LeitorDAOImpl implements LeitorDAO, Serializable {

	private static final long serialVersionUID = -4328186006615111026L;
	Connection connection;

	public LeitorDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public void cadastrarLeitor(int id, Leitor leitor) throws SQLException {
		String sql = "INSERT INTO Leitor VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, id);
		ps.setString(2, leitor.getAvatar());
		ps.setString(3, leitor.getNome());
		ps.setString(4, leitor.getSobrenome());
		ps.setString(5, leitor.getSexo());
		ps.setDate(6, new Date(leitor.getDataNascimento().getTime()));
		ps.setString(7, leitor.getCelular());
		ps.setString(8, leitor.getTelefone());
		ps.setString(9, leitor.getCategoriaPreferida());
		ps.setString(10, leitor.getRedeSocial());
		ps.execute();
		ps.close();
		System.out.println("Usuario " + leitor.getNome() + " " + leitor.getSobrenome() + " cadastrado");

	}

	@Override
	public List<Leitor> pesquisarLeitores(String nome) throws SQLException {
		List<Leitor> lista = new ArrayList<Leitor>();
		String sql = "SELECT * FROM Leitor WHERE nome LIKE ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, "%" + nome + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Leitor l = new Leitor();
			l.setId(rs.getInt("id"));
			l.setAvatar(rs.getString("avatar"));
			l.setNome(rs.getString("nome"));
			l.setSobrenome(rs.getString("sobrenome"));
			l.setDataNascimento(rs.getDate("dataNascimento"));
			l.setSexo(rs.getString("sexo"));
			l.setTelefone(rs.getString("telefone"));
			l.setCelular(rs.getString("celular"));
			l.setCategoriaPreferida(rs.getString("categoriaPreferida"));
			l.setRedeSocial(rs.getString("redeSocial"));
			lista.add(l);
			System.out.println("usuario com o nome: " + l.getNome() + " " + l.getSobrenome());
		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public List<Leitor> listarLeitores() throws SQLException {
		List<Leitor> lista = new ArrayList<Leitor>();
		String sql = "SELECT * FROM Leitor";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Leitor l = new Leitor();
			l.setId(rs.getInt("id"));
			l.setAvatar(rs.getString("avatar"));
			l.setNome(rs.getString("nome"));
			l.setSobrenome(rs.getString("sobrenome"));
			l.setDataNascimento(rs.getDate("dataNascimento"));
			l.setSexo(rs.getString("sexo"));
			l.setTelefone(rs.getString("telefone"));
			l.setCelular(rs.getString("celular"));
			l.setCategoriaPreferida(rs.getString("categoriaPreferida"));
			l.setRedeSocial(rs.getString("redeSocial"));
			lista.add(l);
			System.out.println("usuario com o nome: " + l.getNome() + " " + l.getSobrenome());
		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public void excluirLeitor(int id) throws SQLException {
		String sql = "DELETE FROM Leitor WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		System.out.println("Leitor com o id " + id + " foi excluido com sucesso");
	}

	@Override
	public void AlterarLeitor(Leitor leitor, int id) throws SQLException {
		String sql = "UPDATE Leitor SET avatar = ?, nome = ?,"
				+ " sobrenome = ?, dataNascimento = ?, sexo = ?,"
				+ " telefone = ?, celular = ?, categoriaPreferida = ?,"
				+ " redeSocial = ? WHERE id = ? ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, leitor.getAvatar());
		ps.setString(2, leitor.getNome());
		ps.setString(3, leitor.getSobrenome());
		ps.setDate(4, new Date(leitor.getDataNascimento().getTime()));
		ps.setString(5, leitor.getSexo());
		ps.setString(6, leitor.getTelefone());
		ps.setString(7, leitor.getCelular());
		ps.setString(8, leitor.getCategoriaPreferida());
		ps.setString(9, leitor.getRedeSocial());
		ps.setInt(10, id);

		ps.execute();
		ps.close();
	}

}
