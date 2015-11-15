package labeng.projeto.dao.implementation;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import labeng.projeto.dao.generic.GenericDAO;
import labeng.projeto.dao.interfaces.UsuarioDAO;
import labeng.projeto.models.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4328186006615111026L;
	Connection connection;

	public UsuarioDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public boolean verificaExistenciaEmail(String email) throws SQLException {
		Usuario u = new Usuario();
		List<Usuario> listaemail = new ArrayList<Usuario>();
		String sql = "SELECT * FROM Usuario WHERE email = '"+email+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
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
		String sql = "SELECT * FROM Usuario WHERE login =  '"+login+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			u.setLogin("login");
			listalogin.add(u);
			return true;
		}
		ps.close();
		rs.close();
		return false;
	}

	@Override
	public void cadastrarUsuario(Usuario u) throws SQLException {
		System.out.println(u.getAvatar() + "-"
							+ u.getNome() + "-" 
							+ u.getSobrenome() + "-" 
							+ u.getEmail() + "-"
							+ u.getDataNascimento() + "-" 
							+ u.getSexo()+"-"
							+ u.getLogin()+"-"
							+ u.getSenha()+"-"
							+ u.getTelefone()+"-"
							+ u.getCelular()+"-");
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "INSERT INTO Usuario VALUES(?, ?, ? , ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, u.getAvatar());
		ps.setString(2, u.getNome());
		ps.setString(3, u.getSobrenome());
		ps.setString(4, u.getEmail());
		ps.setDate(5, new Date(u.getDataNascimento().getTime()));
		ps.setString(6, u.getSexo());
		ps.setString(7, u.getLogin());
		ps.setString(8, u.getSenha());
		ps.setString(9, u.getTelefone());
		ps.setString(10, u.getCelular());

		ps.execute();
		ps.close();
		System.out.println("Usuario " + u.getNome() + " cadastrado");
	}

	@Override
	public List<Usuario> pesquisarUsuario(String nome) throws SQLException {
		List<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT * FROM Usuario  WHERE nome LIKE ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, "%" + nome + "%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Usuario u = new Usuario();
			u.setId(rs.getInt("id"));
			u.setAvatar(rs.getString("avatar"));
			u.setNome(rs.getString("nome"));
			u.setSobrenome(rs.getString("sobrenome"));
			u.setEmail(rs.getString("email"));
			u.setDataNascimento(rs.getDate("dataNascimento"));
			u.setSexo(rs.getString("sexo"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
			u.setTelefone(rs.getString("telefone"));
			u.setCelular(rs.getString("celular"));
			lista.add(u);
			System.out.println(
					"id: " + u.getId() + " nome: " + u.getNome() + " " + u.getSobrenome() + " sexo: " + u.getSexo());
		}
		rs.close();
		ps.close();
		System.out.println("Lista cheia");
		return lista;
	}

	@Override
	public List<Usuario> listarUsuarios() throws SQLException {
		List<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT * FROM Usuario";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Usuario u = new Usuario();
			u.setId(rs.getInt("id"));
			u.setAvatar(rs.getString("avatar"));
			u.setNome(rs.getString("nome"));
			u.setSobrenome(rs.getString("sobrenome"));
			u.setEmail(rs.getString("email"));
			u.setDataNascimento(rs.getDate("dataNascimento"));
			u.setSexo(rs.getString("sexo"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
			u.setTelefone(rs.getString("telefone"));
			u.setCelular(rs.getString("celular"));
			lista.add(u);
		}
		rs.close();
		ps.close();
		System.out.println("Lista cheia(usuarios)");
		return lista;
	}

	@Override
	public void excluirUsuario(int id) throws SQLException {
		String sql = "DELETE FROM Usuario WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		System.out.println("Usuario com o id " + id + " foi excluido");

	}

	@Override
	public void alterarUsuario(Usuario u, int id) throws SQLException {
		String sql = "UPDATE Usuario SET avatar = ?, nome = ?, sobrenome = ?,"
				+ " datanascimento = ?, senha = ?, telefone = ?, celular = ? WHERE id = ? ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, u.getAvatar());
		ps.setString(2, u.getNome());
		ps.setString(3, u.getSobrenome());
		ps.setDate(4, new java.sql.Date(u.getDataNascimento().getTime()));
		ps.setString(5, u.getSenha());
		ps.setString(6, u.getTelefone());
		ps.setString(7, u.getCelular());
		ps.setInt(8, id);

		ps.execute();
		ps.close();
	}

}
