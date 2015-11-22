package labeng.projeto.dao.implementation;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import labeng.projeto.dao.generic.GenericDAO;
import labeng.projeto.dao.interfaces.UserProfileDAO;
import labeng.projeto.models.Perfil;
import labeng.projeto.models.Usuario;

public class UserProfileDAOImpl implements UserProfileDAO, Serializable {

	private static final long serialVersionUID = -1939782733754703841L;

	Connection connection;

	public UserProfileDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public void criarPerfil(Perfil perfil) throws SQLException {

		String sqlUsuario = "INSERT INTO Usuario VALUES(?, ?, ?, ?)";
		PreparedStatement psUsuario = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);

		psUsuario.setString(1, perfil.getUsuario().getEmail());
		psUsuario.setString(2, perfil.getUsuario().getUsuario());
		psUsuario.setString(3, perfil.getUsuario().getPassword());
		psUsuario.setString(4, perfil.getUsuario().getTipo());
		psUsuario.execute();
		ResultSet rs = psUsuario.getGeneratedKeys();
		rs.next();
		perfil.getUsuario().setIdUsuario(rs.getInt(1));
		psUsuario.close();

		String sqlPerfil = "INSERT INTO Perfil VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement psPerfil = connection.prepareStatement(sqlPerfil);

		psPerfil.setLong(1, perfil.getUsuario().getIdUsuario());
		psPerfil.setString(2, perfil.getAvatar());
		psPerfil.setString(3, perfil.getNome());
		psPerfil.setString(4, perfil.getSobrenome());
		psPerfil.setString(5, perfil.getSexo());
		psPerfil.setDate(6, new Date(perfil.getDataNascimento().getTime()));
		psPerfil.setString(7, perfil.getCelular());
		psPerfil.setString(8, perfil.getTelefone());
		psPerfil.setString(9, perfil.getRedeSocial());

		psPerfil.execute();
		psPerfil.close();
	}

	@Override
	public List<Perfil> pesquisaPerfilPorNome(String nome) throws SQLException {
		List<Perfil> lista = new ArrayList<Perfil>();
		String sqlPesquisa = "SELECT u.idUsuario, u.email, u.senha, u.tipo,"
				+ "p.idPerfil, p.avatar, p.nome, p.sobrenome, p.sexo," + "p.dataNascimento, p.celular, p.telefone,"
				+ "p.redeSocial FROM Usuario u " + " INNER JOIN Perfil p " + " ON p.idUsuario = u.idUsuario"
				+ " WHERE p.nome LIKE ?";
		PreparedStatement ps = connection.prepareStatement(sqlPesquisa);
		ps.setString(1, "%" + nome + "%");
		System.out.println(sqlPesquisa);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Usuario u = new Usuario();
			u.setIdUsuario(rs.getLong("idUsuario"));
			u.setEmail(rs.getString("email"));
			u.setPassword(rs.getString("senha"));
			u.setTipo(rs.getString("tipo"));
			Perfil p = new Perfil();
			p.setUsuario(u);
			p.setIdPerfil(rs.getLong("idPerfil"));
			p.setNome(rs.getString("nome"));
			p.setSobrenome(rs.getString("sobrenome"));
			p.setAvatar(rs.getString("avatar"));
			p.setSexo(rs.getString("sexo"));
			p.setDataNascimento(rs.getDate("dataNascimento"));
			p.setCelular(rs.getString("celular"));
			p.setTelefone(rs.getString("telefone"));
			p.setRedeSocial(rs.getString("redeSocial"));
			lista.add(p);

		}
		rs.close();
		ps.close();
		return lista;
	}

	@Override
	public Perfil pesquisaPerfilPorID(long id) throws SQLException {
		String sqlPesquisa = "SELECT u.idUsuario, u.email, u.senha, u.tipo,"
				+ "p.idPerfil, p.avatar, p.nome, p.sobrenome, p.sexo," + "p.dataNascimento, p.celular, p.telefone,"
				+ "p.redeSocial FROM Usuario u" + " INNER JOIN Perfil p " + "ON p.idUsuario = u.idUsuario"
				+ " WHERE p.idPerfil = ?";
		System.out.println(sqlPesquisa);
		PreparedStatement ps = connection.prepareStatement(sqlPesquisa);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Usuario u = new Usuario();
			u.setIdUsuario(rs.getLong("idUsuario"));
			u.setEmail(rs.getString("email"));
			u.setPassword(rs.getString("senha"));
			u.setTipo(rs.getString("tipo"));
			Perfil p = new Perfil();
			p.setUsuario(u);
			p.setIdPerfil(rs.getLong("idPerfil"));
			p.setNome(rs.getString("nome"));
			p.setSobrenome(rs.getString("sobrenome"));
			p.setAvatar(rs.getString("avatar"));
			p.setSexo(rs.getString("sexo"));
			p.setDataNascimento(rs.getDate("dataNascimento"));
			p.setCelular(rs.getString("celular"));
			p.setTelefone(rs.getString("telefone"));
			p.setRedeSocial(rs.getString("redeSocial"));
			rs.close();
			ps.close();
			return p;
		}

		rs.close();
		ps.close();
		return null;
	}

	@Override
	public void atualizarPerfil(Perfil perfil, long id) throws SQLException {
		String sqlPerfil = "UPDATE Perfil SET avatar = ?, nome = ?, sobrenome = ?,"
				+ "sexo = ?, dataNascimento = ?, celular = ?," + "telefone = ?, redeSocial = ?  WHERE idUsuario = ?";
		PreparedStatement psPerfil = connection.prepareStatement(sqlPerfil);
		psPerfil.setString(1, perfil.getAvatar());
		psPerfil.setString(2, perfil.getNome());
		psPerfil.setString(3, perfil.getSobrenome());
		psPerfil.setString(4, perfil.getSexo());
		psPerfil.setDate(5, new java.sql.Date(perfil.getDataNascimento().getTime()));
		psPerfil.setString(6, perfil.getCelular());
		psPerfil.setString(7, perfil.getTelefone());
		psPerfil.setString(8, perfil.getRedeSocial());
		psPerfil.setLong(9, id);
		psPerfil.execute();
		psPerfil.close();

		String sqlUsuario = "UPDATE Usuario SET senha = ?, tipo = ?" + " WHERE idUsuario = ?";
		PreparedStatement psUsuario = connection.prepareStatement(sqlUsuario);
		psUsuario.setString(1, perfil.getUsuario().getPassword());
		psUsuario.setString(2, perfil.getUsuario().getTipo());
		psUsuario.setLong(3, id);
		psUsuario.execute();
		psUsuario.close();

	}

	@Override
	public void atualizarTipo(Perfil perfil, long id) throws SQLException {
		String sql = "UPDATE Usuario SET tipo = ? WHERE idUsuario = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, perfil.getUsuario().getTipo());
		ps.setLong(2, id);
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizarSenha(Perfil perfil, long id) throws SQLException {
		String sql = "UPDATE Usuario SET senha = ? WHERE idUsuario = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, perfil.getUsuario().getPassword());
		ps.setLong(2, id);
		ps.execute();
		ps.close();
	}

	@Override
	public void excluirPerfil(long id) throws SQLException {
		String sqlExcluirPerfil = "DELETE FROM Perfil WHERE idUsuario = ?";
		PreparedStatement ps1 = connection.prepareStatement(sqlExcluirPerfil);
		ps1.setLong(1, id);
		ps1.execute();
		ps1.close();
		String sqlExcluirUsuario = "DELETE FROM Usuario WHERE idUsuario = ?";
		PreparedStatement ps = connection.prepareStatement(sqlExcluirUsuario);
		ps.setLong(1, id);
		ps.execute();
		ps.close();

		System.out.println("Usuario com o id " + id + " foi excluido");
	}

	@Override
	public boolean verificaExistenciaEmail(String email) throws SQLException {
		Usuario u = new Usuario();
		List<Usuario> listaemail = new ArrayList<Usuario>();
		String sql = "SELECT * FROM Usuario WHERE email = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			u.setEmail("email");
			listaemail.add(u);
			return true;
		}
		ps.close();
		rs.close();
		return false;
	}

	@Override
	public boolean verificaExistenciaLogin(String login) throws SQLException {
		Usuario u = new Usuario();
		List<Usuario> listalogin = new ArrayList<Usuario>();
		String sql = "SELECT * FROM Usuario WHERE usuario =  ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, login);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			u.setUsuario("usuario");
			listalogin.add(u);
			return true;
		}
		ps.close();
		rs.close();
		return false;
	}

	@Override
	public List<Perfil> listarUsuarios() throws SQLException {
		List<Perfil> lista = new ArrayList<Perfil>();
		String sqlPesquisa = "SELECT u.idUsuario, u.email, u.usuario, u.senha, u.tipo,"
				+ "p.idPerfil, p.avatar, p.nome, p.sobrenome, p.sexo," + "p.dataNascimento, p.celular, p.telefone,"
				+ "p.redeSocial FROM Usuario u " + " INNER JOIN Perfil p " + " ON p.idUsuario = u.idUsuario";
		PreparedStatement ps = connection.prepareStatement(sqlPesquisa);
		System.out.println(sqlPesquisa);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Usuario u = new Usuario();
			u.setIdUsuario(rs.getLong("idUsuario"));
			u.setEmail(rs.getString("email"));
			u.setUsuario(rs.getString("usuario"));
			u.setPassword(rs.getString("senha"));
			u.setTipo(rs.getString("tipo"));
			Perfil p = new Perfil();
			p.setUsuario(u);
			p.setIdPerfil(rs.getLong("idPerfil"));
			p.setNome(rs.getString("nome"));
			p.setSobrenome(rs.getString("sobrenome"));
			p.setAvatar(rs.getString("avatar"));
			p.setSexo(rs.getString("sexo"));
			p.setDataNascimento(rs.getDate("dataNascimento"));
			p.setCelular(rs.getString("celular"));
			p.setTelefone(rs.getString("telefone"));
			p.setRedeSocial(rs.getString("redeSocial"));
			lista.add(p);
		}
		rs.close();
		ps.close();
		return lista;
	}

}
