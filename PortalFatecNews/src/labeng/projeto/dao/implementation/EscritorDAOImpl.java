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
import labeng.projeto.dao.interfaces.EscritorDAO;
import labeng.projeto.models.Escritor;

public class EscritorDAOImpl implements EscritorDAO, Serializable {
	
	
	
	private static final long serialVersionUID = -389763253998144210L;
	Connection connection;
	
	public EscritorDAOImpl(){
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}
	
	
	@Override
	public void cadastrarEscritor(int id, Escritor escritor) throws SQLException {
		String sql = "INSERT INTO Escritor VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, id);
		ps.setString(2, escritor.getAvatar());
		ps.setString(3, escritor.getNome());
		ps.setString(4, escritor.getSobrenome());
		ps.setString(5, escritor.getSexo());
		ps.setDate(6, new Date(escritor.getDataNascimento().getTime()));
		ps.setString(7, escritor.getCelular());
		ps.setString(8, escritor.getTelefone());
		ps.setString(9, escritor.getEspecialidade());
		ps.setString(10, escritor.getBlog());
		ps.setString(11, escritor.getBiografiaepremios());
		ps.setInt(12, escritor.getCodigoUBE());
		ps.execute();
		ps.close();
		System.out.println("Usuário " + escritor.getNome() + " " + escritor.getSobrenome() + " cadastrado");

		
	}

	@Override
	public List<Escritor> pesquisarEscritores(String nome) throws SQLException {
		List<Escritor> lista = new ArrayList<Escritor>();
		String sql = "SELECT * FROM Escritor WHERE nome LIKE ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, "%" + nome + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Escritor e = new Escritor();
			e.setId(rs.getInt("id"));
			e.setAvatar(rs.getString("avatar"));
			e.setNome(rs.getString("nome"));
			e.setSobrenome(rs.getString("sobrenome"));
			e.setDataNascimento(rs.getDate("dataNascimento"));
			e.setSexo(rs.getString("sexo"));
			e.setTelefone(rs.getString("telefone"));
			e.setCelular(rs.getString("celular"));
			e.setEspecialidade(rs.getString("especialidade"));
			e.setBlog(rs.getString("blog"));
			e.setBiografiaepremios(rs.getString("biografiaPremios"));
			e.setCodigoUBE(rs.getInt("codigoUBE"));
			lista.add(e);
			System.out.println("Usuário com o nome: " + e.getNome() + " " + e.getSobrenome());
		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public List<Escritor> listarEscritores() throws SQLException {
		List<Escritor> lista = new ArrayList<Escritor>();
		String sql = "SELECT * FROM Escritor";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Escritor e = new Escritor();
			e.setId(rs.getInt("id"));
			e.setAvatar(rs.getString("avatar"));
			e.setNome(rs.getString("nome"));
			e.setSobrenome(rs.getString("sobrenome"));
			e.setDataNascimento(rs.getDate("dataNascimento"));
			e.setSexo(rs.getString("sexo"));
			e.setTelefone(rs.getString("telefone"));
			e.setCelular(rs.getString("celular"));
			e.setEspecialidade(rs.getString("especialidade"));
			e.setBlog(rs.getString("blog"));
			e.setBiografiaepremios(rs.getString("biografiaPremios"));
			e.setCodigoUBE(rs.getInt("codigoUBE"));
			lista.add(e);
			System.out.println("Usuário com o nome: " + e.getNome() + " " + e.getSobrenome());
		}
		ps.close();
		rs.close();
		return lista;

	}

	@Override
	public void excluirEscritor(int id) throws SQLException {
		String sql = "DELETE FROM Escritor WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		System.out.println("Escritor com o id " + id + " foi excluido com sucesso");
	
		
	}

	@Override
	public void AlterarEscritor(Escritor escritor, int id) throws SQLException {
		String sql = "UPDATE Escritor SET avatar = ?, nome = ?,"
				+ " sobrenome = ?, dataNascimento = ?, sexo = ?,"
				+ " telefone = ?, celular = ?, especialidade = ?,"
				+ " blog = ?, biografiaPremios = ? WHERE id = ? ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, escritor.getAvatar());
		ps.setString(2, escritor.getNome());
		ps.setString(3, escritor.getSobrenome());
		ps.setDate(4, new Date(escritor.getDataNascimento().getTime()));
		ps.setString(5, escritor.getSexo());
		ps.setString(6, escritor.getTelefone());
		ps.setString(7, escritor.getCelular());
		ps.setString(8, escritor.getEspecialidade());
		ps.setString(9, escritor.getBlog());
		ps.setString(10, escritor.getBiografiaepremios());
		ps.setInt(11, id);

		ps.execute();
		ps.close();
		
	}

}
