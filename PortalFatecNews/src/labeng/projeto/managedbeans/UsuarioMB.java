package labeng.projeto.managedbeans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import labeng.projeto.dao.implementation.UsuarioDAOImpl;
import labeng.projeto.dao.interfaces.UsuarioDAO;
import labeng.projeto.models.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5639642367417917210L;
	private Usuario usuarioAtual;
	private UsuarioDAO usuarioDAO;

	// só para fins de teste
	private List<Usuario> usuarios;

	public UsuarioMB() throws SQLException {
		usuarioAtual = new Usuario();
		usuarioDAO = new UsuarioDAOImpl();

		try {
			setUsuarios(usuarioDAO.listarUsuarios());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String cadastrar() {
		try {
			boolean existeEmail = usuarioDAO.verificaExistenciaEmail(usuarioAtual.getEmail());
			boolean existeLogin = usuarioDAO.verificaExistenciaLogin(usuarioAtual.getLogin());
			if (existeEmail) {
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"E-mail já cadastrado, favor inserir outro e-mail!", null);
				fc.addMessage("", msg);
			} else if (existeLogin) {
					FacesContext fc = FacesContext.getCurrentInstance();
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Login já cadastrado, favor inserir outro login!", null);
					fc.addMessage("", msg);
				}  else {
				usuarioDAO.cadastrarUsuario(getUsuarioAtual());

				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário foi cadastrado com sucesso",
						null);
				fc.addMessage("", msg);
				usuarioAtual = new Usuario();
			}

		} catch (SQLException e) {
			e.printStackTrace();

			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar", null);
			fc.addMessage("", msg);
		}
		return "";
	}

	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	

	
}
