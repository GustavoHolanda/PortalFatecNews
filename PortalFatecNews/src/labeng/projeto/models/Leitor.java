package labeng.projeto.models;

public class Leitor extends Pessoa{

	private String categoriaPreferida;
	private String redeSocial;

	public String getRedeSocial() {
		return redeSocial;
	}

	public void setRedeSocial(String redeSocial) {
		this.redeSocial = redeSocial;
	}

	public String getCategoriaPreferida() {
		return categoriaPreferida;
	}

	public void setCategoriaPreferida(String categoriaPreferida) {
		this.categoriaPreferida = categoriaPreferida;
	}
	
}
