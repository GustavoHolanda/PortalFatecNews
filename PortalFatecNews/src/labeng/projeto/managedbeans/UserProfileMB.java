package labeng.projeto.managedbeans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import labeng.projeto.dao.implementation.UserProfileDAOImpl;
import labeng.projeto.dao.interfaces.UserProfileDAO;
import labeng.projeto.models.Perfil;

@ManagedBean
@SessionScoped
public class UserProfileMB implements Serializable {

	private static final long serialVersionUID = 5639642367417917210L;
	private Perfil perfilAtual;
	private UserProfileDAO userProfileDAO;

	// só para fins de teste
	private List<Perfil> perfils;

	public UserProfileMB() throws SQLException {
		perfilAtual = new Perfil();
		userProfileDAO = new UserProfileDAOImpl();

		try {
			setPerfils(userProfileDAO.listarUsuarios());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String cadastrar() {
		try {
			boolean existeEmail = userProfileDAO.verificaExistenciaEmail(perfilAtual.getUsuario().getEmail());
			boolean existeLogin = userProfileDAO.verificaExistenciaLogin(perfilAtual.getUsuario().getUsuario());
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
			} else {
				userProfileDAO.criarPerfil(getPerfilAtual());

				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário foi cadastrado com sucesso",
						null);
				fc.addMessage("", msg);
				perfilAtual = new Perfil();
			}

		} catch (SQLException e) {
			e.printStackTrace();

			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar", null);
			fc.addMessage("", msg);
		}
		return "";
	}

	public Perfil getPerfilAtual() {
		return perfilAtual;
	}

	public void setPerfilAtual(Perfil perfilAtual) {
		this.perfilAtual = perfilAtual;
	}

	public UserProfileDAO getUserProfileDAO() {
		return userProfileDAO;
	}

	public void setUserProfileDAO(UserProfileDAO userProfileDAO) {
		this.userProfileDAO = userProfileDAO;
	}

	public List<Perfil> getPerfils() {
		return perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

}
