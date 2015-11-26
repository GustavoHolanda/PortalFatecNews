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
import labeng.projeto.models.Usuario;

@ManagedBean
@SessionScoped
public class UserProfileMB implements Serializable {

	private static final long serialVersionUID = 5639642367417917210L;
	//
	private Usuario usuarioAtual;
	//
	private Perfil perfilAtual;
	private UserProfileDAO userProfileDAO;

	// s� para fins de teste
	private List<Perfil> perfis;

	public UserProfileMB() throws SQLException {
		//
		usuarioAtual = new Usuario();
		//
		perfilAtual = new Perfil();
		userProfileDAO = new UserProfileDAOImpl();
		
		
		try {
			setPerfis(userProfileDAO.listarUsuarios());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String cadastrar() {
		perfilAtual.setUsuario(usuarioAtual);
		try {
			
			if(perfilAtual.getUsuario().getEmail().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo E-mail!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getUsuario().getUsuario().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo Usuario!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getUsuario().getPassword().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo Senha!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getAvatar().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo Avatar!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getNome().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo Nome!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getSobrenome().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo Sobrenome!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getDataNascimento()== null){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo data de nascimento!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getTelefone().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo Telefone!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getCelular().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo Celular!", null);
				fc.addMessage("", msg);
			} else if(perfilAtual.getRedeSocial().isEmpty()){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Favor preencher o campo Rede Social!", null);
				fc.addMessage("", msg);
			} else{
				
				boolean existeEmail = userProfileDAO.verificaExistenciaEmail(perfilAtual.getUsuario().getEmail());
				boolean existeLogin = userProfileDAO.verificaExistenciaLogin(perfilAtual.getUsuario().getUsuario());
				if (existeEmail) {
					FacesContext fc = FacesContext.getCurrentInstance();
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							"E-mail j� cadastrado, favor inserir outro e-mail!", null);
					fc.addMessage("", msg);
				} else if (existeLogin) {
					FacesContext fc = FacesContext.getCurrentInstance();
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Usu�rio j� cadastrado, favor inserir outro usu�rio!", null);
					fc.addMessage("", msg);
				} else {
					userProfileDAO.criarPerfil(getPerfilAtual());

					FacesContext fc = FacesContext.getCurrentInstance();
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usu�rio foi cadastrado com sucesso",
							null);
					fc.addMessage("", msg);
					
					setPerfis(userProfileDAO.listarUsuarios());
					usuarioAtual = new Usuario();
					perfilAtual = new Perfil();

			}							
			}

		} catch (SQLException e) {
			e.printStackTrace();

			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar", null);
			fc.addMessage("", msg);
		}
		return "";
	}

	public void excluir(Perfil perfil){
		
		try {
			userProfileDAO.excluirPerfil(perfil.getUsuario().getIdUsuario());
			setPerfis(userProfileDAO.listarUsuarios());
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usu�rio foi apagado com sucesso",
					null);
			fc.addMessage("", msg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Perfil getPerfilAtual() {
		return perfilAtual;
	}

	public void setPerfilAtual(Perfil perfilAtual) {
		this.perfilAtual = perfilAtual;
	}
	

	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserProfileDAO getUserProfileDAO() {
		return userProfileDAO;
	}

	public void setUserProfileDAO(UserProfileDAO userProfileDAO) {
		this.userProfileDAO = userProfileDAO;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfils) {
		this.perfis = perfils;
	}

}
