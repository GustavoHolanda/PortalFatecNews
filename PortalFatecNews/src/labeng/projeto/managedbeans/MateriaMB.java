package labeng.projeto.managedbeans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import labeng.projeto.dao.implementation.MateriaDAOImpl;
import labeng.projeto.dao.interfaces.MateriaDAO;
import labeng.projeto.models.Materia;
import labeng.projeto.models.Perfil;

@ManagedBean
@SessionScoped
public class MateriaMB implements Serializable {

	private static final long serialVersionUID = 1378337282300656513L;

	private Materia materiaAtual;
	private MateriaDAO materiaDAO;
	private List<Materia> materias;

	public MateriaMB() {
		materiaAtual = new Materia();
		materiaDAO = new MateriaDAOImpl();
		try {
			setMaterias(materiaDAO.listaMaterias());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void cadastrarMateria() {

		try {
			/*
			UserProfileMB up = new UserProfileMB();
			Perfil p = new Perfil();
			p.setIdPerfil(up.getPerfilAtual().getIdPerfil());
			*/
			boolean existeEmail = materiaDAO.verificaExistenciaMateria(materiaAtual.getTitulo());
			if(existeEmail){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Uma matéria com esse título já cadastrada, favor inserir outro título!", null);
				fc.addMessage("", msg);
			} else {
			Perfil p = new Perfil();
			p.setIdPerfil(1);
			materiaAtual.setPerfil(p);
			materiaDAO.novaMateria(materiaAtual);
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Materia foi cadastrada com sucesso", null);
			fc.addMessage("", msg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar nova materia", null);
			fc.addMessage("", msg);
		}
		
	}

	public Materia getMateriaAtual() {
		return materiaAtual;
	}

	public void setMateriaAtual(Materia materiaAtual) {
		this.materiaAtual = materiaAtual;
	}

	public MateriaDAO getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(MateriaDAO materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
