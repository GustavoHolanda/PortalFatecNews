package labeng.projeto.models;

public class Escritor extends Pessoa {

	private String especialidade;
	private String blog;
	private String biografiaepremios;
	private int codigoUBE;
	
	public int getCodigoUBE() {
		return codigoUBE;
	}

	public void setCodigoUBE(int codigoUBE) {
		this.codigoUBE = codigoUBE;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getBiografiaepremios() {
		return biografiaepremios;
	}

	public void setBiografiaepremios(String biografiaepremios) {
		this.biografiaepremios = biografiaepremios;
	}

}
