package labeng.projeto.dao.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import labeng.projeto.dao.interfaces.UserProfileDAO;
import labeng.projeto.models.Perfil;
import labeng.projeto.models.Usuario;

public class Testdao {

	private static Perfil p;
	private static Usuario u;
	private static UserProfileDAO updao;

	public static void main(String[] args) {
		updao = new UserProfileDAOImpl();
		/*
		for (int cont = 1; cont <= 20; cont++) {
			u = new Usuario();
			u.setEmail("gustavofernandesdeholanda@hotmail.com" + cont);
			u.setUsuario("Gustavo" + cont);
			u.setPassword("senha" + cont);
			u.setTipo("L");
			
			p = new Perfil();
			p.setUsuario(u);
			p.setNome("Gustavo" + cont);
			p.setSobrenome("Fernandes de Holanda" + cont);
			p.setAvatar("Hamburguer.jpg");
			p.setSexo("Feminino");
			p.setDataNascimento(new Date());
			p.setCelular("11987976120");
			p.setTelefone("1127226475");
			p.setRedeSocial("tumblr.com/BrizaDoida");

			System.out.println(p.getNome());

			try {
				updao.criarPerfil(p);
				System.out.println("ok");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("erro");
			}
		}
		
		*/
		Perfil p;
		try {
			p = updao.pesquisaPerfilPorID(1);
			System.out.println(p.getNome() + " " + p.getUsuario().getEmail());
			

			List<Perfil> lista = new ArrayList<Perfil>();
			lista = updao.pesquisaPerfilPorNome("Gustavo1");
			System.out.println(lista.size());

			List<Perfil> lista2 = new ArrayList<Perfil>();
			lista2 = updao.listarUsuarios();
			System.out.println(lista2.size());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 
		try {
			updao.excluirPerfil(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		u = new Usuario();
	
		u.setPassword("senha123");
		u.setTipo("L");
		p = new Perfil(0, "Gustavo", "Fernandes de Holanda", "Gustavo1.jpg", "Masculino", 
				new Date(), "11987976120",
				"1127226475", "fb.com/SilvioRomeroBritoSamu", u);
		System.out.println(p.getNome());
		
		try {
			updao.atualizarPerfil(p, 6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		u = new Usuario();
		u.setPassword("123456");
		Perfil p1 = new Perfil();
		p1.setUsuario(u);
		
		try {
			updao.atualizarSenha(p1, 7);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}

}
