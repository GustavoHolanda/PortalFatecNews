package labeng.projeto.dao.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import labeng.projeto.dao.interfaces.LeitorDAO;
import labeng.projeto.dao.interfaces.UsuarioDAO;
import labeng.projeto.models.Leitor;
import labeng.projeto.models.Usuario;

public class Testdao {

	private static Usuario u;
	private static Leitor l;

	public static void main(String[] args) {

		u = new Usuario();

		u.setEmail("gustavofernandesdeholanda@gmail.com");
		u.setLogin("GustavoFernandes");
		u.setSenha("1234");

		l = new Leitor();
		l.setAvatar("avatar1.jpg");
		l.setNome("Gustavo");
		l.setSobrenome("Fernandes de Holanda");
		l.setSexo("Masculino");
		l.setDataNascimento(new Date());
		l.setCelular("11987976120");
		l.setTelefone("1127226475");
		l.setCategoriaPreferida("Esporte");
		l.setRedeSocial("fb.com/gustavoFernandes");

		UsuarioDAO udao = new UsuarioDAOImpl();
		LeitorDAO ldao = new LeitorDAOImpl();

		System.out.println(u.getEmail());
		try {
			int id = udao.cadastrarUsuario(u);
			ldao.cadastrarLeitor(id, l);
			ldao.pesquisarLeitores("g");

			Usuario u = new Usuario();
			u = udao.pesquisarUsuario(id);
			

			System.out.println(u.getId() + " " + u.getEmail() + " " + u.getLogin() + " " + u.getSenha());
			System.out.println("ok");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro");
		}

}

}
