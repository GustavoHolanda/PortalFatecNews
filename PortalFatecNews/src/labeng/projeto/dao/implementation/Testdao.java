package labeng.projeto.dao.implementation;

import java.sql.SQLException;
import java.util.Date;

import labeng.projeto.dao.interfaces.UsuarioDAO;
import labeng.projeto.models.Usuario;

public class Testdao {

	private static Usuario u;
	
	public static void main(String[] args) {
		
		
		 u = new Usuario();
		 u.setAvatar("a");
		 u.setNome("a");
		 u.setSobrenome("a");
		 u.setEmail("a");
		 u.setDataNascimento(new Date());
		 u.setLogin("a");
		 u.setSenha("a");
		 u.setSexo("Masculino");
		 u.setTelefone("22222222");
		 u.setCelular("22222222");
		 
		 UsuarioDAO udao = new UsuarioDAOImpl();
		 System.out.println(u.getNome());
		 try {
			udao.cadastrarUsuario(u);
			System.out.println("ok");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro");
		}
		 
		
	}

}
