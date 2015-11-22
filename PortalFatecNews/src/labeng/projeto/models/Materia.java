package labeng.projeto.models;

import java.util.Date;

public class Materia {

	private long idMateria;
	private Perfil perfil;
	private String titulo;
	private String subtitulo;
	private Date data = new Date();
	private String imagem;
	private String fonteImagem;
	private String corpo;
	private String fonteMateria;

	public long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(long idMateria) {
		this.idMateria = idMateria;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getFonteImagem() {
		return fonteImagem;
	}

	public void setFonteImagem(String fonteImagem) {
		this.fonteImagem = fonteImagem;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public String getFonteMateria() {
		return fonteMateria;
	}

	public void setFonteMateria(String fonteMateria) {
		this.fonteMateria = fonteMateria;
	}

}
