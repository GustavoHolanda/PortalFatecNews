package labeng.projeto.dao.implementation;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import labeng.projeto.dao.generic.GenericDAO;
import labeng.projeto.dao.interfaces.UsuarioDAO;
import labeng.projeto.models.Leitor;
import labeng.projeto.models.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO, Serializable {

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
		String sql = "SELECT * FROM Usuario WHERE email = '" + email + "'";
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
		String sql = "SELECT * FROM Usuario WHERE login =  '" + login + "'";
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
	public int cadastrarUsuario(Usuario u) throws SQLException {
		System.out.println(u.getEmail() + "-" + u.getLogin() + "-" + u.getSenha());
		String sql = "INSERT INTO Usuario VALUES(?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, u.getEmail());
		ps.setString(2, u.getLogin());
		ps.setString(3, u.getSenha());
		ps.execute();
		System.out.println("Usuario " + u.getId() + " cadastrado");
		ResultSet rs = ps.getGeneratedKeys();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		System.out.println("Id gerado pelo insert foi " + id);
		ps.close();
		rs.close();
		return id;
	}

	@Override
	public Usuario pesquisarUsuario(int id) throws SQLException {
		Usuario u = new Usuario();
		List<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT * FROM Usuario  WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			u.setId(rs.getInt("id"));
			u.setEmail(rs.getString("email"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
			System.out.println("id: " + u.getId() + " e-mail: " + u.getEmail());
		}
		rs.close();
		ps.close();
		System.out.println("Usuario pesquisado");
		return u;
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
			u.setEmail(rs.getString("email"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
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
	public void alterarSenha(Usuario u, int id) throws SQLException {
		String sql = "UPDATE Usuario SET senha = ? WHERE id = ? ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, u.getSenha());
		ps.setInt(2, id);

		ps.execute();
		ps.close();
	}

}
