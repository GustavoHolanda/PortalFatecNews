package labeng.projeto.models;

import java.io.Serializable;
import java.util.Date;

public class Comentario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -631562225453181524L;
	private long idComentario;
	private Materia materia;
	private Perfil perfil;
	private Date data = new Date();
	private String corpo;

	public long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(long idComentario) {
		this.idComentario = idComentario;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

}
