package labeng.projeto.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
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
		/*try {
			setMaterias(materiaDAO.listaMaterias());
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

	}
	
	

	public void cadastrarMateria() {
		try {
			/*
			 * UserProfileMB up = new UserProfileMB(); Perfil p = new Perfil();
			 * p.setIdPerfil(up.getPerfilAtual().getIdPerfil());
			 */
			boolean existeMateria = materiaDAO.verificaExistenciaMateria(materiaAtual.getTitulo());
			if (existeMateria) {
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
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Materia foi cadastrada com sucesso",
						
						null);
				fc.addMessage("", msg);
				
				materiaAtual = new Materia();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar nova materia", null);
			fc.addMessage("", msg);
		}

	}

	public void consultaMateria(long id) {
		try {
			materiaAtual = materiaDAO.carregaMateria(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void carregaMateriasPorNome() {
		try {
			
			// setMaterias(materiaDAO.pesquisaMateriaPorTitulo(materiaAtual.getTitulo()));
			setMaterias(materiaDAO.pesquisaMateriaPorTitulo(materiaAtual.getTitulo()));
			System.out.println(materias.size());
			if (materias.size() < 0) {
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Não foi encontrada nenhuma matéria com esse título.", null);
				fc.addMessage("", msg);
			} else {
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Foram encontradas: " + materias.size()
						+ " matérias com o título: " + materiaAtual.getTitulo() + ".", null);
				fc.addMessage("", msg);
			}
		} catch (SQLException e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao pesquisar materias" + e.getMessage(), null);
			fc.addMessage("", msg);
		}
	}

	public void chamarMateria(String materia) {
		try {
			materiaAtual.setTitulo(materia);
			long retorno;
			retorno = materiaDAO.pesquisaMateriaPorTituloReturnUm(materiaAtual.getTitulo());
			materiaAtual.setIdMateria(retorno);
			FacesContext.getCurrentInstance().getExternalContext().redirect("Materias.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void chamarPesquisar(){
		
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("Pesquisa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
