package labeng.projeto.models;

import java.util.Date;

public class Materia {

	private int id;
	private Date data;
	private String titulo;
	private String subTitulo;
	private String imagem;
	private String fonteImagem;
	private String corpoMateria;
	private String fonteMateria;
	private Escritor escritor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
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

	public String getCorpoMateria() {
		return corpoMateria;
	}

	public void setCorpoMateria(String corpoMateria) {
		this.corpoMateria = corpoMateria;
	}

	public String getFonteMateria() {
		return fonteMateria;
	}

	public void setFonteMateria(String fonteMateria) {
		this.fonteMateria = fonteMateria;
	}

	public Escritor getEscritor() {
		return escritor;
	}

	public void setEscritor(Escritor escritor) {
		this.escritor = escritor;
	}

}
