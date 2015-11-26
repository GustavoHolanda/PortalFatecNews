package labeng.projeto.managedbeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import labeng.projeto.dao.implementation.ComentarioDAOImpl;
import labeng.projeto.dao.interfaces.ComentarioDAO;
import labeng.projeto.models.Comentario;
import labeng.projeto.models.Materia;
import labeng.projeto.models.Perfil;

@ManagedBean
@SessionScoped
public class ComentarioMB {

	private Comentario comentarioAtual;
	private ComentarioDAO comentarioDAO;
	private List<Comentario> listaComentario;

	public ComentarioMB() {
		comentarioAtual = new Comentario();
		comentarioDAO = new ComentarioDAOImpl();
		MateriaMB mb = new MateriaMB();
		try {
			setListaComentario(comentarioDAO.listarComentariosPorMateria(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void novoComentario() {

		try {
			Materia mb = new Materia();
			Perfil p = new Perfil();
			/*MateriaMB mbean = new MateriaMB();
			mb.setIdMateria(mbean.getMateriaAtual().getIdMateria());
			UserProfileMB upmb = new UserProfileMB();
			p.setIdPerfil(upmb.getPerfilAtual().getIdPerfil());
			*/
			mb.setIdMateria(1);
			p.setIdPerfil(1);
			comentarioAtual.setMateria(mb);
			comentarioAtual.setPerfil(p);
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comentário cadastrado com sucesso", null);
			fc.addMessage("", msg);
			comentarioDAO.novoComentario(comentarioAtual);
			setListaComentario(comentarioDAO.listarComentariosPorMateria(1));
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao cadastrar comentário" + e.getMessage(), null);
			fc.addMessage("", msg);
		}
		comentarioAtual = new Comentario();
	}

	public Comentario getComentarioAtual() {
		return comentarioAtual;
	}

	public void setComentarioAtual(Comentario comentarioAtual) {
		this.comentarioAtual = comentarioAtual;
	}

	public ComentarioDAO getComentarioDAO() {
		return comentarioDAO;
	}

	public void setComentarioDAO(ComentarioDAO comentarioDAO) {
		this.comentarioDAO = comentarioDAO;
	}

	public List<Comentario> getListaComentario() {
		return listaComentario;
	}

	public void setListaComentario(List<Comentario> listaComentario) {
		this.listaComentario = listaComentario;
	}

}
